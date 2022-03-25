package ejercicio16.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//@ControllerAdvice
// @RestController
@RestControllerAdvice
public class CustomErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<CustomError> handleNotFoundException(NotFoundException ex, WebRequest request) {
        CustomError customError = new CustomError(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<CustomError>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<CustomError> handleNotUnprocesableException(UnprocesableException ex, WebRequest request) {
        CustomError customError = new CustomError(new Date(), ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        return new ResponseEntity<CustomError>(customError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public final ResponseEntity<CustomError> handleStorageFileNotFoundException(StorageFileNotFoundException ex, WebRequest request) {
        CustomError exceptionResponse = new CustomError(new Date(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<CustomError>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StorageException.class)
    public final ResponseEntity<CustomError> handleStorageException(StorageException ex, WebRequest request) {
        CustomError exceptionResponse = new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<CustomError>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
