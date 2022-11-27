package com.baiu.hrrch.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final String MESSAGE = "К сожалению такая запись не найдена.\n" +
            "Проверьте данные и обратитесь к администратору системы.";

    public EntityNotFoundException() {
        super(MESSAGE);
    }

    public EntityNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Class entityClass, Object id) {
        super(String.format("К сожалению %s с идентификатором <%s> не найден.", entityClass.getName(), id));
    }
}
