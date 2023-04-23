package com.pfa.gestiontransfert.exceptions;

import org.springframework.http.HttpStatus;

public class TrajetException extends BaseException {
    public TrajetException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public TrajetException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
