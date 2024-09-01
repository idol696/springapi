package ru.prostostudia.springapi;

import java.util.List;
import java.util.Map;

public interface DepartmentServiceInterface
{
    int ALL_DEPARTMENTS = -1;
    Map<String, Employee> getEmployeesInDepartment(int department);
    Employee getSalaryMin(int department);
    Employee getSalaryMax(int department);
    Map<Integer, List<Employee>> getEmployeesGroupByDepartment();
}
