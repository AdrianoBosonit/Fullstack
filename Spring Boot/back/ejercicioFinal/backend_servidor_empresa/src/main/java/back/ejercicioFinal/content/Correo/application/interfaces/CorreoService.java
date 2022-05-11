package back.ejercicioFinal.content.Correo.application.interfaces;

import back.ejercicioFinal.content.Correo.infrastructure.dto.CorreoInputDto;
import back.ejercicioFinal.content.Correo.infrastructure.dto.CorreoOutputDto;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;

import java.util.Date;
import java.util.List;

public interface CorreoService {
    public ReservaOutputDto putCorreo(CorreoInputDto correoInputDto);

    public List<CorreoOutputDto> getCorreos(String ciudadDestino, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior);
}
