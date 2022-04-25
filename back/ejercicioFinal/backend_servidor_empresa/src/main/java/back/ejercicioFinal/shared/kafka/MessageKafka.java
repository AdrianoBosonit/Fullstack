package back.ejercicioFinal.shared.kafka;

import back.ejercicioFinal.content.Reserva.ReservaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageKafka {
    String grupo;
    ReservaEntity reservaEntity;
}
