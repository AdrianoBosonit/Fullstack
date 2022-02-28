package ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.input;

import lombok.Data;

@Data
public class ProfesorInputDTO {
    private String idProfesor;
    private Integer idPersona;
//    @NotNull
    private String coments;
    //ProfesorEntity profesor;
//    @NotNull
    private String branch;
}
