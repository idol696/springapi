package ru.prostostudia.springapi;

import java.util.Map;

public interface EmployeeServiceInterface {
    Map<String, Employee> getEmployees();
    Employee addEmployee(String firstName, String lastName);
    void addEmployee(String firstName, String lastName, int department, double salary);
    Employee deleteEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
}