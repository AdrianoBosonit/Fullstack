package ejercicio11.ejercicio11.content.Estudiante.infrastructure;

import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.input.EstudianteInputDTO;
import ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.output.EstudianteOutputSimpleDTO;
import ejercicio11.ejercicio11.content.Estudiante.application.interfaces.IEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class ControladorEstudiante {

    @Autowired
    IEstudiante estudianteService;

    @PostMapping("/add")
    public EstudianteOutputSimpleDTO add(@RequestBody @Valid EstudianteInputDTO estudianteInputDTO) {
        return estudianteService.anadirEstudiante(estudianteInputDTO);

    }

    @GetMapping("/{id}")
    public EstudianteOutputSimpleDTO getId(@PathVariable String id, @RequestParam(value = "outputType",defaultValue = "simple") String outputType) {
        return estudianteService.getId(id, outputType);
    }


    @GetMapping("/get")
    public List<EstudianteOutputSimpleDTO> devolverTodo() {
        return estudianteService.getAll();
    }


    @DeleteMapping("/{id}")
    public void userRemoveId(@PathVariable String id) {
        estudianteService.removeId(id);
    }

    @PutMapping("/update")
    public EstudianteOutputSimpleDTO userUpdate(@RequestBody EstudianteInputDTO estudianteInputDTO) throws Exception {
        return estudianteService.modify(estudianteInputDTO);
    }

    @PutMapping("/inputAs/{id}")
    public EstudianteOutputSimpleDTO inputAs(@PathVariable String id,@RequestBody List<String> lista) throws Exception {
        return estudianteService.insertaAsignaturas(lista,id);
    }

    @DeleteMapping("/deleteAs/{id}")
    public EstudianteOutputSimpleDTO deleteAs(@PathVariable String id,@RequestBody List<String> lista) throws Exception {
        return estudianteService.eliminarAsignaturas(lista,id);
    }

}
