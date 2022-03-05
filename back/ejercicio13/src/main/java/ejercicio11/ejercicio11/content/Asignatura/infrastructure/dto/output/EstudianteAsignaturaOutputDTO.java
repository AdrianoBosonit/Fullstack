package ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.output;

import ejercicio11.ejercicio11.content.Asignatura.domain.EstudianteAsignaturaEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Data
@Component
public class EstudianteAsignaturaOutputDTO {
    private String idAsignatura;
    String idProfesor;
    String idEstudiante;
    String asignatura;
    String comentarios;
    Date initialDate;
    Date finishDate;

    public EstudianteAsignaturaOutputDTO() {
    }

    public EstudianteAsignaturaOutputDTO(EstudianteAsignaturaEntity estudianteAsignatura) {
        setIdAsignatura(estudianteAsignatura.getIdAsignatura());
        if (Optional.ofNullable(estudianteAsignatura.getEstudiante()).isPresent()) {
            setIdEstudiante(estudianteAsignatura.getEstudiante().getIdEstudiante());
        }
        if (Optional.ofNullable(estudianteAsignatura.getProfesor()).isPresent()) {
            setIdProfesor(estudianteAsignatura.getProfesor().getIdProfesor());
        }
        setAsignatura(estudianteAsignatura.getAsignatura());
        setComentarios(estudianteAsignatura.getComentarios());
        setInitialDate(estudianteAsignatura.getInitialDate());
        setFinishDate(estudianteAsignatura.getFinishDate());
    }

}
