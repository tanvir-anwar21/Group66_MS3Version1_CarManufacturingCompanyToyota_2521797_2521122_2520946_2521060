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

    public QualityStatistics() {
        this.defectByCategory = new HashMap<>();
        this.passRate = 0.0;
        this.defectRate = 0.0;
        this.avgRepairTime = 0.0;
        this.qualityScore = "N/A";
        this.date = LocalDate.now();
    }

    public QualityStatistics(String statisticID, String productionLine, String vehicleModel) {
        this();
        this.statisticID = statisticID;
        this.productionLine = productionLine;
        this.vehicleModel = vehicleModel;
    }

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
        if (totalInspected < 0) {
            throw new IllegalArgumentException("Total inspected cannot be negative");
        }
        this.totalInspected = totalInspected;
        calculateRates();
        calculateQualityScore();
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        if (passed < 0) {
            throw new IllegalArgumentException("Passed count cannot be negative");
        }
        this.passed = passed;
        calculateRates();
        calculateQualityScore();
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        if (failed < 0) {
            throw new IllegalArgumentException("Failed count cannot be negative");
        }
        this.failed = failed;
        calculateRates();
    }

    public int getConditionalPassed() {
        return conditionalPassed;
    }

    public void setConditionalPassed(int conditionalPassed) {
        if (conditionalPassed < 0) {
            throw new IllegalArgumentException("Conditional passed count cannot be negative");
        }
        this.conditionalPassed = conditionalPassed;
        calculateRates();
    }

    public int getDefectsFound() {
        return defectsFound;
    }

    public void setDefectsFound(int defectsFound) {
        if (defectsFound < 0) {
            throw new IllegalArgumentException("Defects found cannot be negative");
        }
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
        if (criticalDefects < 0) {
            throw new IllegalArgumentException("Critical defects cannot be negative");
        }
        this.criticalDefects = criticalDefects;
    }

    public int getMajorDefects() {
        return majorDefects;
    }

    public void setMajorDefects(int majorDefects) {
        if (majorDefects < 0) {
            throw new IllegalArgumentException("Major defects cannot be negative");
        }
        this.majorDefects = majorDefects;
    }

    public int getMinorDefects() {
        return minorDefects;
    }

    public void setMinorDefects(int minorDefects) {
        if (minorDefects < 0) {
            throw new IllegalArgumentException("Minor defects cannot be negative");
        }
        this.minorDefects = minorDefects;
    }

    public Map<String, Integer> getDefectByCategory() {
        return defectByCategory;
    }

    public void setDefectByCategory(Map<String, Integer> defectByCategory) {
        this.defectByCategory = defectByCategory;
    }

    public void addDefectCategory(String category, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Defect count cannot be negative");
        }
        this.defectByCategory.put(category, count);
    }

    public double getAvgRepairTime() {
        return avgRepairTime;
    }

    public void setAvgRepairTime(double avgRepairTime) {
        if (avgRepairTime < 0) {
            throw new IllegalArgumentException("Average repair time cannot be negative");
        }
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
        if (reworkCount < 0) {
            throw new IllegalArgumentException("Rework count cannot be negative");
        }
        this.reworkCount = reworkCount;
    }

    public int getScrapCount() {
        return scrapCount;
    }

    public void setScrapCount(int scrapCount) {
        if (scrapCount < 0) {
            throw new IllegalArgumentException("Scrap count cannot be negative");
        }
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

    public void calculateQualityScore() {
        if (totalInspected == 0) {
            this.qualityScore = "N/A";
            return;
        }
        double passRate = ((double) passed / totalInspected) * 100;
        if (passRate >= 95) {
            this.qualityScore = "A";
        } else if (passRate >= 85) {
            this.qualityScore = "B";
        } else if (passRate >= 75) {
            this.qualityScore = "C";
        } else if (passRate >= 60) {
            this.qualityScore = "D";
        } else {
            this.qualityScore = "F";
        }
    }

    public int getDefectsTotal() {
        return criticalDefects + majorDefects + minorDefects;
    }

    public double getPassPercentage() {
        return passRate;
    }

    public double getFailPercentage() {
        return 100 - passRate;
    }

    public boolean isAcceptableQuality() {
        return passRate >= 85;
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