package ejercicio22.ejercicio22;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Ejercicio22Application {

    public static void main(String[] args) {
        SpringApplication.run(Ejercicio22Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            kafkaTemplate.send("ejercicio22", "hello kafka :)");
        };
    }
}
