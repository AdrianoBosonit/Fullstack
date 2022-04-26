package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Autobus.AutobusEntity;
import back.ejercicioFinal.content.Autobus.AutobusRepo;
import back.ejercicioFinal.content.Correo.CorreoInputDto;
import back.ejercicioFinal.content.Correo.CorreoRepo;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.MailSender;
import back.ejercicioFinal.shared.kafka.MessageKafka;
import back.ejercicioFinal.shared.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    ReservaRepo reservaRepo;

    @Autowired
    AutobusRepo autobusRepo;

    @Autowired
    CorreoRepo correoRepo;

    @Autowired
    MailSender mailSender;

    @Autowired
    MessageProducer messageProducer;

    @Value(value = "${message.group.name}")
    private String grupo;

    private List<MessageKafka> mensajesPendientes;


    public ReservaServiceImpl() {
        this.mensajesPendientes = new ArrayList<>();
    }


    @Override
    public ReservaOutputDto addAndSend(ReservaInputDto reservaInputDto) throws Exception {
        ReservaEntity reservaEntity = add(new ReservaEntity(reservaInputDto));
        messageProducer.sendMessageTopic("sincronizacion", new MessageKafka(grupo, MessageKafka.Accion.CREATE, reservaEntity.sinBus()));
        return new ReservaOutputDto(reservaEntity);
    }

    @Override
    public ReservaOutputDto removeId(String id) {
        ReservaEntity reservaEntity = reservaRepo.findById(id).orElseThrow(() -> new BadRequestException("ID de autobus no encontrado"));
        mensajesPendientes.add(new MessageKafka(grupo, MessageKafka.Accion.DELETE, reservaEntity));
        AutobusEntity autobusEntity = autobusRepo.findById(reservaEntity.busReserva.getIdBus()).get();
        autobusEntity.getReservas().remove(reservaEntity);
        reservaRepo.deleteById(reservaEntity.getIdReserva());
        if (autobusEntity.getReservas().size() == 0) {
            autobusRepo.deleteById(autobusEntity.getIdBus());
        }
        return new ReservaOutputDto(reservaEntity);

    }

    @Override
    public void removeBus(String id) {
        AutobusEntity autobusEntity = autobusRepo.findById(id).orElseThrow(() -> new BadRequestException("ID de autobus no encontrado"));
        for (ReservaEntity reservaEntity : autobusEntity.getReservas()) {
            removeId(reservaEntity.getIdReserva());
        }
        autobusRepo.deleteById(id);
    }

    @Override
    public List<ReservaEntity> findAll() {
        return reservaRepo.findAll();
    }


    @Override
    public ReservaEntity add(ReservaEntity reservaEntity) {
        try {
            if (reservaRepo.findByCiudadAndEmailAndFechaReservaAndHoraReserva(reservaEntity.getCiudad(), reservaEntity.getEmail(), reservaEntity.getFechaReserva(), reservaEntity.getHoraReserva()) != null)
                throw new BadRequestException("Reserva invalida");
            reservaEntity.setConfirmado(true);
            List<AutobusEntity> listaAutobuses = autobusRepo.findByCiudadDestinoAndFechaSalidaAndHoraSalida(reservaEntity.getCiudad(), reservaEntity.getFechaReserva(), reservaEntity.getHoraReserva());
            AutobusEntity autobusEntity;
            if (listaAutobuses.isEmpty()) {
                autobusEntity = new AutobusEntity(reservaEntity);
                autobusRepo.saveAndFlush(autobusEntity);
            } else {
                autobusEntity = listaAutobuses.get(0);
            }
            CorreoInputDto correoInputDto = new CorreoInputDto(reservaEntity);
            reservaEntity.setBusReserva(autobusEntity);
            correoRepo.saveAndFlush(mailSender.sendEmail(correoInputDto));
            reservaEntity = reservaRepo.saveAndFlush(reservaEntity);
            autobusEntity.addReserva(reservaEntity);
            autobusRepo.saveAndFlush(autobusEntity);
            mensajesPendientes.add(new MessageKafka(grupo, MessageKafka.Accion.UPDATE, reservaEntity));
            return reservaEntity;
        } catch (Exception e) {
            throw new BadRequestException("Reserva invalida: " + e);
        }
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    void actualizarBD() {
        if (mensajesPendientes.size() > 0) {
            System.out.println("Actualizando");
            for (MessageKafka mensaje : mensajesPendientes) {
                messageProducer.sendMessageTopic("actualizacion", mensaje);
            }
            mensajesPendientes.clear();
        } else
            System.out.println("No hay nada para actualizar");
    }
}



