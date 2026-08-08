package Tanvir.Model_Class;

public class SalesPerformance {

    private final String dealership;
    private final String model;
    private int vehiclesSold;
    private float revenue;
    private float growth;
    private String remarks;

    public SalesPerformance(String dealership,
                            String model,
                            int vehiclesSold,
                            float revenue,
                            float growth,
                            String remarks) {

        this.dealership = dealership;
        this.model = model;
        this.vehiclesSold = vehiclesSold;
        this.revenue = revenue;
        this.growth = growth;
        this.remarks = remarks;
    }

    // Dealership

    public String getDealership() {
        return dealership;
    }

    // Model

    public String getModel() {
        return model;
    }

    // Vehicles Sold

    public int getVehiclesSold() {
        return vehiclesSold;
    }

    public void setVehiclesSold(int vehiclesSold) {
        this.vehiclesSold = vehiclesSold;
    }

    // Revenue

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    // Growth

    public float getGrowth() {
        return growth;
    }

    public void setGrowth(float growth) {
        this.growth = growth;
    }

    // Remarks

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "SalesPerformance{" +
                "dealership='" + dealership + '\'' +
                ", model='" + model + '\'' +
                ", vehiclesSold=" + vehiclesSold +
                ", revenue=" + revenue +
                ", growth=" + growth +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
