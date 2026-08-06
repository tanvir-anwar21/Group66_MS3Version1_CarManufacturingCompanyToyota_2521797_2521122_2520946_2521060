package Tawsif.Models;

public class Vehicle {
    private String vehicleId;
    private String model;
    private String color;
    private String transmission;
    private double price;
    private int stock;

    public Vehicle(String vehicleId, String model, String color, String transmission, double price, int stock) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.color = color;
        this.transmission = transmission;
        this.price = price;
        this.stock = stock;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", transmission='" + transmission + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
