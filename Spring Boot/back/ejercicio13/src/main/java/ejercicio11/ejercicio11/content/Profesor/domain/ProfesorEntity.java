
package ejercicio11.ejercicio11.content.Profesor.domain;

import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.input.EstudianteInputDTO;
import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.input.ProfesorInputDTO;
import ejercicio11.ejercicio11.shared.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@Entity
@Table(name="Profesor")
public class ProfesorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesores_seq")
    @GenericGenerator(
            name = "profesores_seq",
            strategy = "ejercicio11.ejercicio11.shared.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PROF_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "idProfesor")
    private String idProfesor;
    @OneToOne
    @JoinColumn(name="idPersona")
    private PersonaEntity persona;
    @Column(name="comentarios")
    private String coments;
    @Column(name="rama")
    private String branch;


    public ProfesorEntity(){}
    public ProfesorEntity(ProfesorInputDTO profesorInputDTO, PersonaEntity persona){
        setPersona(persona);
        setComents(profesorInputDTO.getComents());
        setBranch(profesorInputDTO.getBranch());
    }

}
