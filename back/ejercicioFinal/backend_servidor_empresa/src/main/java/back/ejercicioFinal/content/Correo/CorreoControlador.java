package back.ejercicioFinal.content.Correo;

import back.ejercicioFinal.content.Reserva.ReservaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v0/")
public class CorreoControlador {
    @Autowired
    CorreoService correoService;

    @PutMapping
    public ReservaOutputDto putCorreo(@Valid @RequestBody CorreoInputDto correoInputDto) {
        return correoService.putCorreo(correoInputDto);
    }

    @GetMapping("/correos")
    public List<CorreoOutputDto> getCorreo(@RequestParam(value="ciudadDestino") String ciudadDestino, @RequestParam(value = "fechaInferior") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInferior,
                                           @RequestParam(value = "fechaSuperior", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaSuperior,
                                           @RequestParam(value = "horaInferior", defaultValue = "0", required = false) Float horaInferior,
                                           @RequestParam(value = "horaSuperior", defaultValue = "23.59", required = false) Float horaSuperior) {
        return correoService.getCorreos(ciudadDestino, fechaInferior, fechaSuperior, horaInferior, horaSuperior);
    }


}
