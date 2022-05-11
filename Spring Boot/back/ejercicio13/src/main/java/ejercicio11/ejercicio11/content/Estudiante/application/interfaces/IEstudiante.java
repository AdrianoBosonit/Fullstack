package ejercicio11.ejercicio11.content.Estudiante.application.interfaces;

import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.input.EstudianteInputDTO;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.output.EstudianteOutputSimpleDTO;

import java.util.List;

public interface IEstudiante {
    EstudianteOutputSimpleDTO getId(String id, String outputType);

    EstudianteOutputSimpleDTO anadirEstudiante(EstudianteInputDTO estudianteInputDTO);

    public List<EstudianteOutputSimpleDTO> getAll();

    public EstudianteOutputSimpleDTO removeId(String id);

    public EstudianteOutputSimpleDTO modify(EstudianteInputDTO estudianteInputDTO);

    public EstudianteOutputSimpleDTO insertaAsignaturas(List<String> estudios, String id);

    public EstudianteOutputSimpleDTO eliminarAsignaturas(List<String> estudios, String id);
}
