package ejercicio6.ejercicio6_avanzando_con_los_controladores;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("persona")
public class ControladorRead {
    ObjectMapper object = new ObjectMapper();

    @Autowired
    ArrayList<PersonaService> personas;

    @GetMapping("/{id}")
    public Persona userGetId(@PathVariable String id) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getPersona().getId() == Integer.parseInt(id)) {
                return personas.get(i).getPersona();
            }
        }
        return null;
    }

    @GetMapping("/nombre/{nombre}")
    public ArrayList<Persona> userGetNombre(@PathVariable String nombre) {
        ArrayList<Persona> personasCoincidentes = new ArrayList<>();
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getPersona().getNombre().equals(nombre)) {
                personasCoincidentes.add(personas.get(i).getPersona());
            }
        }
        return personasCoincidentes;
    }
}
