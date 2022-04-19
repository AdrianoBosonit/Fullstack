package back.ejercicioFinal.content.Correo;

import lombok.Data;

import java.util.Date;

@Data
public class CorreoOutputDto {
    private String ciudad;
    private String email;
    private Date fechaReserva;
    private Float horaReserva;

    public CorreoOutputDto(CorreoEntity correoEntity) {
        this.ciudad = correoEntity.getCiudad();
        this.email = correoEntity.getEmail();
        this.fechaReserva = correoEntity.getFechaReserva();
        this.horaReserva = correoEntity.getHoraReserva();
    }
}
