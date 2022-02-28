package ejercicio11.ejercicio11.content.Persona.application.service;

import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.output.EstudianteOutputFullDTO;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.output.EstudianteOutputSimpleDTO;
import ejercicio11.ejercicio11.content.Estudiante.repository.EstudianteRepo;
import ejercicio11.ejercicio11.content.Persona.application.interfaces.IPersona;
import ejercicio11.ejercicio11.content.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio11.ejercicio11.content.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio11.ejercicio11.content.Persona.infrastructure.dto.output.PersonaOutputDTOFullEstudiante;
import ejercicio11.ejercicio11.content.Persona.infrastructure.dto.output.PersonaOutputDTOFullProfesor;
import ejercicio11.ejercicio11.content.Persona.repository.PersonaRepo;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import ejercicio11.ejercicio11.content.Profesor.repository.ProfesorRepo;
import ejercicio11.ejercicio11.exceptions.NotFoundException;
import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService implements IPersona {
    @Autowired
    PersonaRepo personaRepo;

    @Autowired
    EstudianteRepo estudianteRepo;

    @Autowired
    ProfesorRepo profesorRepo;

    public PersonaOutputDTO add(PersonaInputDTO persona) throws Exception {
        ProfesorEntity profesor = null;
        EstudianteEntity estudiante = null;
        if (Optional.ofNullable(persona.getIdProfesor()).isPresent()) {
            profesor = profesorRepo.findById(persona.getIdProfesor()).get();
        }
        if (Optional.ofNullable(persona.getIdEstudiante()).isPresent()) {
            estudiante = estudianteRepo.findById(persona.getIdEstudiante()).get();
        }
        return new PersonaOutputDTO(personaRepo.saveAndFlush(new PersonaEntity(persona, profesor, estudiante)));
    }

    @Override
    public List<PersonaOutputDTO> getAll(String outputType) {
        return personaRepo.findAll().stream().map(p -> getPersonaOutputDto(p,outputType)).collect(Collectors.toList());
    }

    @Override
    public PersonaOutputDTO buscaId(Integer id, String outputType) {
        return getPersonaOutputDto(personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID de persona no encontrado")), outputType);
    }

    @Override
    public List<PersonaOutputDTO> buscaUsuario(String usuario,String outputType) {
        return personaRepo.findByUsuario(usuario).stream().map(p -> getPersonaOutputDto(p,outputType)).collect(Collectors.toList());
    }

    @Override
    public PersonaOutputDTO removeId(Integer id) throws Exception {
        PersonaEntity persona = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        personaRepo.deleteById(id);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO modify(PersonaInputDTO personaInputDTO) throws Exception {
        PersonaEntity persona = personaRepo.findById(personaInputDTO.getIdPersona()).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        persona.modificar(personaInputDTO);
        personaRepo.saveAndFlush(persona);
        return new PersonaOutputDTO(persona);
    }

    private PersonaOutputDTO getPersonaOutputDto(PersonaEntity persona, String outputType) {
        if (outputType.equals("full")) {
            Optional<ProfesorEntity> profesor = profesorRepo.findByPersona(persona);
            Optional<EstudianteEntity> estudiante = estudianteRepo.findByPersona(persona);
            if (profesor.isPresent()) {
                return new PersonaOutputDTOFullProfesor(persona, profesor.get());
            }
            if (estudiante.isPresent()) {
                return new PersonaOutputDTOFullEstudiante(persona, estudiante.get());
            }
        }
        return new PersonaOutputDTO(persona);

    }
}
