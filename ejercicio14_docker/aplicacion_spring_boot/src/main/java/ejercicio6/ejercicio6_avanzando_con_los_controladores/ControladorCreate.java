package ejercicio6.ejercicio6_avanzando_con_los_controladores;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("persona")
public class ControladorCreate {

    ObjectMapper object = new ObjectMapper();
    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    ArrayList<PersonaService> personas;

    @PostMapping()
    public void userAdd(@RequestBody String jsonParam) throws JsonProcessingException {
        PersonaService persona = new PersonaServiceImpl();
        persona.crearPersona(counter.incrementAndGet(), object.readValue(jsonParam, Persona.class));
        personas.add(persona);
        System.out.println(persona.getPersona());
    }
}
