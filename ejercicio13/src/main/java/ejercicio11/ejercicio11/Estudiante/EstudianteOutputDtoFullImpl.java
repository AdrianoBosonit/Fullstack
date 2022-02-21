package ejercicio11.ejercicio11.Estudiante;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service("full")
@Data
public class EstudianteOutputDtoFullImpl implements EstudianteOutputDto{
    EstudianteOutputFullDTO estudianteOutputFullDTO=new EstudianteOutputFullDTO();

    @Override
    public EstudianteOutputDto getEstudianteOutputDto() {
        return this;
    }

    @Override
    public void setEstudianteOutputDto(EstudianteEntity estudiante) {
        estudianteOutputFullDTO.setEstudiante(estudiante);
    }
}
