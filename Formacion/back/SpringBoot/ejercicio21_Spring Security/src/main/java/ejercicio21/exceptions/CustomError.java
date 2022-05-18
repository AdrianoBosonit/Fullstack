package ejercicio21.exceptions;


import lombok.Getter;

import java.util.Date;

@Getter

public class CustomError {
    private Date timestamp;
    private String mensaje;
    private String httpCodeMessage;

    public CustomError(Date timestamp, String mensaje, String httpCodeMessage) {
        super();
        this.timestamp = timestamp;
        this.mensaje = mensaje;
        this.httpCodeMessage = httpCodeMessage;
    }
}
