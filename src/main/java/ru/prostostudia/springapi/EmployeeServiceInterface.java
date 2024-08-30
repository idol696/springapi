package ru.prostostudia.springapi;

import java.util.List;
import java.util.Map;

public interface EmployeeServiceInterface {
    int ALL_DEPARTMENTS = -1;
    Employee addEmployee(String firstName, String lastName);
    void addEmployee(String firstName, String lastName, int department, double salary);
    Employee deleteEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    Map<String, Employee> getEmployees(int department);
    Map<String, Employee> getEmployees();
    Map<Integer, List<Employee>> getEmployeesGroupByDepartment();
    Employee getSalaryMin(int department);
    Employee getSalaryMax(int department);
}