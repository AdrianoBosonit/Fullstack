package ejercicio6.ejercicio6_avanzando_con_los_controladores;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("persona")
public class ControladorUpdate {
    ObjectMapper object = new ObjectMapper();
    @Autowired
    ArrayList<PersonaService> personas;

    @PutMapping("/{id}")
    public Persona userUpdate(@PathVariable String id, @RequestBody String jsonParam) throws JsonProcessingException {
        PersonaService persona = new PersonaServiceImpl();
        persona.setPersona(object.readValue(jsonParam, Persona.class));
        System.out.println(persona.getPersona());
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getPersona().getId() == Integer.parseInt(id)) {
                personas.get(i).setNombre(Optional.ofNullable(persona.getNombre()).orElse(personas.get(i).getNombre()));
                personas.get(i).setCiudad(Optional.ofNullable(persona.getCiudad()).orElse(personas.get(i).getCiudad()));
                personas.get(i).setEdad(Optional.ofNullable(persona.getEdad()).orElse(personas.get(i).getEdad()));
                for (int j = 0; j < personas.size(); j++) {
                    System.out.println(personas.get(j).getPersona());
                }
                return personas.get(i).getPersona();
            }
        }
        return null;

    }

}
