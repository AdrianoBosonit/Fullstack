package ejercicio11.ejercicio11.Estudiante;

import ejercicio11.ejercicio11.Asignatura.EstudianteAsignaturaEntity;
import ejercicio11.ejercicio11.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.Profesor.ProfesorEntity;
import ejercicio11.ejercicio11.shared.StringPrefixedSequenceIdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "estudiantes")
public class EstudianteEntity {
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
    @Column(name = "id")
    private String idEstudiante;

    @OneToOne
    @JoinColumn(name="idPersona")
    private PersonaEntity persona;
    @Column(name="horas_por_semana")
    private Integer numHoursWeek;
    @Column(name="comentarios")
    private String coments;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "idProfesor")
    //ProfesorEntity profesor;
    @Column(name="rama")
    private String branch;

    public EstudianteEntity(EstudianteInputDTO estudianteInputDTO){
        setIdEstudiante(estudianteInputDTO.getIdEstudiante());
        setPersona(estudianteInputDTO.getPersona());
        setNumHoursWeek(estudianteInputDTO.getNumHoursWeek());
        setComents(estudianteInputDTO.getComents());
        setBranch(estudianteInputDTO.getBranch());

    }

}
