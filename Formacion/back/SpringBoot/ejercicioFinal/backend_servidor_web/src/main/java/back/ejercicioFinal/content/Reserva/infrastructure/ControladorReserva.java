package back.ejercicioFinal.content.Reserva.infrastructure;

import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaInputDto;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;
import back.ejercicioFinal.content.Reserva.application.interfaces.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/")
public class ControladorReserva {

    @Autowired
    ReservaService reservaService;

    @PostMapping("reserva")
    public ReservaOutputDto addReserva(@RequestBody @Valid ReservaInputDto reservaInputDto) throws Exception {
        System.out.println(reservaInputDto.getFechaReserva());
        return reservaService.addAndSend(reservaInputDto);
    }

    @GetMapping("reserva/{ciudad}")
    public List<ReservaOutputDto> getReserva(@PathVariable String ciudad, @RequestParam(value = "fechaInferior") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInferior,
                                             @RequestParam(value = "fechaSuperior", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaSuperior,
                                             @RequestParam(value = "horaInferior", defaultValue = "0", required = false) Float horaInferior,
                                             @RequestParam(value = "horaSuperior", defaultValue = "23.59", required = false) Float horaSuperior) {
        Optional<Date> fechaSup = Optional.ofNullable(fechaSuperior);
        if (fechaSup.isEmpty()) {
            Calendar c = Calendar.getInstance();
            c.setTime(fechaInferior);
            c.add(Calendar.DATE, 1);
            fechaSuperior = c.getTime();
        }
        return reservaService.getReservasRestringidas(ciudad, fechaInferior, fechaSuperior, horaInferior, horaSuperior);
    }


    @GetMapping("allReserva")
    public List<ReservaOutputDto> getAllReservas() {
        return reservaService.findAllReservas();
    }


    @DeleteMapping("deleteReserva/{idReserva}")
    public ReservaOutputDto deleteReserva(@PathVariable String idReserva) {
        return reservaService.removeReservaId(idReserva);
    }

}
