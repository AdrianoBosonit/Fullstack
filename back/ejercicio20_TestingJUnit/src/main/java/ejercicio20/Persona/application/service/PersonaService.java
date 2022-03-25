package ejercicio20.Persona.application.service;

import ejercicio20.Persona.application.interfaces.IPersona;
import ejercicio20.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio20.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio20.Persona.domain.PersonaEntity;
import ejercicio20.Persona.repository.PersonaRepo;
import ejercicio20.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService implements IPersona {

    @Autowired
    PersonaRepo personaRepo;

    public PersonaOutputDTO anadirPersona(PersonaInputDTO persona) throws Exception {
        PersonaEntity personaEnt = new PersonaEntity(persona);
        personaRepo.save(personaEnt);
        return new PersonaOutputDTO(personaEnt);
    }

    @Override
    public List<PersonaOutputDTO> getAll() {
        List<PersonaOutputDTO> listaOutput = new ArrayList<>();
        List<PersonaEntity> listaPersonaEntity = personaRepo.findAll();
        for (PersonaEntity persona : listaPersonaEntity) {
            listaOutput.add(new PersonaOutputDTO(persona));
        }
        return listaOutput;
    }

    @Override
    public PersonaOutputDTO buscaId(Integer id) {
        return new PersonaOutputDTO(personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado")));
    }

    @Override
    public List<PersonaOutputDTO> buscaNombre(String usuario) throws Exception {
        List<PersonaEntity> listaPersonaEntity = personaRepo.findByUsuario(usuario);
        List<PersonaOutputDTO> listaOutput = new ArrayList<>();
        for (PersonaEntity persona : listaPersonaEntity) {
            listaOutput.add(new PersonaOutputDTO(persona));
        }
        return listaOutput;
    }

    @Override
    public void removeId(Integer id) throws Exception {
        PersonaEntity persona = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        personaRepo.deleteById(id);
    }

    @Override
    public PersonaOutputDTO modify(PersonaInputDTO personaInputDTO, Integer id) throws Exception {
        PersonaEntity persona = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        persona.modificar(personaInputDTO);
        personaRepo.save(persona);
        return new PersonaOutputDTO(persona);


    }


}