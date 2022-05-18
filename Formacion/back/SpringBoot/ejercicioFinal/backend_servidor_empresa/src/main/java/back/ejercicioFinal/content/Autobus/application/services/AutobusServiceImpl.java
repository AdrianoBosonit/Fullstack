package back.ejercicioFinal.content.Autobus.application.services;

import back.ejercicioFinal.content.Autobus.domain.AutobusEntity;
import back.ejercicioFinal.content.Autobus.repository.AutobusRepo;
import back.ejercicioFinal.content.Autobus.infrastructure.dto.ReservaDisponibleOutputDto;
import back.ejercicioFinal.content.Autobus.application.interfaces.AutobusService;
import back.ejercicioFinal.content.Reserva.application.interfaces.ReservaService;
import back.ejercicioFinal.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutobusServiceImpl implements AutobusService {
    @Autowired
    AutobusRepo autobusRepo;

    @Autowired
    ReservaService reservaService;

    @Override
    public ReservaDisponibleOutputDto removeBusId(String id) {
        AutobusEntity autobusEntity = autobusRepo.findById(id).orElseThrow(() -> new BadRequestException("ID de autobus no encontrado"));
        Integer tam = autobusEntity.getReservas().size();
        if (!tam.equals(0)) {
            for (int i = tam - 1; i >= 0; i--) {
                reservaService.removeReservaId(autobusEntity.getReservas().get(i).getIdReserva());
            }
        }
        return new ReservaDisponibleOutputDto(autobusEntity);
    }

    @Override
    public List<ReservaDisponibleOutputDto> findAllBus() {
        return autobusRepo.findAll().stream().map(p -> new ReservaDisponibleOutputDto(p)).collect(Collectors.toList());
    }

    @Override
    public List<ReservaDisponibleOutputDto> getReservasRestringidasDisponibles(String ciudad, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior) {
        return reservaService.findReservaConRangos(ciudad, fechaSuperior, fechaInferior, horaInferior, horaSuperior).stream().map(p -> new ReservaDisponibleOutputDto(autobusRepo.findById(p.getBusReserva().getIdBus()).orElseThrow(() -> new BadRequestException("Error")))).collect(Collectors.toList());
    }
}
