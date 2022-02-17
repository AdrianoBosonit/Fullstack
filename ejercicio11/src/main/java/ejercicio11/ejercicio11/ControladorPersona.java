package ejercicio11.ejercicio11;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControladorPersona {
    @Autowired
    PersonaService personaService;

    ObjectMapper object = new ObjectMapper();

    @PostMapping("/userAdd")
    public String userAdd(@RequestBody String persona) throws Exception {
        personaService.anadirPersona(object.readValue(persona, PersonaInputDTO.class));
        return "Se ha creado el usuario correctamente";
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
    public List<PersonaOutputDTO> userGetNombre(@PathVariable String nombre) throws Exception{
        return personaService.buscaNombre(nombre);
    }


    @DeleteMapping("/{id}")
    public String userRemoveId(@PathVariable String id) {
        return "Usuario eliminado";
    }

}
