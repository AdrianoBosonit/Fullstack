package back.ejercicioFinal.shared.kafka.Deserializer;

import back.ejercicioFinal.shared.kafka.MessageKafka;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ReservaDeserializer implements Deserializer<MessageKafka> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public MessageKafka deserialize(String s, byte[] reservabytes) {
        ObjectMapper mapper = new ObjectMapper();
        MessageKafka messageKafka = null;
        try {
            messageKafka = mapper.readValue(reservabytes, MessageKafka.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return messageKafka;
    }

    @Override
    public MessageKafka deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }

}

