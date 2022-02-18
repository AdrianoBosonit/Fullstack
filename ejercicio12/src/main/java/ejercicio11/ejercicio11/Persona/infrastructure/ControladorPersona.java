package ejercicio11.ejercicio11.Persona.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio11.ejercicio11.Persona.application.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorPersona {
    @Autowired
    PersonaService personaService;

    ObjectMapper object = new ObjectMapper();

    @PostMapping("/userAdd")
    public PersonaOutputDTO userAdd(@RequestBody String persona) throws Exception {
        return personaService.anadirPersona(object.readValue(persona, PersonaInputDTO.class));
    }

    @GetMapping("/get")
    public List<PersonaOutputDTO> devolverTodo() {
        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public PersonaOutputDTO userGetId(@PathVariable String id) {
        return personaService.buscaId(Integer.parseInt(id));
    }

    @GetMapping("/nombre/{nombre}")
    public List<PersonaOutputDTO> userGetNombre(@PathVariable String nombre) throws Exception {
        return personaService.buscaNombre(nombre);
    }


    @DeleteMapping("/{id}")
    public void userRemoveId(@PathVariable String id) throws Exception {
        personaService.removeId(Integer.parseInt(id));
    }

    @PutMapping("/userUpdate")
    public PersonaOutputDTO userUpdate(@RequestBody String persona) throws Exception {
        return personaService.modify(object.readValue(persona, PersonaInputDTO.class));
    }

}
