package ru.prostostudia.springapi.exceptions;

public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException() {
        super("EmployeeAlreadyAdded");
    }
}
