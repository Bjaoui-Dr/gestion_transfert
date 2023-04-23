package com.pfa.gestiontransfert.exceptions;

import org.springframework.http.HttpStatus;

public class LieuException extends BaseException {

    public LieuException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public LieuException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
