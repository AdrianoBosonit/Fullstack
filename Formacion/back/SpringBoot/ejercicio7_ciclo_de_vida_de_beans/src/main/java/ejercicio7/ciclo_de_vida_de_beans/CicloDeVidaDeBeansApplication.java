package ejercicio7.ciclo_de_vida_de_beans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CicloDeVidaDeBeansApplication {

    public static void main(String[] args) {
        SpringApplication.run(CicloDeVidaDeBeansApplication.class, args);
    }

    @Bean
    CommandLineRunner ejecutame() {
        return p -> {
            System.out.println("Hola desde la clase secundaria");
        };

    }

    @Bean
    MiClase3 getMiclase3(){
        MiClase3 clase3=new MiClase3();
        return clase3;

    }
}
