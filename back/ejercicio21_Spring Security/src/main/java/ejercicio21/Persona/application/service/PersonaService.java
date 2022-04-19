package ejercicio21.Persona.application.service;

import ejercicio21.Persona.application.interfaces.IPersona;
import ejercicio21.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio21.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio21.Persona.domain.PersonaEntity;
import ejercicio21.Persona.repository.PersonaRepo;
import ejercicio21.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonaService implements IPersona, UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PersonaRepo personaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<PersonaEntity> listPersona = personaRepo.findByUsername(username);
        PersonaEntity persona;
        if (listPersona.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            persona = listPersona.get(0);
        }

        Collection<SimpleGrantedAuthority> auothorities = new ArrayList<>();
        if (persona.getAdmin()) {
            auothorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            auothorities.add(new SimpleGrantedAuthority("USER"));
        }

        return new org.springframework.security.core.userdetails.User(persona.getUsername(), persona.getPassword(), auothorities);

    }

    public PersonaOutputDTO anadirPersona(PersonaInputDTO persona) throws Exception {
        PersonaEntity personaEnt = new PersonaEntity(persona);
        personaEnt.setPassword(passwordEncoder.encode(personaEnt.getPassword()));
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
        List<PersonaEntity> listaPersonaEntity = personaRepo.findByUsername(usuario);
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