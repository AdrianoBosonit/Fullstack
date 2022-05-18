package back.ejercicioFinal.content.Autobus.infrastructure.dto;

import back.ejercicioFinal.content.Autobus.domain.AutobusEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ReservaDisponibleOutputDto {
    private String ciudadDestino;
    private Date fechaSalida;
    private Float horaSalida;
    private Integer numeroPlazas;

    public ReservaDisponibleOutputDto(AutobusEntity autobusEntity) {
        this.ciudadDestino = autobusEntity.getCiudadDestino();
        this.fechaSalida = autobusEntity.getFechaSalida();
        this.horaSalida = autobusEntity.getHoraSalida();
        this.numeroPlazas = autobusEntity.getReservas().size();
    }
}
