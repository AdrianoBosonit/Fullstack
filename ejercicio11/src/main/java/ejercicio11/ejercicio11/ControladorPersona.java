package ejercicio11.ejercicio11;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public PersonaOutputDTO userGetId(@PathVariable String id) throws Exception {
        return personaService.buscaId(Integer.parseInt(id));
    }

    @GetMapping("/nombre/{nombre}")
    public List<PersonaOutputDTO> userGetNombre(@PathVariable String nombre) throws Exception {
        return personaService.buscaNombre(nombre);
    }


    @DeleteMapping("/{id}")
    public PersonaOutputDTO userRemoveId(@PathVariable String id) throws Exception {
        return personaService.removeId(Integer.parseInt(id));
    }

    @PutMapping("/userUpdate")
    public PersonaOutputDTO userUpdate(@RequestBody String persona) throws Exception {
        return personaService.modify(object.readValue(persona, PersonaInputDTO.class));
    }

}
