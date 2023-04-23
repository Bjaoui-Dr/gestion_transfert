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
        return new ResponseEntity<>(
                e.getMessage(), e.getHttpStatus());
    }
}
