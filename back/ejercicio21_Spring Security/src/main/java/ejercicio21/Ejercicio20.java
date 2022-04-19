package ejercicio21;

import ejercicio21.Persona.application.service.PersonaService;
import ejercicio21.Persona.domain.PersonaEntity;
import ejercicio21.Persona.infrastructure.dto.input.PersonaInputDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;


@SpringBootApplication
public class Ejercicio20 {

    public static void main(String[] args) {SpringApplication.run(Ejercicio20.class, args);}

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner run(PersonaService personaService){

        return args -> {
            personaService.anadirPersona(new PersonaInputDTO("Johnny", "1234"
                    , "Ruf", "Mar", "r@gmail.com", "r@gmail.com", "Jaén"
                    , true, new Date(), "imgurl", new Date(), true));

            personaService.anadirPersona(new PersonaInputDTO( "Manuel", "1234"
                    , "Ruf", "Mar", "r@gmail.com", "r@gmail.com", "Jaén"
                    , true, new Date(), "imgurl", new Date(), false));
        };

    }
}
