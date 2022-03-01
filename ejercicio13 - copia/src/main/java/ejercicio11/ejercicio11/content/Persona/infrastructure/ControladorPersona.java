package ejercicio11.ejercicio11.content.Persona.infrastructure;

import ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.output.EstudianteAsignaturaOutputDTO;
import ejercicio11.ejercicio11.content.Persona.application.interfaces.IPersona;
import ejercicio11.ejercicio11.content.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio11.ejercicio11.content.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.output.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("persona")
public class ControladorPersona {
    @Autowired
    IPersona personaService;


    @PostMapping("/userAdd")
    public PersonaOutputDTO userAdd(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        return personaService.add(personaInputDTO);
    }

    @GetMapping("/get")
    public List<PersonaOutputDTO> devolverTodo(@RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        return personaService.getAll(outputType);
    }

    @GetMapping("/{id}")
    public PersonaOutputDTO userGetId(@PathVariable String id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        return personaService.buscaId(Integer.parseInt(id), outputType);
    }

    @GetMapping("/usuario/{usuario}")
    public List<PersonaOutputDTO> userGetNombre(@PathVariable String usuario, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) throws Exception {
        return personaService.buscaUsuario(usuario, outputType);
    }


    @DeleteMapping("/{id}")
    public void userRemoveId(@PathVariable String id) throws Exception {
        personaService.removeId(Integer.parseInt(id));
    }

    @PutMapping("/update")
    public PersonaOutputDTO userUpdate(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        return personaService.modify(personaInputDTO);
    }

    @GetMapping("/getProf/{idProfesor}")
    public ResponseEntity<ProfesorOutputDTO> devolverProfesor(@PathVariable String idProfesor) {
        return new RestTemplate().getForEntity("http://localhost:8080/persona/getProf/"+idProfesor,ProfesorOutputDTO.class,personaService.buscaProfesor(idProfesor));
        //return new ResponseEntity<>( personaService.buscaProfesor(idProfesor), HttpStatus.OK);

    }


}
