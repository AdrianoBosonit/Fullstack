package back.ejercicioFinal.content.Reserva.domain;

import back.ejercicioFinal.content.Autobus.domain.AutobusEntity;
import back.ejercicioFinal.content.Correo.domain.CorreoEntity;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaInputDto;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;
import back.ejercicioFinal.shared.StringPrefixedSequenceIdGenerator;
import back.ejercicioFinal.shared.validator.CheckValores;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

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
    @Column(name = "ciudad")
    @CheckValores(parametros = "Barcelona && Valencia && Madrid && Bilbao")
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
    @CheckValores(parametros = "8.0 && 12.0 && 16.0 && 20.0")
    private Float horaReserva;

    @NonNull
    @Column
    private Boolean confirmado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "idBus")
    AutobusEntity busReserva;


    public ReservaEntity(ReservaInputDto reservaInputDto) throws Exception {
        this.ciudad = reservaInputDto.getCiudad();
        this.nombre = reservaInputDto.getNombre();
        this.apellidos = reservaInputDto.getApellidos();
        this.telefono = reservaInputDto.getTelefono();
        this.email = reservaInputDto.getEmail();
        this.fechaReserva = reservaInputDto.getFechaReserva();
        this.horaReserva = reservaInputDto.getHoraReserva();
        this.confirmado = true;
        this.busReserva = null;
    }

    public ReservaEntity(ReservaOutputDto reservaOutputDto) throws Exception {
        this.ciudad = reservaOutputDto.getCiudadDestino();
        this.nombre = reservaOutputDto.getNombre();
        this.apellidos = reservaOutputDto.getApellido();
        this.telefono = reservaOutputDto.getTelefono();
        this.email = reservaOutputDto.getEmail();
        this.fechaReserva = reservaOutputDto.getFechaReserva();
        this.horaReserva = reservaOutputDto.getHoraReserva();
        this.confirmado = true;
    }

    public ReservaEntity(ReservaInputDto reservaInputDto, AutobusEntity autobusEntity, CorreoEntity correoEntity) throws Exception {
        this.ciudad = reservaInputDto.getCiudad();
        this.nombre = reservaInputDto.getNombre();
        this.apellidos = reservaInputDto.getApellidos();
        this.telefono = reservaInputDto.getTelefono();
        this.email = reservaInputDto.getEmail();
        this.fechaReserva = reservaInputDto.getFechaReserva();
        this.horaReserva = reservaInputDto.getHoraReserva();
        this.confirmado = true;
        this.busReserva = autobusEntity;
    }

    public ReservaEntity sinBus() {
        this.busReserva = null;
        return this;
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
                '}';
    }
}
