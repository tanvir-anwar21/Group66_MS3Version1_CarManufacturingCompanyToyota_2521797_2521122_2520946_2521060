package Tawsif.Models;

import java.time.LocalDate;

public class Payroll {
    private String payrollId;
    private String employeeId;
    private double basicSalary;
    private double bonus;
    private double deduction;
    private double netSalary;
    private LocalDate paymentDate;

    public Payroll(String payrollId, String employeeId, double basicSalary, double bonus, double deduction, double netSalary, LocalDate paymentDate) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.deduction = deduction;
        this.netSalary = netSalary;
        this.paymentDate = paymentDate;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "payrollId='" + payrollId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", basicSalary=" + basicSalary +
                ", bonus=" + bonus +
                ", deduction=" + deduction +
                ", netSalary=" + netSalary +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
