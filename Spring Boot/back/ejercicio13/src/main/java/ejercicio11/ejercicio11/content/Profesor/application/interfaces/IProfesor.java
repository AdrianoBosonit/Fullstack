package ejercicio11.ejercicio11.content.Profesor.application.interfaces;

import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.input.ProfesorInputDTO;
import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.output.ProfesorOutputDTO;

import java.util.List;

public interface IProfesor {
    ProfesorOutputDTO getId(String id);
    ProfesorOutputDTO add(ProfesorInputDTO profesorInputDTO);
    public List<ProfesorOutputDTO> getAll();
    public ProfesorOutputDTO removeId(String id);
   public ProfesorOutputDTO modify(ProfesorInputDTO profesorInputDTO);
}
