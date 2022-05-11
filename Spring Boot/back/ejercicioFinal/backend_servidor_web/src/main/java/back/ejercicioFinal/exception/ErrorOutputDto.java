package back.ejercicioFinal.exception;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorOutputDto {
    private Integer httpCode;
    private String msgError;
    private String tipo;
    private Date fecha;

    public ErrorOutputDto(Integer httpCode, String msgError, String tipo, Date fecha) {
        super();
        this.httpCode = httpCode;
        this.msgError = msgError;
        this.tipo = tipo;
        this.fecha = fecha;
    }
}
