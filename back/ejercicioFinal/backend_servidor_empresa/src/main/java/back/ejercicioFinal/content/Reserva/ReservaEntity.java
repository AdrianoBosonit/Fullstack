package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Autobus.AutobusEntity;
import back.ejercicioFinal.content.Correo.CorreoEntity;
import back.ejercicioFinal.shared.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Locale;

@Data
@Entity
@Table(name = "reserva")
@NoArgsConstructor
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_seq")
    @GenericGenerator(
            name = "reserva_seq",
            strategy = "back.ejercicioFinal.shared.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "RES_"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "idReserva")
    private String idReserva;

    @NonNull
    @Column(name = "ciudad", columnDefinition = "VARCHAR(60) CHECK (ciudad IN ('BARCELONA', 'VALENCIA','MADRID','BILBAO'))")
    private String ciudad;

    @NonNull
    @Column
    private String nombre;

    @NonNull
    @Column
    private String apellidos;

    @NonNull
    @Column
    private String telefono;

    @NonNull
    @Column
    @Email
    private String email;

    @NonNull
    @Column
    private Date fechaReserva;

    @NonNull
    @Column(name = "horaReserva")
    //@Pattern(regexp = "((^ 8.0 $ | ^ 12.0 $ | ^ 16.0 $ | ^ 20.0 $))")
    private Float horaReserva;

    @NonNull
    @Column
    private Boolean confirmado;

    @ManyToOne(fetch = FetchType.LAZY)
    AutobusEntity busReserva;

    @OneToOne
    @JoinColumn(name="idCorreo")
    private CorreoEntity correo;

    public ReservaEntity(ReservaInputDto reservaInputDto) throws Exception {
        this.ciudad = reservaInputDto.getCiudad().toUpperCase(Locale.ROOT);
        this.nombre = reservaInputDto.getNombre();
        this.apellidos = reservaInputDto.getApellidos();
        this.telefono = reservaInputDto.getTelefono();
        this.email = reservaInputDto.getEmail();
        this.fechaReserva = reservaInputDto.getFechaReserva();
        this.horaReserva = reservaInputDto.getHoraReserva();
        if (horaReserva != 8 && horaReserva != 12 && horaReserva != 16 && horaReserva != 20)
            throw new Exception();
        this.confirmado = false;
        this.busReserva = null;
        this.correo=null;
    }

    public ReservaEntity(ReservaOutputDto reservaOutputDto) throws Exception {
        this.ciudad = reservaOutputDto.getCiudadDestino().toUpperCase(Locale.ROOT);
        this.nombre = reservaOutputDto.getNombre();
        this.apellidos = reservaOutputDto.getApellido();
        this.telefono = reservaOutputDto.getTelefono();
        this.email = reservaOutputDto.getEmail();
        this.fechaReserva = reservaOutputDto.getFechaReserva();
        this.horaReserva = reservaOutputDto.getHoraReserva();
        if (horaReserva != 8 && horaReserva != 12 && horaReserva != 16 && horaReserva != 20)
            throw new Exception();
        this.confirmado = false;
        this.correo=null;
    }

    public ReservaEntity(ReservaOutputDto reservaOutputDto, AutobusEntity autobusEntity, CorreoEntity correoEntity) throws Exception {
        this.ciudad = reservaOutputDto.getCiudadDestino().toUpperCase(Locale.ROOT);
        this.nombre = reservaOutputDto.getNombre();
        this.apellidos = reservaOutputDto.getApellido();
        this.telefono = reservaOutputDto.getTelefono();
        this.email = reservaOutputDto.getEmail();
        this.fechaReserva = reservaOutputDto.getFechaReserva();
        this.horaReserva = reservaOutputDto.getHoraReserva();
        if (horaReserva != 8 && horaReserva != 12 && horaReserva != 16 && horaReserva != 20)
            throw new Exception();
        this.confirmado = false;
        this.busReserva = autobusEntity;
        this.correo=correoEntity;

    }

    @Override
    public String toString() {
        return "ReservaEntity{" +
                "idReserva='" + idReserva + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaReserva=" + fechaReserva +
                ", horaReserva=" + horaReserva +
                ", confirmado=" + confirmado +
                ", busReserva=" + busReserva +
                ", correo=" + correo.getIdCorreo() +
                '}';
    }
}
