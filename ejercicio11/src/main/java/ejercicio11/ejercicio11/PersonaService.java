package ejercicio11.ejercicio11;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public PersonaOutputDTO buscaId(Integer id) throws Exception {
        return new PersonaOutputDTO(personaRepo.findById(id).orElseThrow(() -> new Exception("ID no encontrado")));
    }

    @Override
    public List<PersonaOutputDTO> buscaNombre(String nombre) throws Exception {
        List<PersonaEntity> listaPersonaEntity = personaRepo.findAll();
        List<PersonaOutputDTO> listaOutput = new ArrayList<>();
        for (PersonaEntity persona : listaPersonaEntity) {
            if (nombre.equals(persona.getName())) {
                listaOutput.add(new PersonaOutputDTO(persona));
            }

        }
        if (listaOutput.size() == 0) {
            throw new Exception("Nombre no encontrado");
        }
        return listaOutput;
    }

    @Override
    public PersonaOutputDTO removeId(Integer id) throws Exception {
        PersonaEntity persona = personaRepo.findById(id).orElseThrow(() -> new Exception("ID no encontrado"));
        personaRepo.deleteById(id);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO modify(PersonaInputDTO personaInputDTO) throws Exception {
        PersonaEntity persona = personaRepo.findById(personaInputDTO.getId()).orElseThrow(() -> new Exception("ID no encontrado"));
        persona.modificar(personaInputDTO);
        personaRepo.saveAndFlush(persona);
        return new PersonaOutputDTO(persona);
    }


}
