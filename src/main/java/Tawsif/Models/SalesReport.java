package Tawsif.Models;

import java.time.LocalDate;

public class SalesReport {

    private String orderId;
    private String customerName;
    private String vehicleModel;
    private String region;
    private int quantity;
    private double amount;
    private LocalDate orderDate;

    public SalesReport(String orderId,
                       String customerName,
                       String vehicleModel,
                       String region,
                       int quantity,
                       double amount,
                       LocalDate orderDate) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.vehicleModel = vehicleModel;
        this.region = region;
        this.quantity = quantity;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "SalesReport{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", region='" + region + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", orderDate=" + orderDate +
                '}';
    }
}