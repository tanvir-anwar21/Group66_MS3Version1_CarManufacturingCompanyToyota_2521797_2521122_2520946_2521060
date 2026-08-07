package Tawsif.Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {

    private String attendanceId;
    private String employeeId;
    private String employeeName;
    private String department;
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private double hoursWorked;
    private String status;

    public Attendance(String attendanceId,
                      String employeeId,
                      String employeeName,
                      String department,
                      LocalDate date,
                      LocalTime checkIn,
                      LocalTime checkOut,
                      double hoursWorked,
                      String status) {

        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hoursWorked = hoursWorked;
        this.status = status;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId='" + attendanceId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", department='" + department + '\'' +
                ", date=" + date +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", hoursWorked=" + hoursWorked +
                ", status='" + status + '\'' +
                '}';
    }
}