package ejercicio6.ejercicio6_avanzando_con_los_controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("persona")
public class ControladorDelete {
    ObjectMapper object = new ObjectMapper();

    @Autowired
    ArrayList<PersonaService> personas;

    @DeleteMapping("/{id}")
    public void userRemoveId(@PathVariable String id) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getPersona().getId() == Integer.parseInt(id)) {
                personas.remove(i);
            }
        }
    }

}
