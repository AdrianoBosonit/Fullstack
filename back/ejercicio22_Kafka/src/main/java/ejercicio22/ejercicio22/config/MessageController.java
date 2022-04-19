package ejercicio22.ejercicio22.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private KafkaTemplate<String,String> kafkaTemplate;


    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/sendMessage")
    public void publish(@RequestBody MessageRequest request){
        System.out.println(request);
        kafkaTemplate.send("ejercicio22",request.message());
    }
}
