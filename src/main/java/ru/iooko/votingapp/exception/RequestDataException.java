package ru.iooko.votingapp.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class RequestDataException extends ApplicationException {
    public RequestDataException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg, ErrorAttributeOptions.of(MESSAGE));
    }
}