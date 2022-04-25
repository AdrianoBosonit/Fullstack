package back.ejercicioFinal.content.Reserva;

import lombok.Data;

import java.util.Date;


@Data
public class ReservaInputDto {
    private String ciudad;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private Date fechaReserva;
    private Float horaReserva;
}
