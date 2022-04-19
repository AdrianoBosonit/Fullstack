package ejercicio22.ejercicio22;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "ejercicio22", groupId = "kafka")
    void listener(String data) {
        System.out.println("Listener received: " + data + " ☺");
    }
}
