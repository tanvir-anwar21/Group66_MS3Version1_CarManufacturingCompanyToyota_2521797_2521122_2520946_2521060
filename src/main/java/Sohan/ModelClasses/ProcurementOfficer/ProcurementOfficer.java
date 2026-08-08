package Sohan.ModelClasses.ProcurementOfficer;

import Utility.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProcurementOfficer extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    // Procurement Specific Fields
    private double procurementBudgetLimit;
    private double currentBudgetUsed;
    private String assignedDepartment;
    private int yearsOfExperience;
    private List<String> supplierSpecializations;
    private int purchaseOrdersCreated;
    private int purchaseOrdersCompleted;
    private double totalSpent;
    private int supplierCount;
    private double averageOrderValue;
    private String procurementStatus;
    private String procurementLevel;
    private String shiftTiming;
    private int teamSize;
    private double budgetUtilization;

    // Default Constructor
    public ProcurementOfficer() {
        super();
        this.procurementBudgetLimit = 500000.0;
        this.currentBudgetUsed = 0.0;
        this.assignedDepartment = "Procurement";
        this.supplierSpecializations = new ArrayList<>();
        this.purchaseOrdersCreated = 0;
        this.purchaseOrdersCompleted = 0;
        this.totalSpent = 0.0;
        this.supplierCount = 0;
        this.averageOrderValue = 0.0;
        this.procurementStatus = "Active";
        this.procurementLevel = "Senior";
        this.shiftTiming = "Day";
        this.teamSize = 4;
        this.budgetUtilization = 0.0;
        setRole("ProcurementOfficer");
    }

    // Parameterized Constructor
    public ProcurementOfficer(String userId, String fullName, String email,
                              String password, String phoneNumber, String role,
                              double procurementBudgetLimit, String assignedDepartment,
                              int yearsOfExperience) {
        super(userId, fullName, email, password, phoneNumber, role);
        this.procurementBudgetLimit = procurementBudgetLimit;
        this.currentBudgetUsed = 0.0;
        this.assignedDepartment = assignedDepartment;
        this.yearsOfExperience = yearsOfExperience;
        this.supplierSpecializations = new ArrayList<>();
        this.purchaseOrdersCreated = 0;
        this.purchaseOrdersCompleted = 0;
        this.totalSpent = 0.0;
        this.supplierCount = 0;
        this.averageOrderValue = 0.0;
        this.procurementStatus = "Active";
        this.procurementLevel = "Senior";
        this.shiftTiming = "Day";
        this.teamSize = 4;
        this.budgetUtilization = 0.0;
    }

    // Simplified Constructor
    public ProcurementOfficer(String userId, String fullName, String email,
                              String password, String phoneNumber) {
        super(userId, fullName, email, password, phoneNumber, "ProcurementOfficer");
        this.procurementBudgetLimit = 500000.0;
        this.currentBudgetUsed = 0.0;
        this.assignedDepartment = "Procurement";
        this.supplierSpecializations = new ArrayList<>();
        this.purchaseOrdersCreated = 0;
        this.purchaseOrdersCompleted = 0;
        this.totalSpent = 0.0;
        this.supplierCount = 0;
        this.averageOrderValue = 0.0;
        this.procurementStatus = "Active";
        this.procurementLevel = "Senior";
        this.shiftTiming = "Day";
        this.teamSize = 4;
        this.budgetUtilization = 0.0;
    }

    // Getters and Setters
    public double getProcurementBudgetLimit() {
        return procurementBudgetLimit;
    }

    public void setProcurementBudgetLimit(double procurementBudgetLimit) {
        this.procurementBudgetLimit = procurementBudgetLimit;
    }

    public double getCurrentBudgetUsed() {
        return currentBudgetUsed;
    }

    public void setCurrentBudgetUsed(double currentBudgetUsed) {
        this.currentBudgetUsed = currentBudgetUsed;
        calculateBudgetUtilization();
    }

    public void addToBudgetUsed(double amount) {
        this.currentBudgetUsed += amount;
        calculateBudgetUtilization();
    }

    public String getAssignedDepartment() {
        return assignedDepartment;
    }

    public void setAssignedDepartment(String assignedDepartment) {
        this.assignedDepartment = assignedDepartment;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getSupplierSpecializations() {
        return supplierSpecializations;
    }

    public void setSupplierSpecializations(List<String> supplierSpecializations) {
        this.supplierSpecializations = supplierSpecializations;
    }

    public void addSupplierSpecialization(String specialization) {
        if (!this.supplierSpecializations.contains(specialization)) {
            this.supplierSpecializations.add(specialization);
        }
    }

    public int getPurchaseOrdersCreated() {
        return purchaseOrdersCreated;
    }

    public void setPurchaseOrdersCreated(int purchaseOrdersCreated) {
        this.purchaseOrdersCreated = purchaseOrdersCreated;
    }

    public void incrementPurchaseOrdersCreated() {
        this.purchaseOrdersCreated++;
    }

    public int getPurchaseOrdersCompleted() {
        return purchaseOrdersCompleted;
    }

    public void setPurchaseOrdersCompleted(int purchaseOrdersCompleted) {
        this.purchaseOrdersCompleted = purchaseOrdersCompleted;
    }

    public void incrementPurchaseOrdersCompleted() {
        this.purchaseOrdersCompleted++;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public void addToTotalSpent(double amount) {
        this.totalSpent += amount;
    }

    public int getSupplierCount() {
        return supplierCount;
    }

    public void setSupplierCount(int supplierCount) {
        this.supplierCount = supplierCount;
    }

    public void incrementSupplierCount() {
        this.supplierCount++;
    }

    public double getAverageOrderValue() {
        return averageOrderValue;
    }

    public void setAverageOrderValue(double averageOrderValue) {
        this.averageOrderValue = averageOrderValue;
    }

    public String getProcurementStatus() {
        return procurementStatus;
    }

    public void setProcurementStatus(String procurementStatus) {
        this.procurementStatus = procurementStatus;
    }

    public String getProcurementLevel() {
        return procurementLevel;
    }

    public void setProcurementLevel(String procurementLevel) {
        this.procurementLevel = procurementLevel;
    }

    public String getShiftTiming() {
        return shiftTiming;
    }

    public void setShiftTiming(String shiftTiming) {
        this.shiftTiming = shiftTiming;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public double getBudgetUtilization() {
        return budgetUtilization;
    }

    public double getRemainingBudget() {
        return procurementBudgetLimit - currentBudgetUsed;
    }

    private void calculateBudgetUtilization() {
        if (procurementBudgetLimit == 0) {
            this.budgetUtilization = 0.0;
        } else {
            this.budgetUtilization = (currentBudgetUsed / procurementBudgetLimit) * 100;
        }
    }

    public double getBudgetUtilizationPercentage() {
        calculateBudgetUtilization();
        return budgetUtilization;
    }

    public double getOrderCompletionRate() {
        if (purchaseOrdersCreated == 0) return 0.0;
        return ((double) purchaseOrdersCompleted / purchaseOrdersCreated) * 100;
    }

    public double getAverageSpendPerOrder() {
        if (purchaseOrdersCreated == 0) return 0.0;
        return totalSpent / purchaseOrdersCreated;
    }

    @Override
    public String toString() {
        return "ProcurementOfficer{" +
                "userId='" + getUserId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", role='" + getRole() + '\'' +
                ", procurementBudgetLimit=" + procurementBudgetLimit +
                ", currentBudgetUsed=" + currentBudgetUsed +
                ", assignedDepartment='" + assignedDepartment + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", supplierSpecializations=" + supplierSpecializations +
                ", purchaseOrdersCreated=" + purchaseOrdersCreated +
                ", purchaseOrdersCompleted=" + purchaseOrdersCompleted +
                ", totalSpent=" + totalSpent +
                ", supplierCount=" + supplierCount +
                ", averageOrderValue=" + averageOrderValue +
                ", procurementStatus='" + procurementStatus + '\'' +
                ", procurementLevel='" + procurementLevel + '\'' +
                ", shiftTiming='" + shiftTiming + '\'' +
                ", teamSize=" + teamSize +
                ", budgetUtilization=" + budgetUtilization +
                '}';
    }
}