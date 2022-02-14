package ejercicio6.ejercicio6_avanzando_con_los_controladores;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@SpringBootApplication
public class Ejercicio6AvanzandoConLosControladoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ejercicio6AvanzandoConLosControladoresApplication.class, args);
    }

    @Bean
    ArrayList<PersonaService> getPersonas() {
        ArrayList<PersonaService> personas = new ArrayList<>();
        return personas;
    }

}
