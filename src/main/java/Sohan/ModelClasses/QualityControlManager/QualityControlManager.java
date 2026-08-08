package Sohan.ModelClasses.QualityControlManager;

import Utility.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QualityControlManager extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    // QC Specific Fields
    private String qcCertification;
    private String assignedDepartment;
    private int yearsOfExperience;
    private List<String> specializations;
    private double qualityScore;
    private int totalInspectionsCompleted;
    private int defectsReported;
    private int vehiclesApproved;
    private int vehiclesRejected;
    private String qcLevel;
    private String shiftTiming;
    private int teamSize;

    // Default Constructor
    public QualityControlManager() {
        super();
        this.specializations = new ArrayList<>();
        this.qcCertification = "ISO 9001";
        this.assignedDepartment = "Quality Assurance";
        this.yearsOfExperience = 0;
        this.qualityScore = 0.0;
        this.totalInspectionsCompleted = 0;
        this.defectsReported = 0;
        this.vehiclesApproved = 0;
        this.vehiclesRejected = 0;
        this.qcLevel = "Senior";
        this.shiftTiming = "Day";
        this.teamSize = 5;
        setRole("QualityControlManager");
    }

    // Parameterized Constructor
    public QualityControlManager(String userId, String fullName, String email,
                                 String password, String phoneNumber, String role,
                                 String qcCertification, String assignedDepartment,
                                 int yearsOfExperience) {
        super(userId, fullName, email, password, phoneNumber, role);
        this.qcCertification = qcCertification;
        this.assignedDepartment = assignedDepartment;
        this.yearsOfExperience = yearsOfExperience;
        this.specializations = new ArrayList<>();
        this.qualityScore = 0.0;
        this.totalInspectionsCompleted = 0;
        this.defectsReported = 0;
        this.vehiclesApproved = 0;
        this.vehiclesRejected = 0;
        this.qcLevel = "Senior";
        this.shiftTiming = "Day";
        this.teamSize = 5;
    }

    // Simplified Constructor
    public QualityControlManager(String userId, String fullName, String email,
                                 String password, String phoneNumber) {
        super(userId, fullName, email, password, phoneNumber, "QualityControlManager");
        this.specializations = new ArrayList<>();
        this.qcCertification = "ISO 9001";
        this.assignedDepartment = "Quality Assurance";
        this.yearsOfExperience = 0;
        this.qualityScore = 0.0;
        this.totalInspectionsCompleted = 0;
        this.defectsReported = 0;
        this.vehiclesApproved = 0;
        this.vehiclesRejected = 0;
        this.qcLevel = "Senior";
        this.shiftTiming = "Day";
        this.teamSize = 5;
    }

    // Getters and Setters
    public String getQcCertification() {
        return qcCertification;
    }

    public void setQcCertification(String qcCertification) {
        this.qcCertification = qcCertification;
    }

    public String getAssignedDepartment() {
        return assignedDepartment;
    }

    public void setAssignedDepartment(String assignedDepartment) {
        this.assignedDepartment = assignedDepartment;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public void addSpecialization(String specialization) {
        if (!this.specializations.contains(specialization)) {
            this.specializations.add(specialization);
        }
    }

    public double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public int getTotalInspectionsCompleted() {
        return totalInspectionsCompleted;
    }

    public void setTotalInspectionsCompleted(int totalInspectionsCompleted) {
        this.totalInspectionsCompleted = totalInspectionsCompleted;
    }

    public void incrementInspectionsCompleted() {
        this.totalInspectionsCompleted++;
    }

    public int getDefectsReported() {
        return defectsReported;
    }

    public void setDefectsReported(int defectsReported) {
        this.defectsReported = defectsReported;
    }

    public void incrementDefectsReported() {
        this.defectsReported++;
    }

    public int getVehiclesApproved() {
        return vehiclesApproved;
    }

    public void setVehiclesApproved(int vehiclesApproved) {
        this.vehiclesApproved = vehiclesApproved;
    }

    public void incrementVehiclesApproved() {
        this.vehiclesApproved++;
    }

    public int getVehiclesRejected() {
        return vehiclesRejected;
    }

    public void setVehiclesRejected(int vehiclesRejected) {
        this.vehiclesRejected = vehiclesRejected;
    }

    public void incrementVehiclesRejected() {
        this.vehiclesRejected++;
    }

    public String getQcLevel() {
        return qcLevel;
    }

    public void setQcLevel(String qcLevel) {
        this.qcLevel = qcLevel;
    }

    public String getShiftTiming() {
        return shiftTiming;
    }

    public void setShiftTiming(String shiftTiming) {
        this.shiftTiming = shiftTiming;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    // Business Methods
    public double getPassRate() {
        int total = vehiclesApproved + vehiclesRejected;
        if (total == 0) return 0.0;
        return ((double) vehiclesApproved / total) * 100;
    }

    public double getDefectDetectionRate() {
        if (totalInspectionsCompleted == 0) return 0.0;
        return ((double) defectsReported / totalInspectionsCompleted) * 100;
    }

    @Override
    public String toString() {
        return "QualityControlManager{" +
                "userId='" + getUserId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", role='" + getRole() + '\'' +
                ", qcCertification='" + qcCertification + '\'' +
                ", assignedDepartment='" + assignedDepartment + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", specializations=" + specializations +
                ", qualityScore=" + qualityScore +
                ", totalInspectionsCompleted=" + totalInspectionsCompleted +
                ", defectsReported=" + defectsReported +
                ", vehiclesApproved=" + vehiclesApproved +
                ", vehiclesRejected=" + vehiclesRejected +
                ", qcLevel='" + qcLevel + '\'' +
                ", shiftTiming='" + shiftTiming + '\'' +
                ", teamSize=" + teamSize +
                '}';
    }
}