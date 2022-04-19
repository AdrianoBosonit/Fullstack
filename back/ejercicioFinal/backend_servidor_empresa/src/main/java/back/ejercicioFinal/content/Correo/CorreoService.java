package back.ejercicioFinal.content.Correo;

import back.ejercicioFinal.content.Reserva.ReservaOutputDto;

import java.util.Date;
import java.util.List;

public interface CorreoService {
    public ReservaOutputDto putCorreo(CorreoInputDto correoInputDto);
    public List<CorreoOutputDto> getCorreos(String ciudadDestino, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior);
}
