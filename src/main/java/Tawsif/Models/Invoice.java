package Tawsif.Models;

import java.time.LocalDate;

public class Invoice {
    private String invoiceId;
    private String orderId;
    private String customerName;
    private double amount;
    private LocalDate invoiceDate;
    private String paymentStatus;

    public Invoice(String invoiceId, String orderId, String customerName, double amount, LocalDate invoiceDate, String paymentStatus) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.paymentStatus = paymentStatus;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", invoiceDate=" + invoiceDate +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
