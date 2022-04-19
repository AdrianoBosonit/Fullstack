package back.ejercicioFinal.content.Correo;

import back.ejercicioFinal.content.Reserva.ReservaOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorreoInputDto {
    private String ciudad;
    private String email;
    private Date fechaReserva;
    private Float horaReserva;

    public CorreoInputDto(ReservaOutputDto reservaOutputDto) {
        this.ciudad = reservaOutputDto.getCiudadDestino();
        this.email = reservaOutputDto.getEmail();
        this.fechaReserva = reservaOutputDto.getFechaReserva();
        this.horaReserva = reservaOutputDto.getHoraReserva();
    }
}
