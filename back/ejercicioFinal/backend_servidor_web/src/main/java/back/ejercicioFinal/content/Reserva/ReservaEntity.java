package back.ejercicioFinal.content.Reserva;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Locale;



@Data
@Entity
@Table(name = "reserva")
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer idReserva;

    @Column
    @Pattern(regexp = "((^ VALENCIA $ | ^ MADRID $ | ^ BARCELONA $ | ^ BILBAO $))")
    @NotBlank
    private String ciudad;

    @Column
    @NotBlank
    private String nombre;

    @Column
    @NotBlank
    private String apellidos;

    @Column
    @NotBlank
    private String telefono;

    @Column
    @NotBlank
    @Email
    private String email;

    @Column
    @NotBlank
    private Date fechaReserva;

    @Column
    @NotBlank
    @Pattern(regexp = "((^ 8 $ | ^ 12 $ | ^ 16 $ | ^ 20 $))")
    private Float horaReserva;

    @Column
    private Boolean confirmado;

    public ReservaEntity(ReservaInputDto reservaInputDto) {
        this.ciudad = reservaInputDto.getCiudad().toUpperCase(Locale.ROOT);
        this.nombre = reservaInputDto.getNombre();
        this.apellidos = reservaInputDto.getApellidos();
        this.telefono = reservaInputDto.getTelefono();
        this.email = reservaInputDto.getEmail();
        this.fechaReserva = reservaInputDto.getFechaReserva();
        this.horaReserva = reservaInputDto.getHoraReserva();
        this.confirmado=false;
    }
}
