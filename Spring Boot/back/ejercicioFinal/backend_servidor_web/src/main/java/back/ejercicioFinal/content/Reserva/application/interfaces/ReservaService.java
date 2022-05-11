package back.ejercicioFinal.content.Reserva.application.interfaces;

import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaInputDto;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;
import back.ejercicioFinal.content.Reserva.domain.ReservaEntity;

import java.util.Date;
import java.util.List;

public interface ReservaService {
    public ReservaOutputDto addAndSend(ReservaInputDto reservaInputDto) throws Exception;

    public List<ReservaOutputDto> findAllReservas();

    public ReservaOutputDto removeReservaId(String id);

    public ReservaEntity add(ReservaEntity reservaEntity);

    public void updateReservaFromEmpresa(ReservaEntity reservaEntity);

    List<ReservaOutputDto> getReservasRestringidas(String ciudad, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior);

}
