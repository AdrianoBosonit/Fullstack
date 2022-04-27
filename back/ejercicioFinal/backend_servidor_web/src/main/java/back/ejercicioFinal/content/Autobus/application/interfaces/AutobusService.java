package back.ejercicioFinal.content.Autobus.application.interfaces;

import back.ejercicioFinal.content.Autobus.infrastructure.dto.ReservaDisponibleOutputDto;

import java.util.Date;
import java.util.List;

public interface AutobusService {

    public ReservaDisponibleOutputDto removeBusId(String id);

    public List<ReservaDisponibleOutputDto> findAllBus();

    public List<ReservaDisponibleOutputDto> getReservasRestringidasDisponibles(String ciudad, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior);
}
