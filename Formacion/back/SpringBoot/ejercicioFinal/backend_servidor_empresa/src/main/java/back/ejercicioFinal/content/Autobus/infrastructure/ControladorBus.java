package back.ejercicioFinal.content.Autobus.infrastructure;

import back.ejercicioFinal.content.Autobus.infrastructure.dto.ReservaDisponibleOutputDto;
import back.ejercicioFinal.content.Autobus.application.interfaces.AutobusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/")
public class ControladorBus {
    @Autowired
    AutobusService autobusService;

    @GetMapping("disponible/{ciudad}")
    public List<ReservaDisponibleOutputDto> getReservaDisponible(@PathVariable String ciudad, @RequestParam(value = "fechaInferior") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInferior,
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

        return autobusService.getReservasRestringidasDisponibles(ciudad, fechaInferior, fechaSuperior, horaInferior, horaSuperior);
    }

    @GetMapping("allBus")
    public List<ReservaDisponibleOutputDto> getAllBus() {
        return autobusService.findAllBus();
    }

    @DeleteMapping("deleteBus/{idBus}")
    public ReservaDisponibleOutputDto deleteBus(@PathVariable String idBus) {
        return autobusService.removeBusId(idBus);
    }

}
