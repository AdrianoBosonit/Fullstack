package back.ejercicioFinal.content.Reserva.application.interfaces;

import back.ejercicioFinal.content.Reserva.domain.ReservaEntity;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaInputDto;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;

import java.util.Date;
import java.util.List;

public interface ReservaService {
    public ReservaOutputDto addAndSend(ReservaInputDto reservaInputDto) throws Exception;

    public ReservaOutputDto removeReservaId(String id);

    public List<ReservaOutputDto> findAllReservas();

    public ReservaEntity add(ReservaEntity reservaEntity);

    public List<ReservaOutputDto> getReservasRestringidas(String ciudad, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior);

    public List<ReservaEntity> findReservaConRangos(String ciudad, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior);
}
