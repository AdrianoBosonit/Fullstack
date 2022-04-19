package back.ejercicioFinal.content.Autobus;

import back.ejercicioFinal.content.Reserva.ReservaEntity;
import back.ejercicioFinal.content.Reserva.ReservaOutputDto;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bus")
@Data
@NoArgsConstructor
public class AutobusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autobus_seq")
    @GenericGenerator(
            name = "autobus_seq",
            strategy = "back.ejercicioFinal.shared.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "BUS_"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column
    private String idBus;
    @Column
    private String ciudadDestino;
    @Column
    private Date fechaSalida;
    @Column
    private Float horaSalida;

    @OneToMany(mappedBy = "busReserva", cascade = CascadeType.ALL)
    @Size(min = 0, max = 2)
    private List<ReservaEntity> reservas;

    public AutobusEntity(ReservaOutputDto reservaOutputDto) {
        this.ciudadDestino = reservaOutputDto.getCiudadDestino();
        this.fechaSalida = reservaOutputDto.getFechaReserva();
        this.horaSalida = reservaOutputDto.getHoraReserva();
        this.reservas = new ArrayList<>();
    }

    public boolean addReserva( ReservaEntity entity) {
        if(reservas.size()>40)
            throw new BadRequestException("Numero de plazas superadas");
        return reservas.add(entity);
    }

    @Override
    public String toString() {
        ArrayList<String> reservasString = new ArrayList<>();
        for (ReservaEntity p : reservas) {
            reservasString.add("idReserva=" + p.getIdReserva() + ", nombre=" + p.getNombre() + ", apellidos=" + p.getApellidos());
        }
        return "AutobusEntity{" +
                "idBus='" + idBus + '\'' +
                ", ciudadDestino='" + ciudadDestino + '\'' +
                ", fechaSalida=" + fechaSalida +
                ", horaSalida=" + horaSalida +
                ", numeroPlazasDisponibles=" + reservas.size() +
                ", reservas=" + reservasString +
                '}';
    }
}
