package ejercicio11.ejercicio11.content.Profesor.application.services;

import ejercicio11.ejercicio11.content.Estudiante.repository.EstudianteRepo;
import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Persona.repository.PersonaRepo;
import ejercicio11.ejercicio11.content.Profesor.application.interfaces.IProfesor;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.input.ProfesorInputDTO;
import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.output.ProfesorOutputDTO;
import ejercicio11.ejercicio11.content.Profesor.repository.ProfesorRepo;
import ejercicio11.ejercicio11.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorService implements IProfesor {
    @Autowired
    ProfesorRepo profesorRepo;

    @Autowired
    PersonaRepo personaRepo;

    @Autowired
    EstudianteRepo estudianteRepo;

    @Override
    public ProfesorOutputDTO getId(String id) {
        return new ProfesorOutputDTO(profesorRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado")));
    }

    @Override
    public ProfesorOutputDTO add(ProfesorInputDTO profesorInputDTO) {
        PersonaEntity persona = null;
        if (Optional.ofNullable(profesorInputDTO.getIdPersona()).isPresent()) {
            persona = personaRepo.findById(profesorInputDTO.getIdPersona()).orElseThrow(() -> new NotFoundException("ID de persona no encontrado"));
            if (Optional.ofNullable(estudianteRepo.findByPersona(persona)).get().isPresent()) {
                throw new NotFoundException("La persona esta asociada a un estudiante");
            }
        }
        return new ProfesorOutputDTO(profesorRepo.saveAndFlush(new ProfesorEntity(profesorInputDTO, persona)));
    }

    @Override
    public List<ProfesorOutputDTO> getAll() {
        return profesorRepo.findAll().stream().map(p -> new ProfesorOutputDTO(p)).collect(Collectors.toList());
    }

    @Override
    public ProfesorOutputDTO removeId(String id) {
        ProfesorEntity profesor = profesorRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de estudiante no encontrado"));
        profesorRepo.deleteById(id);
        return new ProfesorOutputDTO(profesor);
    }

    @Override
    public ProfesorOutputDTO modify(ProfesorInputDTO profesorInputDTO) {
        return new ProfesorOutputDTO(profesorRepo.saveAndFlush(modificar(profesorInputDTO,
                profesorRepo.findById(profesorInputDTO.getIdProfesor()).orElseThrow(() ->
                        new NotFoundException("ID de estudiante no encontrado")))));
    }


    private ProfesorEntity modificar(ProfesorInputDTO profesorInputDTO, ProfesorEntity profesor) {
        if (Optional.ofNullable(profesorInputDTO.getIdPersona()).isPresent()) {
            PersonaEntity persona = personaRepo.findById(profesorInputDTO.getIdPersona()).orElseThrow(() -> new NotFoundException("ID de persona no encontrado"));
            if (Optional.ofNullable(estudianteRepo.findByPersona(persona)).get().isPresent()) {
                throw new NotFoundException("La persona esta asociada a un estudiante");
            }
            profesor.setPersona(persona);
        }
        profesor.setComents(Optional.ofNullable(profesorInputDTO.getComents()).orElse(profesor.getComents()));
        profesor.setBranch(Optional.ofNullable(profesorInputDTO.getBranch()).orElse(profesor.getBranch()));
        return profesor;
    }

}
