package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Autobus.AutobusEntity;
import back.ejercicioFinal.content.Autobus.AutobusRepo;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public ReservaOutputDto addAndSend(ReservaInputDto reservaInputDto) throws Exception {
        ReservaEntity reservaEntity = add(new ReservaEntity(reservaInputDto));
        messageProducer.sendMessageTopic1("sincronizacion", reservaEntity.sinBus());
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
            throw new BadRequestException("Reserva invalida: "+e);
        }
    }

    @Override
    public List<ReservaEntity> findAll() {
        return reservaRepo.findAll();
    }
}
