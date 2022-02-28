package ejercicio11.ejercicio11.content.Asignatura.application.services;

import ejercicio11.ejercicio11.content.Asignatura.application.interfaces.IEstudianteAsignatura;
import ejercicio11.ejercicio11.content.Asignatura.domain.EstudianteAsignaturaEntity;
import ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.input.EstudianteAsignaturaInputDTO;
import ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.output.EstudianteAsignaturaOutputDTO;
import ejercicio11.ejercicio11.content.Asignatura.repository.EstudianteAsignaturaRepo;
import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Estudiante.repository.EstudianteRepo;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import ejercicio11.ejercicio11.content.Profesor.repository.ProfesorRepo;
import ejercicio11.ejercicio11.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteAsignaturaServiceAsignatura implements IEstudianteAsignatura {
    @Autowired
    EstudianteAsignaturaRepo estudianteAsignaturaRepo;

    @Autowired
    ProfesorRepo profesorRepo;

    @Autowired
    EstudianteRepo estudianteRepo;

    @Override
    public EstudianteAsignaturaOutputDTO getId(String id) {
        return new EstudianteAsignaturaOutputDTO(estudianteAsignaturaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado")));
    }

    @Override
    public EstudianteAsignaturaOutputDTO add(EstudianteAsignaturaInputDTO estudianteAsignaturaInputDTO) {
        ProfesorEntity profesor = null;
        EstudianteEntity estudiante = null;
        if (Optional.ofNullable(estudianteAsignaturaInputDTO.getIdProfesor()).isPresent()) {
            profesor = profesorRepo.findById(estudianteAsignaturaInputDTO.getIdProfesor()).orElseThrow(() -> new NotFoundException("ID de profesor no encontrado"));
        }
        if (Optional.ofNullable(estudianteAsignaturaInputDTO.getIdEstudiante()).isPresent()) {
            estudiante = estudianteRepo.findById(estudianteAsignaturaInputDTO.getIdEstudiante()).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado"));
        }
        return new EstudianteAsignaturaOutputDTO(estudianteAsignaturaRepo.saveAndFlush(new EstudianteAsignaturaEntity(estudianteAsignaturaInputDTO, profesor, estudiante)));
    }

    @Override
    public List<EstudianteAsignaturaOutputDTO> getAll() {
        return estudianteAsignaturaRepo.findAll().stream().map(p -> new EstudianteAsignaturaOutputDTO(p)).collect(Collectors.toList());
    }

    @Override
    public EstudianteAsignaturaOutputDTO removeId(String id) {
        EstudianteAsignaturaEntity estudianteAsignatura = estudianteAsignaturaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de asignatura no encontrado"));
        estudianteAsignaturaRepo.deleteById(id);
        return new EstudianteAsignaturaOutputDTO(estudianteAsignatura);
    }

    @Override
    public EstudianteAsignaturaOutputDTO modify(EstudianteAsignaturaInputDTO estudianteAsignaturaInputDTO) {
        return new EstudianteAsignaturaOutputDTO(estudianteAsignaturaRepo.saveAndFlush(modificar(estudianteAsignaturaInputDTO,
                estudianteAsignaturaRepo.findById(estudianteAsignaturaInputDTO.getIdAsignatura()).orElseThrow(() ->
                        new NotFoundException("ID de asignatura no encontrado")))));
    }

    @Override
    public List<EstudianteAsignaturaOutputDTO> buscaEstudiantesAsignatura(String idEstudiante) {
        return estudianteRepo.findById(idEstudiante).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado"))
                .getEstudios().stream().map(p -> new EstudianteAsignaturaOutputDTO(p)).collect(Collectors.toList());
    }

    private EstudianteAsignaturaEntity modificar(EstudianteAsignaturaInputDTO estudianteAsignaturaInputDTO, EstudianteAsignaturaEntity estudianteAsignatura) {
        if (Optional.ofNullable(estudianteAsignaturaInputDTO.getIdEstudiante()).isPresent()) {
            estudianteAsignatura.setEstudiante(Optional.ofNullable(estudianteRepo.
                    findById(estudianteAsignaturaInputDTO.getIdEstudiante()).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado"))).orElse(estudianteAsignatura.getEstudiante()));
        }
        if (Optional.ofNullable(estudianteAsignaturaInputDTO.getIdProfesor()).isPresent()) {
            estudianteAsignatura.setProfesor(Optional.ofNullable(profesorRepo.
                    findById(estudianteAsignaturaInputDTO.getIdProfesor()).orElseThrow(() -> new NotFoundException("ID de profesor no encontrado"))).orElse(estudianteAsignatura.getProfesor()));
        }
        estudianteAsignatura.setAsignatura(Optional.ofNullable(estudianteAsignaturaInputDTO.getAsignatura()).orElse(estudianteAsignatura.getAsignatura()));
        estudianteAsignatura.setComentarios(Optional.ofNullable(estudianteAsignaturaInputDTO.getComentarios()).orElse(estudianteAsignatura.getComentarios()));
        estudianteAsignatura.setInitialDate(Optional.ofNullable(estudianteAsignaturaInputDTO.getInitialDate()).orElse(estudianteAsignatura.getInitialDate()));
        estudianteAsignatura.setFinishDate(Optional.ofNullable(estudianteAsignaturaInputDTO.getFinishDate()).orElse(estudianteAsignatura.getFinishDate()));
        return estudianteAsignatura;
    }


}
