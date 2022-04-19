package back.ejercicioFinal.content.Correo;

import back.ejercicioFinal.shared.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "Correo")
@NoArgsConstructor
public class CorreoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "correo_seq")
    @GenericGenerator(
            name = "correo_seq",
            strategy = "back.ejercicioFinal.shared.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CORR_"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "idCorreo")
    private String idCorreo;
    @NonNull
    @Column(name = "ciudad", columnDefinition = "VARCHAR(60) CHECK (ciudad IN ('BARCELONA', 'VALENCIA','MADRID','BILBAO'))")
    private String ciudad;

    @NonNull
    @Column
    @Email
    private String email;

    @NonNull
    @Column
    private Date fechaReserva;

    @NonNull
    @Column(name = "horaReserva")
    private Float horaReserva;


    public String getMensaje() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(this.fechaReserva);
        return """
                Buenos días, nos complace decirle que su reserva:
                \n  -  Ciudad:  """ + this.ciudad + """
                \n  -  Hora:  """ + this.horaReserva + """
                \n  -  Fecha:  """ +strDate + """
                \n \nHa sido realizada con exito.Gracias por elegirnos. Disfrute de su viaje.
                """;
    }

    public CorreoEntity(CorreoInputDto correoInputDto) {
        this.ciudad = correoInputDto.getCiudad();
        this.email = correoInputDto.getEmail();
        this.fechaReserva = correoInputDto.getFechaReserva();
        this.horaReserva = correoInputDto.getHoraReserva();
    }
}
