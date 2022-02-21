package ejercicio11.ejercicio11.Estudiante;

import ejercicio11.ejercicio11.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio11.ejercicio11.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService implements IEstudiante {
    @Autowired
    EstudianteRepo estudianteRepo;

    @Autowired
    @Qualifier("simple")
    EstudianteOutputDto estudianteOutputDtoSimple;

    @Autowired
    @Qualifier("full")
    EstudianteOutputDto estudianteOutputDtoFull;

    @Override
    public EstudianteOutputDto getId(String id, String outputType) {
        return getEstudianteOutputDto(estudianteRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado")));

    }

    @Override
    public EstudianteOutputDto anadirEstudiante(EstudianteInputDTO estudianteInputDTO) {
        EstudianteEntity estudiante = new EstudianteEntity(estudianteInputDTO);
        estudianteRepo.saveAndFlush(estudiante);
        return getEstudianteOutputDto(estudiante);
    }


    @Override
    public List<EstudianteOutputDto> getAll() {
        return null;
    }

    @Override
    public EstudianteOutputDto removeId(String id) {
        return null;
    }

    @Override
    public EstudianteOutputDto modify(EstudianteInputDTO estudianteInputDTO) {
        return null;
    }

    private EstudianteOutputDto getEstudianteOutputDto(EstudianteEntity estudiante, String... outputType) {
        switch (outputType[0]) {
            case "full":
                estudianteOutputDtoFull.setEstudianteOutputDto(estudiante);
                return estudianteOutputDtoFull.getEstudianteOutputDto();
            default:
                estudianteOutputDtoSimple.setEstudianteOutputDto(estudiante);
                return estudianteOutputDtoSimple.getEstudianteOutputDto();
        }
    }
}
