package back.ejercicioFinal.shared.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageTopic(String topic, MessageKafka messageKafka) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, messageKafka);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Reserva enviada al topico " + topic + " with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.err.println("Mensaje no enviado");
            }
        });
    }
}
