package ru.iooko.votingapp.exception;

public class VoteNotAllowedUpdateException extends RuntimeException {

    public VoteNotAllowedUpdateException(String message) {
        super(message);
    }
}
