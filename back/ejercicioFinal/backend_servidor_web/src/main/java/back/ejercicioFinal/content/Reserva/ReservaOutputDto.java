package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Reserva.ReservaEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ReservaOutputDto {
    private String ciudadDestino;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Date fechaReserva;
    private Float horaReserva;

    public ReservaOutputDto(ReservaEntity reservaEntity) {
        this.ciudadDestino = reservaEntity.getCiudad();
        this.nombre = reservaEntity.getNombre();
        this.apellido = reservaEntity.getApellidos();
        this.telefono = reservaEntity.getTelefono();
        this.email = reservaEntity.getEmail();
        this.fechaReserva = reservaEntity.getFechaReserva();
        this.horaReserva = reservaEntity.getHoraReserva();
    }
}
