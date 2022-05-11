package back.ejercicioFinal.content.Reserva.infrastructure;

import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaInputDto;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;
import back.ejercicioFinal.content.Reserva.application.interfaces.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v0/")
public class ControladorReserva {

    @Autowired
    ReservaService reservaService;

    @PostMapping("reserva")
    public ReservaOutputDto addReserva(@Valid @RequestBody ReservaInputDto reservaInputDto) throws Exception {
        return reservaService.addAndSend(reservaInputDto);
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
