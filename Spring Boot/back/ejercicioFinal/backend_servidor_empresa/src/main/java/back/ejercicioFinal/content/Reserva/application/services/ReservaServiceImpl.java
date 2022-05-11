package back.ejercicioFinal.content.Reserva.application.services;

import back.ejercicioFinal.content.Autobus.domain.AutobusEntity;
import back.ejercicioFinal.content.Autobus.repository.AutobusRepo;
import back.ejercicioFinal.content.Correo.infrastructure.dto.CorreoInputDto;
import back.ejercicioFinal.content.Correo.repository.CorreoRepo;
import back.ejercicioFinal.content.Reserva.domain.ReservaEntity;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaInputDto;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;
import back.ejercicioFinal.content.Reserva.repository.ReservaRepo;
import back.ejercicioFinal.content.Reserva.application.interfaces.ReservaService;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.mailSender.MailSender;
import back.ejercicioFinal.shared.kafka.MessageKafka;
import back.ejercicioFinal.shared.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public ReservaOutputDto removeReservaId(String id) {
        ReservaEntity reservaEntity = reservaRepo.findById(id).orElseThrow(() -> new BadRequestException("ID de autobus no encontrado"));
        AutobusEntity autobusEntity = autobusRepo.findById(reservaEntity.getBusReserva().getIdBus()).get();
        autobusEntity.getReservas().remove(reservaEntity);

        if (autobusEntity.getReservas().isEmpty()) {
            autobusRepo.deleteById(autobusEntity.getIdBus());
        }
        mensajesPendientes.add(new MessageKafka(grupo, MessageKafka.Accion.DELETE, reservaEntity.sinBus()));
        return new ReservaOutputDto(reservaEntity);

    }


    @Override
    public List<ReservaOutputDto> findAllReservas() {
        return reservaRepo.findAll().stream().map(p -> new ReservaOutputDto(p)).collect(Collectors.toList());
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
            System.out.println("Actualizando BacksWeb");
            for (MessageKafka mensaje : mensajesPendientes) {
                messageProducer.sendMessageTopic("actualizacion", mensaje);
            }
            mensajesPendientes.clear();
        }
    }

    @Override
    public List<ReservaOutputDto> getReservasRestringidas(String ciudad, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior) {
        return null;
    }

    @Override
    public List<ReservaEntity> findReservaConRangos(String ciudad, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior) {
        return reservaRepo.findReservaConRangos(ciudad, fechaSuperior, fechaInferior, horaInferior, horaSuperior);
    }
}



