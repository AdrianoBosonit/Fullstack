package ejercicio11.ejercicio11.Persona.application.service;

import ejercicio11.ejercicio11.NotFoundException;
import ejercicio11.ejercicio11.Persona.application.interfaces.IPersona;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio11.ejercicio11.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.Persona.repository.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersona {
    @Autowired
    PersonaRepo personaRepo;

    public PersonaOutputDTO anadirPersona(PersonaInputDTO persona) throws Exception {
        PersonaEntity personaEnt = new PersonaEntity(persona);
        personaRepo.saveAndFlush(personaEnt);
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
    public PersonaOutputDTO removeId(Integer id) throws Exception {
        PersonaEntity persona = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        personaRepo.deleteById(id);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO modify(PersonaInputDTO personaInputDTO) throws Exception {
        PersonaEntity persona = personaRepo.findById(personaInputDTO.getId()).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        persona.modificar(personaInputDTO);
        personaRepo.saveAndFlush(persona);
        return new PersonaOutputDTO(persona);
    }


}
