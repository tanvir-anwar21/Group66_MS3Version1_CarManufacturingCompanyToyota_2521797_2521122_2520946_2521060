package Tawsif.Models;

import java.time.LocalDate;

public class HRReport {
    private String reportId;
    private String reportType;
    private LocalDate reportDate;
    private String generatedBy;

    public HRReport(String reportId, String reportType, LocalDate reportDate, String generatedBy) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.reportDate = reportDate;
        this.generatedBy = generatedBy;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    @Override
    public String toString() {
        return "HRReport{" +
                "reportId='" + reportId + '\'' +
                ", reportType='" + reportType + '\'' +
                ", reportDate=" + reportDate +
                ", generatedBy='" + generatedBy + '\'' +
                '}';
    }
}
