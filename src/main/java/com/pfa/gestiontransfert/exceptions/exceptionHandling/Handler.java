package com.pfa.gestiontransfert.exceptions.exceptionHandling;

import com.pfa.gestiontransfert.exceptions.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZonedDateTime;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleApiRequestException(BaseException e) {
        ResponseException responseException = new ResponseException(e.getHttpStatus().value(), e.getMessage(),
                e.getHttpStatus(), ZonedDateTime.now());

        return new ResponseEntity<>(
                responseException, e.getHttpStatus());
    }
}
