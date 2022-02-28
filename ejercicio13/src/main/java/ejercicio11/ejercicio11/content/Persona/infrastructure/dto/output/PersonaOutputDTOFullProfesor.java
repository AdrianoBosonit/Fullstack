package ejercicio11.ejercicio11.content.Persona.infrastructure.dto.output;

import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import lombok.Data;

import java.util.Optional;

@Data
public class PersonaOutputDTOFullProfesor extends PersonaOutputDTO {
    private String idProfesor;
    private String coments;
    private String branch;

    public PersonaOutputDTOFullProfesor() {

    }

    public PersonaOutputDTOFullProfesor(PersonaEntity persona, ProfesorEntity profesor) {
        super(persona);
        if (Optional.ofNullable(profesor).isPresent()) {
            setIdProfesor(profesor.getIdProfesor());
            setComents(profesor.getComents());
            setBranch(profesor.getBranch());
        }
    }

}
