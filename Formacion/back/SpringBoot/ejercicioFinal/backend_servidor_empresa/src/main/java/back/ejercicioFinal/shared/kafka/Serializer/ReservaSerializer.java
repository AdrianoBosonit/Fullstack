package back.ejercicioFinal.shared.kafka.Serializer;

import back.ejercicioFinal.shared.kafka.MessageKafka;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ReservaSerializer implements Serializer<MessageKafka> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, MessageKafka messageKafka) {
        byte[] serializedBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serializedBytes = objectMapper.writeValueAsString(messageKafka).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serializedBytes;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, MessageKafka data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }

}
