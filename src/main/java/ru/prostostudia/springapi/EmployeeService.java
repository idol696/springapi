package ru.prostostudia.springapi;

import org.springframework.stereotype.Service;
import ru.prostostudia.springapi.exceptions.EmployeeAlreadyAddedException;
import ru.prostostudia.springapi.exceptions.EmployeeNotFoundException;
import ru.prostostudia.springapi.exceptions.EmployeeStorageIsFullException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    private final Map<String, Employee> employeesBook = new HashMap<>(Map.of());
    private int maxEmployees = Integer.MAX_VALUE;

    public EmployeeService() {
        demoFill();
    }

    public void demoFill() {
        employeesBook.clear();
        addEmployee("Илья", "Бабушкин", 1, 10_000);
        addEmployee("Игорь", "Мусинькин", 1, 20_000);
        addEmployee("Виталий", "Хазбулатов", 1, 5000);
        addEmployee("Иван", "Познер", 2, 10_000);
        addEmployee("Исаакий", "Волондемортов", 2, 30_000);
        addEmployee("Ирина", "Дудина", 1, 3000);
        addEmployee("Иннокентий", "Смактуновский", 2, 5000);
        addEmployee("Наталья", "Бузинова", 3, 80_000);
        addEmployee("Навелий", "Навеяло", 3, 50_000);
        addEmployee("Прасковья", "Прошкина", 2, 30_000);
        setMaxEmployees(10);
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        if (employeesBook.size() > maxEmployees) throw new EmployeeStorageIsFullException();
        this.maxEmployees = maxEmployees;
    }

/*    private List<Integer> getEmployeesDepartment() {   // На будущее - получить номера отделов сотрудников
        return employeesBook.values().stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());
    } */

    private List<Employee> getEmployeesInDepartment(int department) {
        return employeesBook.values().stream()
                .filter(e -> e.getDepartment() == department || department == ALL_DEPARTMENTS)
                .toList();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employeesBook.size() >= maxEmployees) throw new EmployeeStorageIsFullException();
        try {
            findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            Employee employee = new Employee(firstName, lastName);
            employeesBook.put(employee.id(), employee);
            return employee;
        }
        throw new EmployeeAlreadyAddedException();
    }


    @Override
    public Employee getSalaryMin(int department) {
        Optional<Employee> employeeMin = getEmployeesInDepartment(department).stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
        if (employeeMin.isPresent()) return employeeMin.get();
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee getSalaryMax(int department) {
        Optional<Employee> employeeMax = getEmployeesInDepartment(department).stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        if (employeeMax.isPresent()) return employeeMax.get();
        throw new EmployeeNotFoundException();
    }

    @Override
    public void addEmployee(String firstName, String lastName, int department, double salary) {
        final Employee newemployee = new Employee(firstName, lastName, department, salary);
        employeesBook.put(newemployee.id(), newemployee);
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        employeesBook.remove(employee.id());
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String id = new Employee(firstName, lastName).id();
        if (employeesBook.containsKey(id)) {
            return employeesBook.get(id);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Map<String, Employee> getEmployees(int department) {
        return employeesBook.entrySet().stream() // раскопал про entrySet - попробовал
                .filter(e -> e.getValue().getDepartment() == department || department == ALL_DEPARTMENTS)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return getEmployees(ALL_DEPARTMENTS);
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesGroupByDepartment() {
        return employeesBook.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}

