package ejercicio11.ejercicio11.content.Persona.application.interfaces;

import ejercicio11.ejercicio11.content.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio11.ejercicio11.content.Persona.infrastructure.dto.output.PersonaOutputDTO;

import java.util.List;

public interface IPersona {
    public PersonaOutputDTO add(PersonaInputDTO persona) throws Exception;

    public List<PersonaOutputDTO> getAll(String outputType);

    public PersonaOutputDTO buscaId(Integer id,String outputType);

    public List<PersonaOutputDTO> buscaUsuario(String nombre,String outputType) throws Exception;

    public PersonaOutputDTO removeId(Integer id) throws Exception;

    public PersonaOutputDTO modify(PersonaInputDTO personaInputDTO) throws Exception;

}
