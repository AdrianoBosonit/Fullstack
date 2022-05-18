package back.ejercicioFinal.content.Correo.infrastructure.dto;

import back.ejercicioFinal.content.Reserva.domain.ReservaEntity;
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

    public CorreoInputDto(ReservaEntity reservaEntity) {
        this.ciudad = reservaEntity.getCiudad();
        this.email = reservaEntity.getEmail();
        this.fechaReserva = reservaEntity.getFechaReserva();
        this.horaReserva = reservaEntity.getHoraReserva();
    }
}
