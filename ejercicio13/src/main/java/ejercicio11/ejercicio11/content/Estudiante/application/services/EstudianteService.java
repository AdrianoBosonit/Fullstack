package ejercicio11.ejercicio11.content.Estudiante.application.services;

import ejercicio11.ejercicio11.content.Asignatura.domain.EstudianteAsignaturaEntity;
import ejercicio11.ejercicio11.content.Asignatura.repository.EstudianteAsignaturaRepo;
import ejercicio11.ejercicio11.content.Estudiante.application.interfaces.IEstudiante;
import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.input.EstudianteInputDTO;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.output.EstudianteOutputFullDTO;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.output.EstudianteOutputSimpleDTO;
import ejercicio11.ejercicio11.content.Estudiante.repository.EstudianteRepo;
import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Persona.repository.PersonaRepo;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import ejercicio11.ejercicio11.content.Profesor.repository.ProfesorRepo;
import ejercicio11.ejercicio11.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteService implements IEstudiante {
    @Autowired
    EstudianteRepo estudianteRepo;

    @Autowired
    PersonaRepo personaRepo;

    @Autowired
    ProfesorRepo profesorRepo;

    @Autowired
    EstudianteAsignaturaRepo estudianteAsignaturaRepo;

    @Override
    public EstudianteOutputSimpleDTO getId(String id, String outputType) {
        return getEstudianteOutputDto(estudianteRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado")), outputType);
    }

    @Override
    public EstudianteOutputSimpleDTO anadirEstudiante(EstudianteInputDTO estudianteInputDTO) {
        PersonaEntity persona = null;
        ProfesorEntity profesor = null;
        List<EstudianteAsignaturaEntity> estudios = new ArrayList<>();
        if (Optional.ofNullable(estudianteInputDTO.getIdPersona()).isPresent()) {
            persona = personaRepo.findById(estudianteInputDTO.getIdPersona()).orElseThrow(() -> new NotFoundException("ID de persona no encontrado"));
            if (Optional.ofNullable(profesorRepo.findByPersona(persona)).get().isPresent()) {
                throw new NotFoundException("La persona esta asociada a un profesor");
            }
        }
        if (Optional.ofNullable(estudianteInputDTO.getIdProfesor()).isPresent()) {
            profesor = profesorRepo.findById(estudianteInputDTO.getIdProfesor()).orElseThrow(() -> new NotFoundException("ID de de profesor no encontrado"));
        }
        if (Optional.ofNullable(estudianteInputDTO.getEstudios()).isPresent()) {
            estudios = estudianteInputDTO.getEstudios().stream().map(p -> estudianteAsignaturaRepo.findById(p).orElseThrow(() -> new NotFoundException("ID de de asignatura no encontrado"))).collect(Collectors.toList());
        }
        return new EstudianteOutputSimpleDTO(estudianteRepo.saveAndFlush(new EstudianteEntity(estudianteInputDTO, persona, profesor, estudios)));
    }

    @Override
    public List<EstudianteOutputSimpleDTO> getAll() {
        return estudianteRepo.findAll().stream().map(p -> new EstudianteOutputSimpleDTO(p)).collect(Collectors.toList());
    }

    @Override
    public EstudianteOutputSimpleDTO removeId(String id) {
        EstudianteEntity estudiante = estudianteRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado"));
        estudianteRepo.deleteById(id);
        return new EstudianteOutputSimpleDTO(estudiante);
    }

    @Override
    public EstudianteOutputSimpleDTO modify(EstudianteInputDTO estudianteInputDTO) {
        return new EstudianteOutputSimpleDTO(estudianteRepo.saveAndFlush(modificar(estudianteInputDTO,
                estudianteRepo.findById(estudianteInputDTO.getIdEstudiante()).orElseThrow(() ->
                        new NotFoundException("ID de estudiante no encontrado")))));
    }

    private EstudianteOutputSimpleDTO getEstudianteOutputDto(EstudianteEntity estudiante, String outputType) {
        if (outputType.equals("full")) {
            return new EstudianteOutputFullDTO(estudiante);
        } else {
            return new EstudianteOutputSimpleDTO(estudiante);
        }

    }

    private EstudianteEntity modificar(EstudianteInputDTO estudianteInputDTO, EstudianteEntity estudiante) {
        if (Optional.ofNullable(estudianteInputDTO.getIdPersona()).isPresent()) {
            PersonaEntity persona = personaRepo.findById(estudianteInputDTO.getIdPersona()).orElseThrow(() -> new NotFoundException("ID de persona no encontrado"));
            if (Optional.ofNullable(profesorRepo.findByPersona(persona)).get().isPresent()) {
                throw new NotFoundException("La persona esta asociada a un profesor");
            }
            estudiante.setPersona(persona);
        }
        if (Optional.ofNullable(estudianteInputDTO.getIdProfesor()).isPresent()) {
            estudiante.setProfesor(profesorRepo.findById(estudianteInputDTO.getIdProfesor()).orElseThrow(() -> new NotFoundException("ID de de profesor no encontrado")));
        }
        estudiante.setComents(Optional.ofNullable(estudianteInputDTO.getComents()).orElse(estudiante.getComents()));
        estudiante.setNumHoursWeek(Optional.ofNullable(estudianteInputDTO.getNumHoursWeek()).orElse(estudiante.getNumHoursWeek()));
        estudiante.setBranch(Optional.ofNullable(estudianteInputDTO.getBranch()).orElse(estudiante.getBranch()));
        if (Optional.ofNullable(estudianteInputDTO.getEstudios()).isPresent()) {
            estudiante.setEstudios(estudianteInputDTO.getEstudios().stream().map(p -> estudianteAsignaturaRepo.findById(p).orElseThrow(() -> new NotFoundException("ID de de asignatura no encontrado"))).collect(Collectors.toList()));
        }
        return estudiante;
    }

    @Override
    public EstudianteOutputSimpleDTO insertaAsignaturas(List<String> estudios, String id) {
        EstudianteEntity estudiante = estudianteRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado"));
        if (Optional.ofNullable(estudios).isPresent()) {
            for (String p : estudios) {
                EstudianteAsignaturaEntity asignatura =estudianteAsignaturaRepo.findById(p).orElseThrow(() -> new NotFoundException("ID de de asignatura no encontrado"));
                asignatura.setEstudiante(estudiante);
                estudianteAsignaturaRepo.saveAndFlush(asignatura);
            }
        }

        return new EstudianteOutputSimpleDTO(estudianteRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado")));
    }

    @Override
    public EstudianteOutputSimpleDTO eliminarAsignaturas(List<String> estudios, String id) {
        if (Optional.ofNullable(estudios).isPresent()) {
            for (String p : estudios) {
                EstudianteAsignaturaEntity asignatura =estudianteAsignaturaRepo.findById(p).orElseThrow(() -> new NotFoundException("ID de de asignatura no encontrado"));
                asignatura.setEstudiante(null);
                estudianteAsignaturaRepo.saveAndFlush(asignatura);
            }
        }

        return new EstudianteOutputSimpleDTO(estudianteRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado")));
    }
}
