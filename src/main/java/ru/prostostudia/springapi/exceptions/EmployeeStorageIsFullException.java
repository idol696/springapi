package ru.prostostudia.springapi.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("ArrayIsFull");
    }
}
