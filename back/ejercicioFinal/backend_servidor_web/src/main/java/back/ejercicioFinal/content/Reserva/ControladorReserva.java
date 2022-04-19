package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.ReservaDisponibleOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v0/")
public class ControladorReserva {

    @Autowired
    ReservaService reservaService;

    @PostMapping("reserva")
    public ReservaOutputDto addReserva(@Valid @RequestBody ReservaInputDto reservaInputDto) {
        return reservaService.add(reservaInputDto);
    }

    //    required=false
//    defaultValue = "simple"
    @GetMapping("disponible/{ciudad}")
    public List<ReservaDisponibleOutputDto> getReserva(@PathVariable String ciudad, @RequestParam(value = "fechaInferior") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInferior,
                                                       @RequestParam(value = "fechaSuperior", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaSuperior,
                                                       @RequestParam(value = "horaInferior", defaultValue = "0", required = false) Float horaInferior,
                                                       @RequestParam(value = "horaSuperior", defaultValue = "23.59", required = false) Float horaSuperior) {

        return null;
    }

}
