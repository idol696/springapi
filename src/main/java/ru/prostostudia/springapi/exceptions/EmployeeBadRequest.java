package ru.prostostudia.springapi.exceptions;

import org.springframework.http.HttpStatus;

public class EmployeeBadRequest extends RuntimeException {
    public EmployeeBadRequest () {
        super(String.valueOf(HttpStatus.BAD_REQUEST));
    }
}
