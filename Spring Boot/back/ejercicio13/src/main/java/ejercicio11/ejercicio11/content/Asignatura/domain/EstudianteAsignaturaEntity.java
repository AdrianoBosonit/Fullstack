
package ejercicio11.ejercicio11.content.Asignatura.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.input.EstudianteAsignaturaInputDTO;
import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.input.EstudianteInputDTO;
import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import ejercicio11.ejercicio11.shared.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Alumnos_Estudios")
@Data
public class EstudianteAsignaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alu_est_seq")
    @GenericGenerator(
            name = "alu_est_seq",
            strategy = "ejercicio11.ejercicio11.shared.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ALU_AS_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "id_asignatura")
    private String idAsignatura;
    @ManyToOne
    @JoinColumn(name = "idProfesor")
    ProfesorEntity profesor;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEstudiante")
    EstudianteEntity estudiante;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comentarios;
    @Column(name = "initial_date")
    Date initialDate;
    @Column(name = "finish_date")
    Date finishDate;

    public  EstudianteAsignaturaEntity(){}
    public EstudianteAsignaturaEntity(EstudianteAsignaturaInputDTO estudianteAsignaturaInputDTO, ProfesorEntity profesor,EstudianteEntity estudiante){
        setEstudiante(estudiante);
        setProfesor(profesor);
        setAsignatura(estudianteAsignaturaInputDTO.getAsignatura());
        setComentarios(estudianteAsignaturaInputDTO.getComentarios());
        setInitialDate(estudianteAsignaturaInputDTO.getInitialDate());
        setFinishDate(estudianteAsignaturaInputDTO.getFinishDate());

    }



}
