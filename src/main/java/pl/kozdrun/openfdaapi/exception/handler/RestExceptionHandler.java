package pl.kozdrun.openfdaapi.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.kozdrun.openfdaapi.exception.ApplicationException;
import pl.kozdrun.openfdaapi.exception.FdaApiResponseException;
import pl.kozdrun.openfdaapi.model.dto.RestExceptionDto;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FdaApiResponseException.class)
    public ResponseEntity<RestExceptionDto> handleException(FdaApiResponseException exception) {
        return createResponse(exception, "Error while invoking fda api with url: " + exception.getFdaCallUrl());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<RestExceptionDto> handleException(ApplicationException exception) {
        return createResponse(exception, "Application processing exception");
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestExceptionDto> handleException(Throwable exception) {
        return createResponse("Unexpected error occurred", exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private <E extends ApplicationException> ResponseEntity<RestExceptionDto> createResponse(E restException, String title) {
        return createResponse(title, restException.getMessage(), restException.getStatus().value());
    }

    private ResponseEntity<RestExceptionDto> createResponse(String title, String message, int code) {
        RestExceptionDto restExceptionDto = new RestExceptionDto(title, message, code, LocalDateTime.now());
        return new ResponseEntity<>(restExceptionDto, HttpStatus.valueOf(restExceptionDto.getCode()));
    }
}