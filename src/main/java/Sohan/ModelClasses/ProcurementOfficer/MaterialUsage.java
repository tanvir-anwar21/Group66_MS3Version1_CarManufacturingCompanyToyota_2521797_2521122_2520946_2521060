package Sohan.ModelClasses.ProcurementOfficer;

import java.io.Serializable;
import java.time.LocalDate;

public class MaterialUsage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String usageID;
    private String materialID;
    private String materialName;
    private String materialCategory;
    private double quantityUsed;
    private double remainingStock;
    private LocalDate usageDate;
    private String department;
    private String productionLine;
    private String vehicleModel;
    private int usageCount;
    private double avgConsumption;
    private double minStockLevel;
    private double maxStockLevel;
    private String status;
    private String unit;
    private String supplierName;
    private double reorderPoint;
    private double lastOrderQuantity;
    private LocalDate lastOrderDate;
    private String notes;

    // Default Constructor
    public MaterialUsage() {
        this.usageDate = LocalDate.now();
        this.status = "Normal";
        this.unit = "units";
        this.usageCount = 0;
        this.avgConsumption = 0.0;
        this.minStockLevel = 100.0;
        this.maxStockLevel = 10000.0;
        this.reorderPoint = 500.0;
    }

    // Parameterized Constructor
    public MaterialUsage(String usageID, String materialID, String materialName,
                         double quantityUsed, double remainingStock) {
        this();
        this.usageID = usageID;
        this.materialID = materialID;
        this.materialName = materialName;
        this.quantityUsed = quantityUsed;
        this.remainingStock = remainingStock;
        updateStatus();
    }

    // Getters and Setters
    public String getUsageID() {
        return usageID;
    }

    public void setUsageID(String usageID) {
        this.usageID = usageID;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialCategory() {
        return materialCategory;
    }

    public void setMaterialCategory(String materialCategory) {
        this.materialCategory = materialCategory;
    }

    public double getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(double quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public double getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(double remainingStock) {
        this.remainingStock = remainingStock;
        updateStatus();
    }

    public LocalDate getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(LocalDate usageDate) {
        this.usageDate = usageDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }

    public double getAvgConsumption() {
        return avgConsumption;
    }

    public void setAvgConsumption(double avgConsumption) {
        this.avgConsumption = avgConsumption;
    }

    public double getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(double minStockLevel) {
        this.minStockLevel = minStockLevel;
        updateStatus();
    }

    public double getMaxStockLevel() {
        return maxStockLevel;
    }

    public void setMaxStockLevel(double maxStockLevel) {
        this.maxStockLevel = maxStockLevel;
        updateStatus();
    }

    public String getStatus() {
        return status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getReorderPoint() {
        return reorderPoint;
    }

    public void setReorderPoint(double reorderPoint) {
        this.reorderPoint = reorderPoint;
        updateStatus();
    }

    public double getLastOrderQuantity() {
        return lastOrderQuantity;
    }

    public void setLastOrderQuantity(double lastOrderQuantity) {
        this.lastOrderQuantity = lastOrderQuantity;
    }

    public LocalDate getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(LocalDate lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private void updateStatus() {
        if (remainingStock <= minStockLevel) {
            this.status = "Critical - Reorder Required";
        } else if (remainingStock <= reorderPoint) {
            this.status = "Low Stock - Order Soon";
        } else if (remainingStock >= maxStockLevel) {
            this.status = "Overstocked";
        } else {
            this.status = "Normal";
        }
    }

    public boolean isLowStock() {
        return "Low Stock - Order Soon".equals(status) || "Critical - Reorder Required".equals(status);
    }

    public boolean isOverstocked() {
        return "Overstocked".equals(status);
    }

    public double getDaysOfStockLeft(double dailyConsumption) {
        if (dailyConsumption <= 0) return 999.0;
        return remainingStock / dailyConsumption;
    }

    @Override
    public String toString() {
        return "MaterialUsage{" +
                "usageID='" + usageID + '\'' +
                ", materialName='" + materialName + '\'' +
                ", quantityUsed=" + quantityUsed +
                ", remainingStock=" + remainingStock +
                ", usageDate=" + usageDate +
                ", status='" + status + '\'' +
                '}';
    }
}