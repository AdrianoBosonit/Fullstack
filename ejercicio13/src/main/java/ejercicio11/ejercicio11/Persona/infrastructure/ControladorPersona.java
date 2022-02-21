package ejercicio11.ejercicio11.Persona.infrastructure;
import ejercicio11.ejercicio11.Persona.application.interfaces.IPersona;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio11.ejercicio11.Persona.infrastructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class ControladorPersona {
    @Autowired
    IPersona personaService;

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

    @PutMapping("/userUpdate")
    public PersonaOutputDTO userUpdate(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        return personaService.modify(personaInputDTO);
    }

}
