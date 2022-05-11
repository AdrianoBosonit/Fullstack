package ejercicio11.ejercicio11.shared;

import ejercicio11.ejercicio11.content.Profesor.infrastructure.dto.output.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="feign",url="http://localhost:8082/")
public interface IFeign {
    @GetMapping("profesor/{idProfesor}")
    ResponseEntity<ProfesorOutputDTO> call(@PathVariable("idProfesor") String idProfesor);
}
