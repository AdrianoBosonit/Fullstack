package ejercicio11.ejercicio11;

import java.util.List;

public interface IPersona {
    public void anadirPersona(PersonaInputDTO persona) throws Exception;
    List<PersonaOutputDTO> getAll();
    PersonaOutputDTO buscaId(Integer id) throws Exception;
    List<PersonaOutputDTO> buscaNombre(String nombre) throws Exception;

}
