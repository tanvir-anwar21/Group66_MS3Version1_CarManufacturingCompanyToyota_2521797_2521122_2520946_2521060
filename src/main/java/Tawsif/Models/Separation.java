package Tawsif.Models;

import java.time.LocalDate;

public class Separation {
    private String employeeId;
    private String separationType;
    private LocalDate lastWorkingDay;
    private String reason;
    private String status;

    public Separation(String employeeId, String separationType, LocalDate lastWorkingDay, String reason, String status) {
        this.employeeId = employeeId;
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
        return "Separation{" +
                "employeeId='" + employeeId + '\'' +
                ", separationType='" + separationType + '\'' +
                ", lastWorkingDay=" + lastWorkingDay +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
