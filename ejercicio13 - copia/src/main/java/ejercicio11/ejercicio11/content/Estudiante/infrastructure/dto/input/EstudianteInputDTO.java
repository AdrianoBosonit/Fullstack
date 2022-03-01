package ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.input;

import ejercicio11.ejercicio11.content.Asignatura.domain.EstudianteAsignaturaEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import lombok.Data;

import java.util.List;

@Data
public class EstudianteInputDTO {
    private String idEstudiante;
    private Integer idPersona;
//    @NotNull
    private Integer numHoursWeek;
    private String coments;
    String idProfesor;
//    @NotNull
    private String branch;
    List<String> estudios;
}
