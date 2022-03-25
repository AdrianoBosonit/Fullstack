package ejercicio18.Persona.application.service;

import ejercicio18.Persona.application.interfaces.IPersona;
import ejercicio18.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio18.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio18.Persona.domain.PersonaEntity;
import ejercicio18.Persona.repository.PersonaRepo;
import ejercicio18.exceptions.NotFoundException;
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
        return crearPersonaOutputDTO(personaEnt);
    }

    @Override
    public List<PersonaOutputDTO> getAll() {
        List<PersonaOutputDTO> listaOutput = new ArrayList<>();
        List<PersonaEntity> listaPersonaEntity = personaRepo.findAll();
        for (PersonaEntity persona : listaPersonaEntity) {
            listaOutput.add(crearPersonaOutputDTO(persona));
        }
        return listaOutput;
    }

    @Override
    public PersonaOutputDTO buscaId(Integer id) {
        return crearPersonaOutputDTO(personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado")));
    }

    @Override
    public List<PersonaOutputDTO> buscaNombre(String usuario) throws Exception {
        List<PersonaEntity> listaPersonaEntity = personaRepo.findByUsuario(usuario);
        List<PersonaOutputDTO> listaOutput = new ArrayList<>();
        for (PersonaEntity persona : listaPersonaEntity) {
            listaOutput.add(crearPersonaOutputDTO(persona));
        }
        return listaOutput;
    }

    @Override
    public PersonaOutputDTO removeId(Integer id) throws Exception {
        PersonaEntity persona = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        personaRepo.deleteById(id);
        return crearPersonaOutputDTO(persona);

    }

    @Override
    public PersonaOutputDTO modify(PersonaInputDTO personaInputDTO, Integer id) throws Exception {
        PersonaEntity persona = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado"));
        persona.modificar(personaInputDTO);
        personaRepo.save(persona);
        return crearPersonaOutputDTO(persona);


    }

    @Override
    public List<PersonaOutputDTO> getData(HashMap<String, Object> data, Integer numPag, Integer tamPag) {
        return personaRepo.getData(data,numPag,tamPag).stream().map(persona -> crearPersonaOutputDTO(persona)).collect(Collectors.toList());
    }


    private PersonaOutputDTO crearPersonaOutputDTO(PersonaEntity persona) {
        return new PersonaOutputDTO(persona.getId(), persona.getUsuario(), persona.getPassword(), persona.getName(),
                persona.getSurname(), persona.getCompany_email(), persona.getPersonal_email(), persona.getCity(),
                persona.getActive(), persona.getCreated_date(), persona.getImagen_url(), persona.getTermination_date());
    }


}