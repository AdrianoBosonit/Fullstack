package ejercicio11.ejercicio11.content.Estudiante.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ejercicio11.ejercicio11.content.Asignatura.domain.EstudianteAsignaturaEntity;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.input.EstudianteInputDTO;
import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import ejercicio11.ejercicio11.shared.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Estudiante")
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @GenericGenerator(
            name = "student_seq",
            strategy = "ejercicio11.ejercicio11.shared.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STU_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "idEstudiante")
    String idEstudiante;

    @OneToOne
    @JoinColumn(name="idPersona")
    private PersonaEntity persona;
    @Column(name="horas_por_semana")
    private Integer numHoursWeek;
    @Column(name="comentarios")
    private String coments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesor")
    ProfesorEntity profesor;
    @Column(name="rama")
    private String branch;
    @JsonIgnore
    @OneToMany(mappedBy="estudiante")
    List<EstudianteAsignaturaEntity> estudios;
    public EstudianteEntity(){}

    public EstudianteEntity(EstudianteInputDTO estudianteInputDTO, PersonaEntity persona,ProfesorEntity profesor,List<EstudianteAsignaturaEntity> estudios){
        setPersona(persona);
        setProfesor(profesor);
        setNumHoursWeek(estudianteInputDTO.getNumHoursWeek());
        setComents(estudianteInputDTO.getComents());
        setBranch(estudianteInputDTO.getBranch());
        setEstudios(estudios);
    }





}
