package ejercicio11.ejercicio11.content.Asignatura.infrastructure;

import ejercicio11.ejercicio11.content.Asignatura.application.interfaces.IEstudianteAsignatura;
import ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.input.EstudianteAsignaturaInputDTO;
import ejercicio11.ejercicio11.content.Asignatura.infrastructure.dto.output.EstudianteAsignaturaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class ControladorEstudianteAsignatura {

    @Autowired
    IEstudianteAsignatura estudianteAsignaturaService;

    @PostMapping("/add")
    public EstudianteAsignaturaOutputDTO add(@RequestBody @Valid EstudianteAsignaturaInputDTO estudianteAsignaturaInputDTO) {
        return estudianteAsignaturaService.add(estudianteAsignaturaInputDTO);

    }

    @GetMapping("/{id}")
    public EstudianteAsignaturaOutputDTO getId(@PathVariable String id) {
        return estudianteAsignaturaService.getId(id);
    }


    @GetMapping("/get")
    public List<EstudianteAsignaturaOutputDTO> devolverTodo() {
        return estudianteAsignaturaService.getAll();
    }

    @GetMapping("/getAsEst/{idEstudiante}")
    public List<EstudianteAsignaturaOutputDTO> devolverListaAsEstudiante(@PathVariable String idEstudiante) {
        return estudianteAsignaturaService.buscaEstudiantesAsignatura(idEstudiante);
    }


    @DeleteMapping("/{id}")
    public void userRemoveId(@PathVariable String id) {
        estudianteAsignaturaService.removeId(id);
    }

    @PutMapping("/update")
    public EstudianteAsignaturaOutputDTO userUpdate(@RequestBody EstudianteAsignaturaInputDTO estudianteAsignaturaInputDTO) throws Exception {
        return estudianteAsignaturaService.modify(estudianteAsignaturaInputDTO);
    }

}
