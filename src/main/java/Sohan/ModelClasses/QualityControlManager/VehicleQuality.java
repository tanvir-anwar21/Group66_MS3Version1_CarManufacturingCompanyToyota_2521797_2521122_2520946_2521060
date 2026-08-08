package Sohan.ModelClasses.QualityControlManager;

import java.io.Serializable;
import java.time.LocalDate;

public class VehicleQuality implements Serializable {
    private static final long serialVersionUID = 1L;

    private String qualityID;
    private String vehicleID;
    private String vehicleModel;
    private String vin;
    private String inspectorID;
    private String inspectorName;
    private LocalDate inspectionDate;
    private String engineStatus;
    private String bodyStatus;
    private String paintStatus;
    private String brakesStatus;
    private String safetyStatus;
    private String electricalStatus;
    private String overallStatus;
    private String notes;
    private int defectsCount;
    private boolean approvedForDelivery;
    private String approvalStatus;
    private String batchID;
    private String qualityScore;

    // Default Constructor
    public VehicleQuality() {
        this.engineStatus = "Not Tested";
        this.bodyStatus = "Not Tested";
        this.paintStatus = "Not Tested";
        this.brakesStatus = "Not Tested";
        this.safetyStatus = "Not Tested";
        this.electricalStatus = "Not Tested";
        this.overallStatus = "Pending";
        this.approvedForDelivery = false;
        this.approvalStatus = "Pending";
        this.defectsCount = 0;
        this.qualityScore = "N/A";
        this.inspectionDate = LocalDate.now();
    }

    // Parameterized Constructor
    public VehicleQuality(String qualityID, String vehicleID, String vehicleModel, String vin,
                          String inspectorID, String inspectorName) {
        this();
        this.qualityID = qualityID;
        this.vehicleID = vehicleID;
        this.vehicleModel = vehicleModel;
        this.vin = vin;
        this.inspectorID = inspectorID;
        this.inspectorName = inspectorName;
    }

    // Getters and Setters
    public String getQualityID() {
        return qualityID;
    }

    public void setQualityID(String qualityID) {
        this.qualityID = qualityID;
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

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
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

    public int getDefectsCount() {
        return defectsCount;
    }

    public void setDefectsCount(int defectsCount) {
        this.defectsCount = defectsCount;
    }

    public void incrementDefectsCount() {
        this.defectsCount++;
    }

    public boolean isApprovedForDelivery() {
        return approvedForDelivery;
    }

    public void setApprovedForDelivery(boolean approvedForDelivery) {
        this.approvedForDelivery = approvedForDelivery;
        this.approvalStatus = approvedForDelivery ? "Approved" : "Rejected";
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(String qualityScore) {
        this.qualityScore = qualityScore;
    }

    public boolean isPassing() {
        return "Pass".equals(overallStatus) || "Conditional Pass".equals(overallStatus);
    }

    public boolean hasDefects() {
        return defectsCount > 0;
    }

    @Override
    public String toString() {
        return "VehicleQuality{" +
                "qualityID='" + qualityID + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vin='" + vin + '\'' +
                ", inspectorName='" + inspectorName + '\'' +
                ", inspectionDate=" + inspectionDate +
                ", overallStatus='" + overallStatus + '\'' +
                ", defectsCount=" + defectsCount +
                ", approvedForDelivery=" + approvedForDelivery +
                '}';
    }
}