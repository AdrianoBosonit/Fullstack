package ejercicio11.ejercicio11.content.Asignatura.application.interfaces;

import ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.input.EstudianteAsignaturaInputDTO;
import ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.output.EstudianteAsignaturaOutputDTO;

import java.util.List;

public interface IEstudianteAsignatura {
    EstudianteAsignaturaOutputDTO getId(String id);

    EstudianteAsignaturaOutputDTO add(EstudianteAsignaturaInputDTO estudianteAsignaturaInputDTO);

    public List<EstudianteAsignaturaOutputDTO> getAll();

    public EstudianteAsignaturaOutputDTO removeId(String id);

    public EstudianteAsignaturaOutputDTO modify(EstudianteAsignaturaInputDTO estudianteAsignaturaInputDTO);

    public List<EstudianteAsignaturaOutputDTO> buscaEstudiantesAsignatura(String idEstudiante);
}
