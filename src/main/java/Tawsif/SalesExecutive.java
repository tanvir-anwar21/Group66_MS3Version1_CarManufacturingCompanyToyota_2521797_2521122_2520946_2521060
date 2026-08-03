package Tawsif;


import com.example.group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060.User;

public class SalesExecutive extends User {

    private String employeeId;
    private String salesRegion;
    private double monthlySales;
    private int salesTarget;
    private double commission;

    public SalesExecutive() {
    }

    public SalesExecutive(String userId, String fullName, String email,
                          String password, String phoneNumber, String role,
                          String employeeId, String salesRegion,
                          double monthlySales, int salesTarget,
                          double commission) {

        super(userId, fullName, email, password, phoneNumber, role);

        this.employeeId = employeeId;
        this.salesRegion = salesRegion;
        this.monthlySales = monthlySales;
        this.salesTarget = salesTarget;
        this.commission = commission;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getSalesRegion() {
        return salesRegion;
    }

    public void setSalesRegion(String salesRegion) {
        this.salesRegion = salesRegion;
    }

    public double getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(double monthlySales) {
        this.monthlySales = monthlySales;
    }

    public int getSalesTarget() {
        return salesTarget;
    }

    public void setSalesTarget(int salesTarget) {
        this.salesTarget = salesTarget;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        return "SalesExecutive{" +
                "employeeId='" + employeeId + '\'' +
                ", salesRegion='" + salesRegion + '\'' +
                ", monthlySales=" + monthlySales +
                ", salesTarget=" + salesTarget +
                ", commission=" + commission +
                '}';
    }
}