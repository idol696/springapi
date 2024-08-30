package ru.prostostudia.springapi.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("EmployeeNotFound");
    }
}
