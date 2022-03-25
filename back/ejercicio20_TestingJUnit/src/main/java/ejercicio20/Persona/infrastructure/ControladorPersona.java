package ejercicio20.Persona.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import ejercicio20.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio20.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio20.Persona.application.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ControladorPersona {
    @Autowired
    PersonaService personaService;

    ObjectMapper object = new ObjectMapper();

    public static final String GREATER_THAN = "greater";
    public static final String LESS_THAN = "less";
    public static final String NAME = "name";
    public static final String USER = "user";


    @PostMapping("/userAdd")
    public PersonaOutputDTO userAdd(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        return personaService.anadirPersona(personaInputDTO);
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

    @PutMapping("/update/{id}")
    public PersonaOutputDTO userUpdate(@RequestBody PersonaInputDTO personaInputDTO, @PathVariable String id) throws Exception {
        return personaService.modify(personaInputDTO, Integer.parseInt(id));
    }

}
