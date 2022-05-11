package back.ejercicioFinal.shared.kafka;


import back.ejercicioFinal.content.Reserva.application.interfaces.ReservaService;
import back.ejercicioFinal.shared.kafka.Deserializer.ReservaDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    ReservaDeserializer reservaDeserializer = new ReservaDeserializer();

    @Autowired
    ReservaService reservaService;

    @Value("${message.group.name}")
    String grupo;

    @KafkaListener(topics = "${message.topic.name:kafkatopic}", groupId = "${message.group.name:kafkagroup}")
    public void listenTopic1(MessageKafka messageKafka) {
        if (!messageKafka.getGrupo().equals(grupo)) {
            System.out.println("Reserva recibida desde topic1 " + messageKafka);
            reservaService.add(messageKafka.getReservaEntity());
            System.out.println("Objeto deserializado correctamente");
        }
    }

    @KafkaListener(topics = "${message.topic.name2:kafkatopic}", groupId = "${message.group.name:kafkagroup}")
    public void listenTopic2(MessageKafka messageKafka) {
        if (!messageKafka.getGrupo().equals(grupo)) {
            System.out.println("Reserva recibida desde topic1 " + messageKafka);
            //reservaService.add(messageKafka.getReservaEntity());
            System.out.println("Objeto deserializado correctamente");
        }
    }


}
