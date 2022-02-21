package ejercicio11.ejercicio11.Estudiante;

import ejercicio11.ejercicio11.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.output.PersonaOutputDTO;

import java.util.List;

public interface IEstudiante {
    EstudianteOutputDto getId(String id,String outputType);
    EstudianteOutputDto anadirEstudiante(EstudianteInputDTO estudianteInputDTO);
    public List<EstudianteOutputDto> getAll();
    public EstudianteOutputDto removeId(String id);
    public EstudianteOutputDto modify(EstudianteInputDTO estudianteInputDTO);
}
