package ejercicio11.ejercicio11.content.Profesor.infrastructure;

import ejercicio11.ejercicio11.content.Profesor.application.interfaces.IProfesor;
import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.input.ProfesorInputDTO;
import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.output.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ControladorProfesor {

    @Autowired
    IProfesor profesorService;

    @PostMapping("/add")
    public ProfesorOutputDTO add(@RequestBody @Valid ProfesorInputDTO profesorInputDTO) {
        return profesorService.add(profesorInputDTO);

    }

    @GetMapping("/{id}")
    public ProfesorOutputDTO getId(@PathVariable String id) {
        return profesorService.getId(id);
    }


    @GetMapping("/get")
    public List<ProfesorOutputDTO> devolverTodo() {
        return profesorService.getAll();
    }


    @DeleteMapping("/{id}")
    public void userRemoveId(@PathVariable String id) {
        profesorService.removeId(id);
    }

    @PutMapping("/update")
    public ProfesorOutputDTO userUpdate(@RequestBody ProfesorInputDTO profesorInputDTO) throws Exception {
        return profesorService.modify(profesorInputDTO);
    }

}
