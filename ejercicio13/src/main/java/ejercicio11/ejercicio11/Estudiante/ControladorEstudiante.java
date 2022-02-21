package ejercicio11.ejercicio11.Estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class ControladorEstudiante {

    @Autowired
    IEstudiante estudianteService;

    @GetMapping("/{id}")
    public EstudianteOutputDto getId(@PathVariable String id, @RequestParam(value = "outputType",defaultValue = "simple") String outputType) {
        System.out.println("Hola");
        return estudianteService.getId(id, outputType);
    }

    @PostMapping("/add")
    public EstudianteOutputDto add(@RequestBody @Valid EstudianteInputDTO estudianteInputDTO){
        System.out.println("Hola");
        return estudianteService.anadirEstudiante(estudianteInputDTO);
    }

    @GetMapping("/get")
    public List<EstudianteOutputDto> devolverTodo() {
        return estudianteService.getAll();
    }


    @DeleteMapping("/{id}")
    public void userRemoveId(@PathVariable String id) {
        estudianteService.removeId(id);
    }

    @PutMapping("/userUpdate")
    public EstudianteOutputDto userUpdate(@RequestBody EstudianteInputDTO estudianteInputDTO) throws Exception {
        return estudianteService.modify(estudianteInputDTO);
    }

}
