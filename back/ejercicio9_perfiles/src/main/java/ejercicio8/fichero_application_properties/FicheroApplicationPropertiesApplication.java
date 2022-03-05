package ejercicio8.fichero_application_properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Configuration
public class FicheroApplicationPropertiesApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(FicheroApplicationPropertiesApplication.class, args);
    }

    @Bean
    @Profile({"Perfil1", "default"})
    public Perfiles getP1() {
        return new Perfil1("..perfil1..");
    }

    @Bean
    @Profile("Perfil2")
    public Perfiles getp2() {
        return new Perfil2("..perfil2..");
    }

    String getProfile() {
        if (environment.getActiveProfiles() == null) {
            return "default";
        }
        String[] profiles = environment.getActiveProfiles();
        return profiles.length > 0 ? profiles[0] : "default";

    }
}
