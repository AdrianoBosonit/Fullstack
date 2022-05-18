package ejercicio21.Persona.infrastructure;

import ejercicio21.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio21.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio21.Persona.application.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class ControladorPersona {
    @Autowired
    PersonaService personaService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/userAdd")
    public PersonaOutputDTO userAdd(@Valid @RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        personaInputDTO.setPassword(bCryptPasswordEncoder.encode(personaInputDTO.getPassword()));
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
