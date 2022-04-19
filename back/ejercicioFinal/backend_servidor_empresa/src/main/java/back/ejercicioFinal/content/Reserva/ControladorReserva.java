package back.ejercicioFinal.content.Reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v0/")
public class ControladorReserva {

    @Autowired
    ReservaService reservaService;

    @PostMapping("reserva")
    public ReservaOutputDto addReserva(@Valid @RequestBody ReservaOutputDto reservaOutputDto) {
        return reservaService.add(reservaOutputDto);
    }

    @DeleteMapping("/{id}")
    public void reservaRemoveId(@PathVariable String id) {
        reservaService.removeId(id);
    }

//    @GetMapping("disponible/{ciudad}")
//    public List<ReservaDisponibleOutputDto> getReserva(@PathVariable String ciudad, @RequestParam(value = "fechaInferior") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInferior,
//                                                       @RequestParam(value = "fechaSuperior", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaSuperior,
//                                                       @RequestParam(value = "horaInferior", defaultValue = "0", required = false) Float horaInferior,
//                                                       @RequestParam(value = "horaSuperior", defaultValue = "23.59", required = false) Float horaSuperior) {
//
//        return null;
//    }

}
