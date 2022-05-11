package back.ejercicioFinal.content.Reserva.application.services;

import back.ejercicioFinal.content.Autobus.domain.AutobusEntity;
import back.ejercicioFinal.content.Autobus.repository.AutobusRepo;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaInputDto;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;
import back.ejercicioFinal.content.Reserva.repository.ReservaRepo;
import back.ejercicioFinal.content.Reserva.application.interfaces.ReservaService;
import back.ejercicioFinal.content.Reserva.domain.ReservaEntity;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.kafka.MessageKafka;
import back.ejercicioFinal.shared.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public ReservaOutputDto removeReservaId(String id) {
        ReservaEntity reservaEntity = reservaRepo.findById(id).orElseThrow(() -> new BadRequestException("ID de reserva no encontrado"));
        reservaEntity.getBusReserva().getReservas().remove(reservaEntity);
        if (reservaEntity.getBusReserva().getReservas().isEmpty()) {
            autobusRepo.deleteById(reservaEntity.getBusReserva().getIdBus());
        }
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
    public List<ReservaOutputDto> findAllReservas() {
        return reservaRepo.findAll().stream().map(p -> new ReservaOutputDto(p)).collect(Collectors.toList());
    }


    @Override
    public void updateReservaFromEmpresa(ReservaEntity reservaEntity) {
        ReservaEntity reserva = reservaRepo.findById(reservaEntity.getIdReserva()).orElseThrow(() -> new BadRequestException("Error"));
        reservaEntity.setBusReserva(reserva.getBusReserva());
        reservaRepo.saveAndFlush(reservaEntity);
    }

    @Override
    public List<ReservaOutputDto> getReservasRestringidas(String ciudad, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior) {
        return null;
    }

}
