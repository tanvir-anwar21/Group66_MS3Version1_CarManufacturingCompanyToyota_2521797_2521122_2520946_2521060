package Tanvir.Model_Class;

public class ProductionPerformance {

    private final String model;
    private final String assemblyLine;
    private int completed;
    private int pending;
    private int defective;
    private float efficiency;
    private String status;

    public ProductionPerformance(String model,
                                 String assemblyLine,
                                 int completed,
                                 int pending,
                                 int defective,
                                 float efficiency,
                                 String status) {

        this.model = model;
        this.assemblyLine = assemblyLine;
        this.completed = completed;
        this.pending = pending;
        this.defective = defective;
        this.efficiency = efficiency;
        this.status = status;
    }

    // Model

    public String getModel() {
        return model;
    }

    // Assembly Line

    public String getAssemblyLine() {
        return assemblyLine;
    }

    // Completed

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    // Pending

    public int getPending() {
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    // Defective

    public int getDefective() {
        return defective;
    }

    public void setDefective(int defective) {
        this.defective = defective;
    }

    // Efficiency

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    // Status

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductionPerformance{" +
                "model='" + model + '\'' +
                ", assemblyLine='" + assemblyLine + '\'' +
                ", completed=" + completed +
                ", pending=" + pending +
                ", defective=" + defective +
                ", efficiency=" + efficiency +
                ", status='" + status + '\'' +
                '}';
    }
}
