package Sohan.ModelClasses.QualityControlManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkerPerformance implements Serializable {
    private static final long serialVersionUID = 1L;

    private String workerID;
    private String workerName;
    private String department;
    private String position;
    private LocalDate reviewDate;
    private int qualityScore;
    private int productsChecked;
    private int defectsFound;
    private int defectsMissed;
    private double accuracyRate;
    private double efficiencyRate;
    private String performanceRating;
    private List<String> feedbackComments;
    private String status;
    private int inspectionsCompleted;
    private double averageTimePerInspection;
    private int reworkCount;
    private String supervisorID;
    private String supervisorName;

    // Default Constructor
    public WorkerPerformance() {
        this.feedbackComments = new ArrayList<>();
        this.performanceRating = "Average";
        this.status = "Active";
        this.qualityScore = 0;
        this.accuracyRate = 0.0;
        this.efficiencyRate = 0.0;
        this.reviewDate = LocalDate.now();
    }

    // Parameterized Constructor
    public WorkerPerformance(String workerID, String workerName, String department, String position) {
        this();
        this.workerID = workerID;
        this.workerName = workerName;
        this.department = department;
        this.position = position;
    }

    // Getters and Setters
    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(int qualityScore) {
        this.qualityScore = qualityScore;
        updatePerformanceRating();
    }

    public int getProductsChecked() {
        return productsChecked;
    }

    public void setProductsChecked(int productsChecked) {
        this.productsChecked = productsChecked;
    }

    public int getDefectsFound() {
        return defectsFound;
    }

    public void setDefectsFound(int defectsFound) {
        this.defectsFound = defectsFound;
    }

    public int getDefectsMissed() {
        return defectsMissed;
    }

    public void setDefectsMissed(int defectsMissed) {
        this.defectsMissed = defectsMissed;
    }

    public double getAccuracyRate() {
        return accuracyRate;
    }

    public void setAccuracyRate(double accuracyRate) {
        this.accuracyRate = accuracyRate;
    }

    public double getEfficiencyRate() {
        return efficiencyRate;
    }

    public void setEfficiencyRate(double efficiencyRate) {
        this.efficiencyRate = efficiencyRate;
    }

    public String getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(String performanceRating) {
        this.performanceRating = performanceRating;
    }

    public List<String> getFeedbackComments() {
        return feedbackComments;
    }

    public void setFeedbackComments(List<String> feedbackComments) {
        this.feedbackComments = feedbackComments;
    }

    public void addFeedbackComment(String comment) {
        this.feedbackComments.add(comment);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInspectionsCompleted() {
        return inspectionsCompleted;
    }

    public void setInspectionsCompleted(int inspectionsCompleted) {
        this.inspectionsCompleted = inspectionsCompleted;
    }

    public double getAverageTimePerInspection() {
        return averageTimePerInspection;
    }

    public void setAverageTimePerInspection(double averageTimePerInspection) {
        this.averageTimePerInspection = averageTimePerInspection;
    }

    public int getReworkCount() {
        return reworkCount;
    }

    public void setReworkCount(int reworkCount) {
        this.reworkCount = reworkCount;
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    private void updatePerformanceRating() {
        if (qualityScore >= 90) {
            this.performanceRating = "Excellent";
        } else if (qualityScore >= 80) {
            this.performanceRating = "Good";
        } else if (qualityScore >= 70) {
            this.performanceRating = "Average";
        } else if (qualityScore >= 60) {
            this.performanceRating = "Below Average";
        } else {
            this.performanceRating = "Needs Improvement";
        }
    }

    public double getDefectDetectionRate() {
        int total = defectsFound + defectsMissed;
        if (total == 0) return 0.0;
        return ((double) defectsFound / total) * 100;
    }

    public boolean isTopPerformer() {
        return "Excellent".equals(performanceRating);
    }

    public boolean needsImprovement() {
        return "Needs Improvement".equals(performanceRating) || "Below Average".equals(performanceRating);
    }

    @Override
    public String toString() {
        return "WorkerPerformance{" +
                "workerID='" + workerID + '\'' +
                ", workerName='" + workerName + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", qualityScore=" + qualityScore +
                ", performanceRating='" + performanceRating + '\'' +
                ", accuracyRate=" + accuracyRate +
                ", efficiencyRate=" + efficiencyRate +
                '}';
    }
}