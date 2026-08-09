package Sohan.ModelClasses.ProcurementOfficer;

import java.io.Serializable;
import java.time.LocalDate;

public class DeliveryApproval implements Serializable {
    private static final long serialVersionUID = 1L;

    private String approvalID;
    private String deliveryID;
    private String purchaseOrderID;
    private String supplierID;
    private String supplierName;
    private String materialName;
    private double quantityOrdered;
    private double quantityDelivered;
    private double quantityAccepted;
    private double quantityRejected;
    private LocalDate deliveryDate;
    private String qualityCheckResult;
    private String approvalStatus;
    private String approvedBy;
    private LocalDate approvalDate;
    private String notes;
    private boolean inventoryUpdated;
    private LocalDate inventoryUpdateDate;
    private String rejectionReason;
    private double unitPrice;
    private double totalValue;
    private String deliveryStatus;
    private String inspectionNotes;

    public DeliveryApproval() {
        this.approvalStatus = "Pending";
        this.qualityCheckResult = "Not Inspected";
        this.deliveryStatus = "Pending";
        this.inventoryUpdated = false;
        this.quantityAccepted = 0.0;
        this.quantityRejected = 0.0;
        this.deliveryDate = LocalDate.now();
    }

    public DeliveryApproval(String approvalID, String deliveryID, String purchaseOrderID,
                            String supplierName, String materialName, double quantityDelivered) {
        this();
        this.approvalID = approvalID;
        this.deliveryID = deliveryID;
        this.purchaseOrderID = purchaseOrderID;
        this.supplierName = supplierName;
        this.materialName = materialName;
        this.quantityDelivered = quantityDelivered;
    }

    // Getters and Setters
    public String getApprovalID() { return approvalID; }
    public void setApprovalID(String approvalID) { this.approvalID = approvalID; }
    public String getDeliveryID() { return deliveryID; }
    public void setDeliveryID(String deliveryID) { this.deliveryID = deliveryID; }
    public String getPurchaseOrderID() { return purchaseOrderID; }
    public void setPurchaseOrderID(String purchaseOrderID) { this.purchaseOrderID = purchaseOrderID; }
    public String getSupplierID() { return supplierID; }
    public void setSupplierID(String supplierID) { this.supplierID = supplierID; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public double getQuantityOrdered() { return quantityOrdered; }
    public void setQuantityOrdered(double quantityOrdered) { this.quantityOrdered = quantityOrdered; }
    public double getQuantityDelivered() { return quantityDelivered; }
    public void setQuantityDelivered(double quantityDelivered) { this.quantityDelivered = quantityDelivered; }
    public double getQuantityAccepted() { return quantityAccepted; }
    public void setQuantityAccepted(double quantityAccepted) {
        if (quantityAccepted < 0) throw new IllegalArgumentException("Accepted quantity cannot be negative");
        this.quantityAccepted = quantityAccepted;
    }
    public double getQuantityRejected() { return quantityRejected; }
    public void setQuantityRejected(double quantityRejected) {
        if (quantityRejected < 0) throw new IllegalArgumentException("Rejected quantity cannot be negative");
        this.quantityRejected = quantityRejected;
    }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    public String getQualityCheckResult() { return qualityCheckResult; }
    public void setQualityCheckResult(String qualityCheckResult) { this.qualityCheckResult = qualityCheckResult; }
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public LocalDate getApprovalDate() { return approvalDate; }
    public void setApprovalDate(LocalDate approvalDate) { this.approvalDate = approvalDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public boolean isInventoryUpdated() { return inventoryUpdated; }
    public void setInventoryUpdated(boolean inventoryUpdated) {
        this.inventoryUpdated = inventoryUpdated;
        if (inventoryUpdated) {
            this.inventoryUpdateDate = LocalDate.now();
        }
    }
    public LocalDate getInventoryUpdateDate() { return inventoryUpdateDate; }
    public void setInventoryUpdateDate(LocalDate inventoryUpdateDate) { this.inventoryUpdateDate = inventoryUpdateDate; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0) throw new IllegalArgumentException("Unit price cannot be negative");
        this.unitPrice = unitPrice;
    }
    public double getTotalValue() { return totalValue; }
    public void setTotalValue(double totalValue) { this.totalValue = totalValue; }
    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }
    public String getInspectionNotes() { return inspectionNotes; }
    public void setInspectionNotes(String inspectionNotes) { this.inspectionNotes = inspectionNotes; }

    public boolean isApproved() { return "Approved".equals(approvalStatus); }
    public boolean isRejected() { return "Rejected".equals(approvalStatus); }

    public double getAcceptanceRate() {
        if (quantityDelivered == 0) return 0.0;
        return (quantityAccepted / quantityDelivered) * 100;
    }

    @Override
    public String toString() {
        return "DeliveryApproval{" +
                "approvalID='" + approvalID + '\'' +
                ", deliveryID='" + deliveryID + '\'' +
                ", purchaseOrderID='" + purchaseOrderID + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", quantityDelivered=" + quantityDelivered +
                ", quantityAccepted=" + quantityAccepted +
                ", qualityCheckResult='" + qualityCheckResult + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                '}';
    }
}