package Sohan.ModelClasses.QualityControlManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InspectionReport implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reportID;
    private String vehicleID;
    private String vehicleModel;
    private String inspectorID;
    private String inspectorName;
    private LocalDate reportDate;
    private String engineStatus;
    private String bodyStatus;
    private String paintStatus;
    private String brakesStatus;
    private String safetyStatus;
    private String electricalStatus;
    private String overallStatus;
    private String notes;
    private List<DefectRecord> defectsFound;
    private String recommendations;
    private boolean approved;
    private String approvalStatus;

    // Default Constructor
    public InspectionReport() {
        this.defectsFound = new ArrayList<>();
        this.approved = false;
        this.approvalStatus = "Pending";
        this.overallStatus = "Pending";
        this.reportDate = LocalDate.now();
    }

    // Parameterized Constructor
    public InspectionReport(String reportID, String vehicleID, String vehicleModel,
                            String inspectorID, String inspectorName) {
        this();
        this.reportID = reportID;
        this.vehicleID = vehicleID;
        this.vehicleModel = vehicleModel;
        this.inspectorID = inspectorID;
        this.inspectorName = inspectorName;
    }

    // Getters and Setters
    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
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

    public String getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(String inspectorID) {
        this.inspectorID = inspectorID;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(String engineStatus) {
        this.engineStatus = engineStatus;
    }

    public String getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(String bodyStatus) {
        this.bodyStatus = bodyStatus;
    }

    public String getPaintStatus() {
        return paintStatus;
    }

    public void setPaintStatus(String paintStatus) {
        this.paintStatus = paintStatus;
    }

    public String getBrakesStatus() {
        return brakesStatus;
    }

    public void setBrakesStatus(String brakesStatus) {
        this.brakesStatus = brakesStatus;
    }

    public String getSafetyStatus() {
        return safetyStatus;
    }

    public void setSafetyStatus(String safetyStatus) {
        this.safetyStatus = safetyStatus;
    }

    public String getElectricalStatus() {
        return electricalStatus;
    }

    public void setElectricalStatus(String electricalStatus) {
        this.electricalStatus = electricalStatus;
    }

    public String getOverallStatus() {
        return overallStatus;
    }

    public void setOverallStatus(String overallStatus) {
        this.overallStatus = overallStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<DefectRecord> getDefectsFound() {
        return defectsFound;
    }

    public void setDefectsFound(List<DefectRecord> defectsFound) {
        this.defectsFound = defectsFound;
    }

    public void addDefect(DefectRecord defect) {
        this.defectsFound.add(defect);
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
        this.approvalStatus = approved ? "Approved" : "Rejected";
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public int getDefectsCount() {
        return defectsFound.size();
    }

    public boolean isPassing() {
        return "Pass".equals(overallStatus) || "Conditional Pass".equals(overallStatus);
    }

    @Override
    public String toString() {
        return "InspectionReport{" +
                "reportID='" + reportID + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", inspectorName='" + inspectorName + '\'' +
                ", reportDate=" + reportDate +
                ", overallStatus='" + overallStatus + '\'' +
                ", defectsCount=" + getDefectsCount() +
                ", approved=" + approved +
                '}';
    }
}