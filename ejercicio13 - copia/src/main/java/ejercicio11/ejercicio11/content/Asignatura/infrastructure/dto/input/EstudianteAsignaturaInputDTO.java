package ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.input;

import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class EstudianteAsignaturaInputDTO {
    private String idAsignatura;
    String  idProfesor;
    String idEstudiante;
    String asignatura;
    String comentarios;
    Date initialDate;
    Date finishDate;
}
