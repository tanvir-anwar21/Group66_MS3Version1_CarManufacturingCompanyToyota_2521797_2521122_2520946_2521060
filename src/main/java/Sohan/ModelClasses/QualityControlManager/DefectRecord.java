package Sohan.ModelClasses.QualityControlManager;

import java.io.Serializable;
import java.time.LocalDate;

public class DefectRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String defectID;
    private String vehicleID;
    private String vehicleModel;
    private String category;
    private String partInfo;
    private String description;
    private String severity;
    private String detectedBy;
    private LocalDate detectionDate;
    private String status;
    private boolean urgent;
    private String assignedTo;
    private LocalDate resolvedDate;
    private String resolutionNotes;
    private String reportedBy;
    private String productionLine;
    private String priority;

    public DefectRecord() {
        this.status = "Open";
        this.urgent = false;
        this.severity = "Medium";
        this.priority = "Medium";
        this.detectionDate = LocalDate.now();
    }

    public DefectRecord(String defectID, String vehicleID, String vehicleModel,
                        String category, String partInfo, String description) {
        this();
        this.defectID = defectID;
        this.vehicleID = vehicleID;
        this.vehicleModel = vehicleModel;
        this.category = category;
        this.partInfo = partInfo;
        this.description = description;
    }

    public String getDefectID() {
        return defectID;
    }

    public void setDefectID(String defectID) {
        this.defectID = defectID;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPartInfo() {
        return partInfo;
    }

    public void setPartInfo(String partInfo) {
        this.partInfo = partInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDetectedBy() {
        return detectedBy;
    }

    public void setDetectedBy(String detectedBy) {
        this.detectedBy = detectedBy;
    }

    public LocalDate getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(LocalDate detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(LocalDate resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public String getResolutionNotes() {
        return resolutionNotes;
    }

    public void setResolutionNotes(String resolutionNotes) {
        this.resolutionNotes = resolutionNotes;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isResolved() {
        return "Resolved".equals(status) || "Closed".equals(status);
    }

    public boolean isCritical() {
        return "Critical".equals(severity);
    }

    public boolean isHighPriority() {
        return "High".equals(priority) || "Critical".equals(severity);
    }

    @Override
    public String toString() {
        return "DefectRecord{" +
                "defectID='" + defectID + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", category='" + category + '\'' +
                ", severity='" + severity + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", urgent=" + urgent +
                ", detectionDate=" + detectionDate +
                '}';
    }
}