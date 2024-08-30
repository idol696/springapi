package ru.prostostudia.springapi;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private int department;
    private double salary;

    public Employee(String firstName, String lastName, int department, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {
        throw new RuntimeException("Задайте имя, фамилию, отдел и зарплату!");
    }

    public String id() {
        return (getFirstName() + getLastName()).toLowerCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDepartment(int department) {
            this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary > 0 ? salary : 0;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary > 0 ? salary : 0;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

