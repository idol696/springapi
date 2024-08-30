package ru.prostostudia.springapi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.prostostudia.springapi.exceptions.EmployeeAlreadyAddedException;
import ru.prostostudia.springapi.exceptions.EmployeeNotFoundException;
import ru.prostostudia.springapi.exceptions.EmployeeStorageIsFullException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceInterface employeeService;


    public EmployeeController(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Object employeeList() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "/del")
    public Object employeeDelete(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/add")
    public Object employeeAdd(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/find")
    public Object employeeFind(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/departments/all", params = "departmentId")
    public Object employeeInDepartment(@RequestParam("departmentId") int department) {
        try {
            return employeeService.getEmployees(department);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }
    @GetMapping(path = "/departments/all")
    public Object employeeGroupByDepartment() {
        try {
            return employeeService.getEmployeesGroupByDepartment();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/departments/min-salary")
    public Object employeeMinSalary(@RequestParam("departmentId") int department) {
        try {
            return employeeService.getSalaryMin(department);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/departments/max-salary")
    public Object employeeMaxSalary(@RequestParam("departmentId") int department) {
        try {
            return employeeService.getSalaryMax(department);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }
}
