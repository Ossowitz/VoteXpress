package ru.iooko.votingapp.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class NotAllowedUpdateException extends ApplicationException {

    private static final String EXCEPTION_UPDATE_RESTRICTION =
            "exception.user.notAllowedUpdateException";

    public NotAllowedUpdateException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, EXCEPTION_UPDATE_RESTRICTION,
                ErrorAttributeOptions.of(MESSAGE));
    }

}