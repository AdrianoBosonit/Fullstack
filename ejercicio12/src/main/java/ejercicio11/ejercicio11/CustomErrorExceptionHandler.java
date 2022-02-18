package ejercicio11.ejercicio11;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//@ControllerAdvice
// @RestController
@RestControllerAdvice
public class CustomErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<CustomError> handleNotFoundException(NotFoundException ex, CustomError request) {
        CustomError customError = new CustomError(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<CustomError>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<CustomError> handleNotUnprocesableException(UnprocesableException ex, CustomError request) {
        CustomError customError = new CustomError(new Date(), ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        return new ResponseEntity<CustomError>(customError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
