package ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.output;

import ejercicio11.ejercicio11.content.Asignatura.domain.EstudianteAsignaturaEntity;
import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Data
@Component
public class EstudianteOutputSimpleDTO {
    private String idEstudiante;
    private Integer idPersona;
    private Integer numHoursWeek;
    private String coments;
    private String idProfesor;
    private String branch;
    private List<EstudianteAsignaturaEntity> estudios;

    public EstudianteOutputSimpleDTO() {
    }

    public EstudianteOutputSimpleDTO(EstudianteEntity estudiante) {
        setIdEstudiante(estudiante.getIdEstudiante());
        if (Optional.ofNullable(estudiante.getPersona()).isPresent()) {
            setIdPersona(estudiante.getPersona().getIdPersona());
        }
        if (Optional.ofNullable(estudiante.getProfesor()).isPresent()) {
            setIdProfesor(estudiante.getProfesor().getIdProfesor());
        }
        setNumHoursWeek(estudiante.getNumHoursWeek());
        setComents(estudiante.getComents());
        setBranch(estudiante.getBranch());
        setEstudios(estudiante.getEstudios());
    }

}
