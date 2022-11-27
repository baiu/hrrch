package com.baiu.hrrch.exception;

import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.person.Person;

public class CatalogAccessDeniedException extends RuntimeException {

    private static final String MESSAGE = "Извините, у вас нет доступа к каталогу";

    public CatalogAccessDeniedException() {
        super(MESSAGE);
    }

    public CatalogAccessDeniedException(Person person, Catalog catalog) {
        super(MESSAGE);
    }

    public CatalogAccessDeniedException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public CatalogAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
