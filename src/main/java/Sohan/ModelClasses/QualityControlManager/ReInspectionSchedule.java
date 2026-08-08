package Sohan.ModelClasses.QualityControlManager;

import java.io.Serializable;
import java.time.LocalDate;

public class ReInspectionSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

    private String scheduleID;
    private String vehicleID;
    private String vehicleModel;
    private String vin;
    private LocalDate previousInspectionDate;
    private LocalDate reInspectionDate;
    private String reason;
    private String assignedInspectorID;
    private String assignedInspectorName;
    private String priority;
    private String status;
    private String notes;
    private LocalDate scheduledDate;
    private boolean completed;
    private String result;

    // Default Constructor
    public ReInspectionSchedule() {
        this.status = "Scheduled";
        this.priority = "Medium";
        this.completed = false;
        this.scheduledDate = LocalDate.now();
    }

    // Parameterized Constructor
    public ReInspectionSchedule(String scheduleID, String vehicleID, String vehicleModel,
                                LocalDate reInspectionDate, String reason, String assignedInspectorID) {
        this();
        this.scheduleID = scheduleID;
        this.vehicleID = vehicleID;
        this.vehicleModel = vehicleModel;
        this.reInspectionDate = reInspectionDate;
        this.reason = reason;
        this.assignedInspectorID = assignedInspectorID;
    }

    // Getters and Setters
    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public LocalDate getPreviousInspectionDate() {
        return previousInspectionDate;
    }

    public void setPreviousInspectionDate(LocalDate previousInspectionDate) {
        this.previousInspectionDate = previousInspectionDate;
    }

    public LocalDate getReInspectionDate() {
        return reInspectionDate;
    }

    public void setReInspectionDate(LocalDate reInspectionDate) {
        this.reInspectionDate = reInspectionDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAssignedInspectorID() {
        return assignedInspectorID;
    }

    public void setAssignedInspectorID(String assignedInspectorID) {
        this.assignedInspectorID = assignedInspectorID;
    }

    public String getAssignedInspectorName() {
        return assignedInspectorName;
    }

    public void setAssignedInspectorName(String assignedInspectorName) {
        this.assignedInspectorName = assignedInspectorName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        this.status = completed ? "Completed" : "Scheduled";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isOverdue() {
        return !completed && reInspectionDate != null && reInspectionDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "ReInspectionSchedule{" +
                "scheduleID='" + scheduleID + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", reInspectionDate=" + reInspectionDate +
                ", reason='" + reason + '\'' +
                ", assignedInspectorName='" + assignedInspectorName + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}