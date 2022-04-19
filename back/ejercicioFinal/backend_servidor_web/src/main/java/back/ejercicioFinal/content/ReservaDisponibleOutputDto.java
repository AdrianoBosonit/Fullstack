package back.ejercicioFinal.content;

import lombok.Data;

import java.util.Date;

@Data
public class ReservaDisponibleOutputDto {
    private String ciudadDestino;
    private Date fechaSalida;
    private Float horaSalida;
    private Integer numeroPlazas;

}
