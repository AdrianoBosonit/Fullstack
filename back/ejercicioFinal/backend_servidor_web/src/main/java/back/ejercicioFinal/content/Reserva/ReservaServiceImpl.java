package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Autobus.AutobusEntity;
import back.ejercicioFinal.content.Autobus.AutobusRepo;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.kafka.MessageKafka;
import back.ejercicioFinal.shared.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    ReservaRepo reservaRepo;

    @Autowired
    AutobusRepo autobusRepo;

    @Autowired
    MessageProducer messageProducer;

    @Value(value = "${message.group.name}")
    private String grupo;


    @Override
    public ReservaOutputDto addAndSend(ReservaInputDto reservaInputDto) throws Exception {
        ReservaEntity reservaEntity = add(new ReservaEntity(reservaInputDto));
        messageProducer.sendMessageTopic("sincronizacion", new MessageKafka(grupo, MessageKafka.Accion.CREATE, reservaEntity.sinBus()));
        return new ReservaOutputDto(reservaEntity);
    }

    @Override
    public ReservaOutputDto removeId(String id) {
        ReservaEntity reservaEntity = reservaRepo.findById(id).orElseThrow(() -> new BadRequestException("ID de autobus no encontrado"));
        AutobusEntity autobusEntity = autobusRepo.findById(reservaEntity.getBusReserva().getIdBus()).get();
        autobusEntity.getReservas().remove(reservaEntity);
        System.out.println(reservaEntity);
        //reservaRepo.deleteById(reservaEntity.getIdReserva());
        if (autobusEntity.getReservas().size() == 0) {
            autobusRepo.deleteById(autobusEntity.getIdBus());
        }
        autobusRepo.saveAndFlush(autobusEntity);
        System.out.println(reservaRepo.findAll());
        System.out.println(autobusRepo.findAll());
        return new ReservaOutputDto(reservaEntity);

    }

    @Override
    public ReservaEntity add(ReservaEntity reservaEntity) {
        try {
            if (reservaRepo.findByCiudadAndEmailAndFechaReservaAndHoraReserva(reservaEntity.getCiudad(), reservaEntity.getEmail(), reservaEntity.getFechaReserva(), reservaEntity.getHoraReserva()) != null)
                throw new BadRequestException("Reserva invalida");
            List<AutobusEntity> listaAutobuses = autobusRepo.findByCiudadDestinoAndFechaSalidaAndHoraSalida(reservaEntity.getCiudad(), reservaEntity.getFechaReserva(), reservaEntity.getHoraReserva());
            AutobusEntity autobusEntity;
            if (listaAutobuses.isEmpty()) {
                autobusEntity = new AutobusEntity(reservaEntity);
                autobusRepo.saveAndFlush(autobusEntity);
            } else {
                autobusEntity = listaAutobuses.get(0);
            }
            reservaEntity.setBusReserva(autobusEntity);
            reservaEntity = reservaRepo.saveAndFlush(reservaEntity);
            autobusEntity.addReserva(reservaEntity);
            autobusRepo.saveAndFlush(autobusEntity);
            return reservaEntity;
        } catch (Exception e) {
            throw new BadRequestException("Reserva invalida: " + e);
        }
    }

    @Override
    public List<ReservaEntity> findAll() {
        return reservaRepo.findAll();
    }

    @Override
    public void updateReservaFromEmpresa(ReservaEntity reservaEntity) {
        ReservaEntity reserva = reservaRepo.findById(reservaEntity.getIdReserva()).orElseThrow(() -> new BadRequestException("Error"));
        AutobusEntity autobusEntity = autobusRepo.findById(reserva.getBusReserva().getIdBus()).orElseThrow(() -> new BadRequestException("Error"));
        for (int i = 0; i < autobusEntity.getReservas().size(); i++) {
            if (autobusEntity.getReservas().get(i).equals(reserva.getIdReserva())) {
                reservaRepo.saveAndFlush(autobusEntity.getReservas().get(i).updateReserva(reservaEntity));

            }
        }
        autobusRepo.saveAndFlush(autobusEntity);

        System.out.println(reservaRepo.findAll());
        System.out.println(autobusRepo.findAll());
    }
}
