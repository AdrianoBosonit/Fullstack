package back.ejercicioFinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ErrorOutputDtoExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorOutputDto> handleNotFoundException(BadRequestException ex, WebRequest request) {
        ErrorOutputDto errorOutputDto = new ErrorOutputDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase(),new Date());
        return new ResponseEntity<ErrorOutputDto>(errorOutputDto, HttpStatus.BAD_REQUEST);
    }

}
