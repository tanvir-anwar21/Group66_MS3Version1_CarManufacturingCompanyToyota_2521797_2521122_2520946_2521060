package Sohan.ModelClasses.ProcurementOfficer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    private String supplierID;
    private String name;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
    private String type;
    private String status;
    private String paymentTerms;
    private String deliveryTerms;
    private double creditLimit;
    private double currentBalance;
    private List<String> materialsSupplied;
    private String rating;
    private int yearsInBusiness;
    private LocalDate registrationDate;
    private String taxID;
    private boolean preferred;
    private boolean taxExempt;
    private String website;
    private String bankDetails;
    private List<String> performanceHistory;
    private int ordersCompleted;
    private int ordersFailed;
    private double onTimeDeliveryRate;
    private double qualityScore;

    // Default Constructor
    public Supplier() {
        this.materialsSupplied = new ArrayList<>();
        this.performanceHistory = new ArrayList<>();
        this.status = "Active";
        this.rating = "B";
        this.preferred = false;
        this.taxExempt = false;
        this.ordersCompleted = 0;
        this.ordersFailed = 0;
        this.onTimeDeliveryRate = 0.0;
        this.qualityScore = 0.0;
        this.creditLimit = 100000.0;
        this.currentBalance = 0.0;
        this.registrationDate = LocalDate.now();
    }

    // Parameterized Constructor
    public Supplier(String supplierID, String name, String contactPerson, String email, String phone) {
        this();
        this.supplierID = supplierID;
        this.name = name;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<String> getMaterialsSupplied() {
        return materialsSupplied;
    }

    public void setMaterialsSupplied(List<String> materialsSupplied) {
        this.materialsSupplied = materialsSupplied;
    }

    public void addMaterialSupplied(String material) {
        if (!this.materialsSupplied.contains(material)) {
            this.materialsSupplied.add(material);
        }
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getYearsInBusiness() {
        return yearsInBusiness;
    }

    public void setYearsInBusiness(int yearsInBusiness) {
        this.yearsInBusiness = yearsInBusiness;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public boolean isTaxExempt() {
        return taxExempt;
    }

    public void setTaxExempt(boolean taxExempt) {
        this.taxExempt = taxExempt;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public List<String> getPerformanceHistory() {
        return performanceHistory;
    }

    public void setPerformanceHistory(List<String> performanceHistory) {
        this.performanceHistory = performanceHistory;
    }

    public void addPerformanceNote(String note) {
        this.performanceHistory.add(note);
    }

    public int getOrdersCompleted() {
        return ordersCompleted;
    }

    public void setOrdersCompleted(int ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    public void incrementOrdersCompleted() {
        this.ordersCompleted++;
    }

    public int getOrdersFailed() {
        return ordersFailed;
    }

    public void setOrdersFailed(int ordersFailed) {
        this.ordersFailed = ordersFailed;
    }

    public void incrementOrdersFailed() {
        this.ordersFailed++;
    }

    public double getOnTimeDeliveryRate() {
        return onTimeDeliveryRate;
    }

    public void setOnTimeDeliveryRate(double onTimeDeliveryRate) {
        this.onTimeDeliveryRate = onTimeDeliveryRate;
    }

    public double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public double getSuccessRate() {
        int total = ordersCompleted + ordersFailed;
        if (total == 0) return 0.0;
        return ((double) ordersCompleted / total) * 100;
    }

    public boolean isActive() {
        return "Active".equals(status);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierID='" + supplierID + '\'' +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", rating='" + rating + '\'' +
                ", preferred=" + preferred +
                '}';
    }
}