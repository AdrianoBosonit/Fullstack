package ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.output;

import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import lombok.Data;

import java.util.Optional;

@Data
public class ProfesorOutputDTO {
    private String idProfesor;
    private Integer idPersona;
    private String coments;
    private String branch;

    public ProfesorOutputDTO() {
    }

    public ProfesorOutputDTO(ProfesorEntity profesor) {
        setIdProfesor(profesor.getIdProfesor());
        if (Optional.ofNullable(profesor.getPersona()).isPresent()) {
            setIdPersona(profesor.getPersona().getIdPersona());
        }
        setComents(profesor.getComents());
        setBranch(profesor.getBranch());
    }

    public void setProfesor(ProfesorEntity profesor) {
        setIdProfesor(profesor.getIdProfesor());
        if (Optional.ofNullable(profesor.getPersona()).isPresent()) {
            setIdPersona(profesor.getPersona().getIdPersona());
        }

    }
}
