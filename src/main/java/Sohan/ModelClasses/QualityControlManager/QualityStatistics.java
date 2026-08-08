package Sohan.ModelClasses.QualityControlManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class QualityStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    private String statisticID;
    private LocalDate date;
    private String productionLine;
    private String vehicleModel;
    private int totalInspected;
    private int passed;
    private int failed;
    private int conditionalPassed;
    private int defectsFound;
    private double passRate;
    private double defectRate;
    private int criticalDefects;
    private int majorDefects;
    private int minorDefects;
    private Map<String, Integer> defectByCategory;
    private double avgRepairTime;
    private String qualityScore;
    private int reworkCount;
    private int scrapCount;

    // Default Constructor
    public QualityStatistics() {
        this.defectByCategory = new HashMap<>();
        this.passRate = 0.0;
        this.defectRate = 0.0;
        this.avgRepairTime = 0.0;
        this.qualityScore = "A";
        this.date = LocalDate.now();
    }

    // Parameterized Constructor
    public QualityStatistics(String statisticID, String productionLine, String vehicleModel) {
        this();
        this.statisticID = statisticID;
        this.productionLine = productionLine;
        this.vehicleModel = vehicleModel;
    }

    // Getters and Setters
    public String getStatisticID() {
        return statisticID;
    }

    public void setStatisticID(String statisticID) {
        this.statisticID = statisticID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public int getTotalInspected() {
        return totalInspected;
    }

    public void setTotalInspected(int totalInspected) {
        this.totalInspected = totalInspected;
        calculateRates();
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
        calculateRates();
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
        calculateRates();
    }

    public int getConditionalPassed() {
        return conditionalPassed;
    }

    public void setConditionalPassed(int conditionalPassed) {
        this.conditionalPassed = conditionalPassed;
        calculateRates();
    }

    public int getDefectsFound() {
        return defectsFound;
    }

    public void setDefectsFound(int defectsFound) {
        this.defectsFound = defectsFound;
    }

    public double getPassRate() {
        return passRate;
    }

    public double getDefectRate() {
        return defectRate;
    }

    public int getCriticalDefects() {
        return criticalDefects;
    }

    public void setCriticalDefects(int criticalDefects) {
        this.criticalDefects = criticalDefects;
    }

    public int getMajorDefects() {
        return majorDefects;
    }

    public void setMajorDefects(int majorDefects) {
        this.majorDefects = majorDefects;
    }

    public int getMinorDefects() {
        return minorDefects;
    }

    public void setMinorDefects(int minorDefects) {
        this.minorDefects = minorDefects;
    }

    public Map<String, Integer> getDefectByCategory() {
        return defectByCategory;
    }

    public void setDefectByCategory(Map<String, Integer> defectByCategory) {
        this.defectByCategory = defectByCategory;
    }

    public void addDefectCategory(String category, int count) {
        this.defectByCategory.put(category, count);
    }

    public double getAvgRepairTime() {
        return avgRepairTime;
    }

    public void setAvgRepairTime(double avgRepairTime) {
        this.avgRepairTime = avgRepairTime;
    }

    public String getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(String qualityScore) {
        this.qualityScore = qualityScore;
    }

    public int getReworkCount() {
        return reworkCount;
    }

    public void setReworkCount(int reworkCount) {
        this.reworkCount = reworkCount;
    }

    public int getScrapCount() {
        return scrapCount;
    }

    public void setScrapCount(int scrapCount) {
        this.scrapCount = scrapCount;
    }

    private void calculateRates() {
        if (totalInspected > 0) {
            this.passRate = ((double) passed / totalInspected) * 100;
            this.defectRate = ((double) (failed + conditionalPassed) / totalInspected) * 100;
        } else {
            this.passRate = 0.0;
            this.defectRate = 0.0;
        }
    }

    public int getDefectsTotal() {
        return criticalDefects + majorDefects + minorDefects;
    }

    @Override
    public String toString() {
        return "QualityStatistics{" +
                "statisticID='" + statisticID + '\'' +
                ", date=" + date +
                ", productionLine='" + productionLine + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", totalInspected=" + totalInspected +
                ", passed=" + passed +
                ", failed=" + failed +
                ", passRate=" + String.format("%.2f", passRate) + "%" +
                ", defectRate=" + String.format("%.2f", defectRate) + "%" +
                ", qualityScore='" + qualityScore + '\'' +
                '}';
    }
}