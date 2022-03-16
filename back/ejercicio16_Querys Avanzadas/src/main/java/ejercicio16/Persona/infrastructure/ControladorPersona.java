package ejercicio16.Persona.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import ejercicio16.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio16.Persona.infrastructure.dto.output.PersonaOutputDTO;
import ejercicio16.Persona.application.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class ControladorPersona {
    @Autowired
    PersonaService personaService;

    ObjectMapper object = new ObjectMapper();

    public static final String GREATER_THAN = "greater";
    public static final String LESS_THAN = "less";
    public static final String NAME = "name";
    public static final String USER = "user";


    @PostMapping("/userAdd")
    public PersonaOutputDTO userAdd(@RequestBody String persona) throws Exception {
        return personaService.anadirPersona(object.readValue(persona, PersonaInputDTO.class));
    }

    @GetMapping("/get")
    public List<PersonaOutputDTO> devolverTodo() {
        return personaService.getAll();
    }

    @GetMapping("/getData")
    public List<PersonaOutputDTO> devolverQuery(@RequestParam(required = false, name = "user") String user,
                                                @RequestParam(value = "name", required = false) String name,
                                                @RequestParam(required = false, value = "surname") String surname,
                                                @RequestParam(required = false, value = "createdDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date createdDate,
                                                @RequestParam(required = false, value = "dateCondition") String dateCondition,
                                                @RequestParam(required = false, value = "orderCondition") String orderCondition,
                                                @RequestParam(value = "numPag") Integer numPag,
                                                @RequestParam(required = false, value = "tamPag") Integer tamPag) {
        if (tamPag == null) {
            tamPag = 10;
        }
        return personaService.getData(createData(user, name, surname, createdDate, dateCondition, orderCondition), numPag, tamPag);
    }

    @GetMapping("/{id}")
    public PersonaOutputDTO userGetId(@PathVariable String id) {
        return personaService.buscaId(Integer.parseInt(id));
    }

    @GetMapping("/nombre/{nombre}")
    public List<PersonaOutputDTO> userGetNombre(@PathVariable String nombre) throws Exception {
        return personaService.buscaNombre(nombre);
    }


    @DeleteMapping("/{id}")
    public void userRemoveId(@PathVariable String id) throws Exception {
        personaService.removeId(Integer.parseInt(id));
    }

    @PutMapping("/update/{id}")
    public PersonaOutputDTO userUpdate(@RequestBody PersonaInputDTO personaInputDTO, @PathVariable String id) throws Exception {
        return personaService.modify(personaInputDTO, Integer.parseInt(id));
    }

    private HashMap<String, Object> createData(String user, String name, String surname, Date createdDate, String dateCondition, String orderCondition) {
        HashMap<String, Object> data = new HashMap<>();
        if (user != null)
            data.put("usuario", user);
        if (name != null)
            data.put("name", name);
        if (surname != null)
            data.put("surname", surname);
        if (dateCondition == null)
            dateCondition = GREATER_THAN;
        if (!dateCondition.equals(GREATER_THAN) && !dateCondition.equals(LESS_THAN))
            dateCondition = GREATER_THAN;
        if (createdDate != null) {
            data.put("created_date", createdDate);
            data.put("dateCondition", dateCondition);
        }
        if (orderCondition == null)
            orderCondition = NAME;
        if (!orderCondition.equals(NAME) && !orderCondition.equals(USER))
            orderCondition = NAME;
        if (orderCondition != null) {
            data.put("orderCondition", orderCondition);
        }

        return data;

    }
}
