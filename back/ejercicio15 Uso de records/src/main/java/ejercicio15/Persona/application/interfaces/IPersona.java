package ejercicio15.Persona.application.interfaces;

import ejercicio15.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio15.Persona.infrastructure.dto.output.PersonaOutputDTO;

import java.util.List;

public interface IPersona {
    public PersonaOutputDTO anadirPersona(PersonaInputDTO persona) throws Exception;

    public List<PersonaOutputDTO> getAll();

    public PersonaOutputDTO buscaId(Integer id);

    public List<PersonaOutputDTO> buscaNombre(String nombre) throws Exception;

    public PersonaOutputDTO removeId(Integer id) throws Exception;

    public PersonaOutputDTO modify(PersonaInputDTO personaInputDTO,Integer id) throws Exception;

}
