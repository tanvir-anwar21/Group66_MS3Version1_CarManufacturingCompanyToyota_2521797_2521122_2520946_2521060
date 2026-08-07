package Tawsif.Models;

import java.time.LocalDate;

public class EmployeeSeparation {

    private String employeeId;
    private String employeeName;
    private String department;
    private String separationType;
    private LocalDate lastWorkingDay;
    private String reason;
    private String status;

    public EmployeeSeparation(String employeeId,
                              String employeeName,
                              String department,
                              String separationType,
                              LocalDate lastWorkingDay,
                              String reason,
                              String status) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.separationType = separationType;
        this.lastWorkingDay = lastWorkingDay;
        this.reason = reason;
        this.status = status;
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

    public String getSeparationType() {
        return separationType;
    }

    public void setSeparationType(String separationType) {
        this.separationType = separationType;
    }

    public LocalDate getLastWorkingDay() {
        return lastWorkingDay;
    }

    public void setLastWorkingDay(LocalDate lastWorkingDay) {
        this.lastWorkingDay = lastWorkingDay;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeeSeparation{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", department='" + department + '\'' +
                ", separationType='" + separationType + '\'' +
                ", lastWorkingDay=" + lastWorkingDay +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}