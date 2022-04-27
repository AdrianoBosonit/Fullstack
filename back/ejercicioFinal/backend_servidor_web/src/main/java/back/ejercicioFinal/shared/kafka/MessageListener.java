package back.ejercicioFinal.shared.kafka;


import back.ejercicioFinal.content.Reserva.application.interfaces.ReservaService;
import back.ejercicioFinal.shared.kafka.Deserializer.ReservaDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageListener {

    ReservaDeserializer reservaDeserializer = new ReservaDeserializer();

    Map<String, MessageKafka> map = new HashMap<String, MessageKafka>();

    @Autowired
    ReservaService reservaService;

    @Value("${message.group.name}")
    String grupo;

    @KafkaListener(topics = "${message.topic.name:kafkatopic}", groupId = "${message.group.name:kafkagroup}")
    public void listenTopic1(MessageKafka messageKafka) {
        //if(messageKafka.equals(map.get(messageKafka.reservaInputDto.)))
        if (!messageKafka.getGrupo().equals(grupo)) {
            System.out.println("Reserva recibida desde topic1 " + messageKafka);
            System.out.println("Añadiendo a base de datos...");
            reservaService.add(messageKafka.getReservaEntity());
            System.out.println("Objeto deserializado correctamente");
        }
    }

    @KafkaListener(topics = "${message.topic.name2:kafkatopic}", groupId = "${message.group.name:kafkagroup}")
    public void listenTopic2(MessageKafka messageKafka) {
        if (!messageKafka.getGrupo().equals(grupo)) {
            System.out.println("Reserva recibida desde topic2 " + messageKafka);
            switch (messageKafka.getAccion()) {
                case UPDATE:
                    System.out.println("Actualizando...");
                    reservaService.updateReservaFromEmpresa(messageKafka.getReservaEntity());
                    break;
                case DELETE:
                    System.out.println("Borrando...");
                    reservaService.removeReservaId(messageKafka.getReservaEntity().getIdReserva());
                    break;

            }


            System.out.println("Objeto deserializado correctamente");
        }
    }

}
