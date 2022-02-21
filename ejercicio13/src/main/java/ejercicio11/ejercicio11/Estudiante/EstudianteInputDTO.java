package ejercicio11.ejercicio11.Estudiante;

import com.sun.istack.NotNull;
import ejercicio11.ejercicio11.Persona.domain.PersonaEntity;
import lombok.Data;

@Data
public class EstudianteInputDTO {
    private String idEstudiante;
    private PersonaEntity persona;
//    @NotNull
    private Integer numHoursWeek;
    private String coments;
    //ProfesorEntity profesor;
//    @NotNull
    private String branch;
}
