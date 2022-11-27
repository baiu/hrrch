package com.baiu.hrrch.exception;

public class NotAuthorizedException extends RuntimeException {

    private static final String MESSAGE = "Извините, вы авторизованы для выполнения данной операции";

    public NotAuthorizedException() {
        super(MESSAGE);
    }

    public NotAuthorizedException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
