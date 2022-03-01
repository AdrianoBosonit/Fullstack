package ejercicio11.ejercicio11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ejercicio11Application {

    public static void main(String[] args) {
        SpringApplication.run(Ejercicio11Application.class, args);
    }

}
