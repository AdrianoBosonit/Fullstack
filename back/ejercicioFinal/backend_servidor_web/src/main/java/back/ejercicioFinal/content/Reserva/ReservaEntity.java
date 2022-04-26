package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Autobus.AutobusEntity;
import back.ejercicioFinal.shared.StringPrefixedSequenceIdGenerator;
import back.ejercicioFinal.shared.validator.CheckValores;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "ciudad")
    @CheckValores(parametros = "BARCELONA && VALENCIA && MADRID && BILBAO")
    private String ciudad;

    @NotNull
    @Column
    private String nombre;

    @NotNull
    @Column
    private String apellidos;

    @NotNull
    @Column
    private String telefono;

    @NotNull
    @Column
    @Email
    private String email;

    @NotNull
    @Column
    @Future
    private Date fechaReserva;

    @NotNull
    @Column(name = "horaReserva")
    @CheckValores(parametros = "8.0 && 12.0 && 16.0 && 20.0")
    private Float horaReserva;

    @NotNull
    @Column
    private Boolean confirmado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name="idBus")
    AutobusEntity busReserva;


    public ReservaEntity(ReservaInputDto reservaInputDto) throws Exception {
        this.ciudad = reservaInputDto.getCiudad().toUpperCase(Locale.ROOT);
        this.nombre = reservaInputDto.getNombre();
        this.apellidos = reservaInputDto.getApellidos();
        this.telefono = reservaInputDto.getTelefono();
        this.email = reservaInputDto.getEmail();
        this.fechaReserva = reservaInputDto.getFechaReserva();
        this.horaReserva = reservaInputDto.getHoraReserva();
        this.confirmado = false;
        this.busReserva = null;
    }

    public ReservaEntity(ReservaInputDto reservaInputDto, AutobusEntity autobusEntity) throws Exception {
        this.ciudad = reservaInputDto.getCiudad().toUpperCase(Locale.ROOT);
        this.nombre = reservaInputDto.getNombre();
        this.apellidos = reservaInputDto.getApellidos();
        this.telefono = reservaInputDto.getTelefono();
        this.email = reservaInputDto.getEmail();
        this.fechaReserva = reservaInputDto.getFechaReserva();
        this.horaReserva = reservaInputDto.getHoraReserva();
        this.confirmado = false;
        this.busReserva = autobusEntity;
    }

    public ReservaEntity sinBus() {
        this.busReserva = null;
        return this;
    }

    public ReservaEntity updateReserva(ReservaEntity reservaEntity){
        this.ciudad = reservaEntity.getCiudad();
        this.nombre = reservaEntity.getNombre();
        this.apellidos = reservaEntity.getApellidos();
        this.telefono = reservaEntity.getTelefono();
        this.email = reservaEntity.getEmail();
        this.fechaReserva = reservaEntity.getFechaReserva();
        this.horaReserva = reservaEntity.getHoraReserva();
        this.confirmado = reservaEntity.getConfirmado();
        this.busReserva = reservaEntity.getBusReserva();
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
