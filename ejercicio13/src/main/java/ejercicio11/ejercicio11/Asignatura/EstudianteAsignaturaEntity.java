package ejercicio11.ejercicio11.Asignatura;

import ejercicio11.ejercicio11.Estudiante.EstudianteEntity;
import ejercicio11.ejercicio11.Profesor.ProfesorEntity;
import ejercicio11.ejercicio11.shared.StringPrefixedSequenceIdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="estudios")
@Getter
@Setter
public class EstudianteAsignaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ausencias_seq")
    @GenericGenerator(
            name = "ausencias_seq",
            strategy = "src/main/java/ejercicio11/ejercicio11/shared/StringPrefixedSequenceIdGenerator.java",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    @Column(name = "id_asignatura")
    private String idAsignatura;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idProfesor")
    ProfesorEntity profesor;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idEstudiante")
    EstudianteEntity estudiante;
    @Column(name="asignatura")
    String asignatura;
    @Column(name="comentarios")
    String comentarios;
    @Column(name="initial_date")
    Date initialDate;
    @Column(name="finish_date")
    Date finishDate;


}
