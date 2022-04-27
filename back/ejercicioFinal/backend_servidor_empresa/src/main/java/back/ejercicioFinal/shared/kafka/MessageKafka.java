package back.ejercicioFinal.shared.kafka;

import back.ejercicioFinal.content.Reserva.domain.ReservaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageKafka {
    public enum Accion {
        CREATE("create"), UPDATE("update"), DELETE("delete");
        private String accion;

        private Accion(String accion) {
            this.accion = accion;
        }

        private Accion() {
            this.accion = "create";
        }

        public String getAccion() {
            return accion;
        }

    }

    String grupo;
    Accion accion;
    ReservaEntity reservaEntity;
}
