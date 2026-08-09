package Sohan.ModelClasses.ProcurementOfficer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderID;
    private String supplierID;
    private String supplierName;
    private String materialName;
    private String materialCategory;
    private double quantity;
    private String unit;
    private double unitPrice;
    private double totalCost;
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private LocalDate actualDeliveryDate;
    private String status;
    private String priority;
    private String orderReference;
    private String createdBy;
    private String approvedBy;
    private LocalDate approvalDate;
    private String deliveryTerms;
    private String paymentTerms;
    private boolean insured;
    private boolean tracked;
    private boolean qualityCertificateRequired;
    private List<String> notes;
    private double discount;
    private double tax;
    private double grandTotal;
    private String department;
    private String projectCode;

    public PurchaseOrder() {
        this.orderDate = LocalDate.now();
        this.status = "Draft";
        this.priority = "Normal";
        this.notes = new ArrayList<>();
        this.insured = false;
        this.tracked = false;
        this.qualityCertificateRequired = false;
        this.discount = 0.0;
        this.tax = 0.0;
        this.unit = "units";
        this.deliveryTerms = "Standard";
        this.paymentTerms = "Net 30";
    }

    public PurchaseOrder(String orderID, String supplierID, String supplierName,
                         String materialName, double quantity, double unitPrice) {
        this();
        this.orderID = orderID;
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.materialName = materialName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalCost = quantity * unitPrice;
        this.grandTotal = this.totalCost;
        this.status = "Pending";
    }

    // Getters and Setters
    public String getOrderID() { return orderID; }
    public void setOrderID(String orderID) { this.orderID = orderID; }
    public String getSupplierID() { return supplierID; }
    public void setSupplierID(String supplierID) { this.supplierID = supplierID; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public String getMaterialCategory() { return materialCategory; }
    public void setMaterialCategory(String materialCategory) { this.materialCategory = materialCategory; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        this.quantity = quantity;
        calculateTotal();
    }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0) throw new IllegalArgumentException("Unit price cannot be negative");
        this.unitPrice = unitPrice;
        calculateTotal();
    }
    public double getTotalCost() { return totalCost; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public LocalDate getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
    public LocalDate getActualDeliveryDate() { return actualDeliveryDate; }
    public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
        if (actualDeliveryDate != null) {
            this.status = "Delivered";
        }
    }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getOrderReference() { return orderReference; }
    public void setOrderReference(String orderReference) { this.orderReference = orderReference; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public LocalDate getApprovalDate() { return approvalDate; }
    public void setApprovalDate(LocalDate approvalDate) { this.approvalDate = approvalDate; }
    public String getDeliveryTerms() { return deliveryTerms; }
    public void setDeliveryTerms(String deliveryTerms) { this.deliveryTerms = deliveryTerms; }
    public String getPaymentTerms() { return paymentTerms; }
    public void setPaymentTerms(String paymentTerms) { this.paymentTerms = paymentTerms; }
    public boolean isInsured() { return insured; }
    public void setInsured(boolean insured) { this.insured = insured; }
    public boolean isTracked() { return tracked; }
    public void setTracked(boolean tracked) { this.tracked = tracked; }
    public boolean isQualityCertificateRequired() { return qualityCertificateRequired; }
    public void setQualityCertificateRequired(boolean qualityCertificateRequired) { this.qualityCertificateRequired = qualityCertificateRequired; }
    public List<String> getNotes() { return notes; }
    public void setNotes(List<String> notes) { this.notes = notes; }
    public void addNote(String note) { this.notes.add(note); }
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100) throw new IllegalArgumentException("Discount must be between 0 and 100");
        this.discount = discount;
        calculateTotal();
    }
    public double getTax() { return tax; }
    public void setTax(double tax) {
        if (tax < 0 || tax > 100) throw new IllegalArgumentException("Tax must be between 0 and 100");
        this.tax = tax;
        calculateTotal();
    }
    public double getGrandTotal() { return grandTotal; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getProjectCode() { return projectCode; }
    public void setProjectCode(String projectCode) { this.projectCode = projectCode; }

    private void calculateTotal() {
        this.totalCost = quantity * unitPrice;
        this.grandTotal = this.totalCost - (this.totalCost * (discount / 100)) +
                (this.totalCost * (tax / 100));
    }

    public boolean isPending() { return "Pending".equals(status) || "Draft".equals(status); }
    public boolean isDelivered() { return "Delivered".equals(status); }
    public boolean isUrgent() { return "Urgent".equals(priority) || "Critical".equals(priority); }

    public boolean isOverdue() {
        return expectedDeliveryDate != null &&
                expectedDeliveryDate.isBefore(LocalDate.now()) &&
                !isDelivered();
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "orderID='" + orderID + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", quantity=" + quantity +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}