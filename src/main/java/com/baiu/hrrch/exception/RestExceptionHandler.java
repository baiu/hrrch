package com.baiu.hrrch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ACCESS_DENIED_MSG = "У вас нет прав на выполнение данного действия. Проверьте свои РОЛИ в системе";

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ACCESS_DENIED_MSG);
    }

    @ExceptionHandler(CatalogAccessDeniedException.class)
    public ResponseEntity<Object> handleCatalogAccessDeniedException(CatalogAccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(CustomInternalServerException.class)
    protected ResponseEntity<Object> handleCustomInternalServerException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<Object> handleNotAuthorizedException(NotAuthorizedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
