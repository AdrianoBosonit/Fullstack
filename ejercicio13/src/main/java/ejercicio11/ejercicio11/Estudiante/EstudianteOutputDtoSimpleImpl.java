package ejercicio11.ejercicio11.Estudiante;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service("simple")
@Data
public class EstudianteOutputDtoSimpleImpl implements EstudianteOutputDto{
    EstudianteOutputSimpleDTO estudianteOutputSimpleDTO=new EstudianteOutputSimpleDTO();

    @Override
    public EstudianteOutputDto getEstudianteOutputDto() {
        return this;
    }

    @Override
    public void setEstudianteOutputDto(EstudianteEntity estudiante) {
        estudianteOutputSimpleDTO.setEstudiante(estudiante);
    }
}
