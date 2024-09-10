package ru.prostostudia.springapi;

import org.springframework.stereotype.Service;
import ru.prostostudia.springapi.exceptions.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements DepartmentServiceInterface {

    private final EmployeeServiceInterface employeeService;

    public DepartmentService (EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    private List<Employee> getEmployeesDepartment(int department) {
        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == department || department == ALL_DEPARTMENTS)
                .toList();
    }

    @Override
    public Map<String, Employee> getEmployeesInDepartment(int department) {
        return employeeService.getEmployees().entrySet().stream() // раскопал про entrySet - попробовал
                .filter(e -> e.getValue().getDepartment() == department || department == ALL_DEPARTMENTS)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Employee getSalaryMin(int department) {
        Optional<Employee> employeeMin = getEmployeesDepartment(department).stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
        if (employeeMin.isPresent()) return employeeMin.get();
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee getSalaryMax(int department) {
        Optional<Employee> employeeMax = getEmployeesDepartment(department).stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        if (employeeMax.isPresent()) return employeeMax.get();
        throw new EmployeeNotFoundException();
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesGroupByDepartment() {
        return employeeService.getEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
