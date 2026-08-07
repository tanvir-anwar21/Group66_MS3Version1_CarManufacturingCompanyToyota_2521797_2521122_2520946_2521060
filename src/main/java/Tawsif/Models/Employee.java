package Tawsif.Models;

import java.time.LocalDate;

public class Employee {

    private String employeeId;
    private String employeeName;
    private String department;
    private String designation;
    private String phone;
    private String email;
    private double salary;
    private LocalDate joiningDate;
    private String status;

    public Employee(String employeeId,
                    String employeeName,
                    String department,
                    String designation,
                    String phone,
                    String email,
                    double salary,
                    LocalDate joiningDate) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.designation = designation;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.status = "Active";
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", joiningDate=" + joiningDate +
                ", status='" + status + '\'' +
                '}';
    }
}