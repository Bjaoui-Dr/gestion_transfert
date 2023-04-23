package com.pfa.gestiontransfert.exceptions.exceptionHandling;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ResponseException {
    private int code;
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;
}
