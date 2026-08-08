package Tanvir.Model_Class;

public class Budget {

    private final String budgetId;
    private String department;
    private float proposedAmount;
    private float projectedRevenue;
    private float projectedExpenses;
    private float budgetAllocation;
    private String status;

    public Budget(String budgetId,
                  String department,
                  float proposedAmount,
                  float projectedRevenue,
                  float projectedExpenses,
                  float budgetAllocation) {

        this.budgetId = budgetId;
        this.department = department;
        this.proposedAmount = proposedAmount;
        this.projectedRevenue = projectedRevenue;
        this.projectedExpenses = projectedExpenses;
        this.budgetAllocation = budgetAllocation;
        this.status = "Pending";
    }

    public final String getBudgetId() {
        return budgetId;
    }

    public final String getDepartment() {
        return department;
    }

    public final float getProposedAmount() {
        return proposedAmount;
    }

    public final float getProjectedRevenue() {
        return projectedRevenue;
    }

    public final float getProjectedExpenses() {
        return projectedExpenses;
    }

    public final float getBudgetAllocation() {
        return budgetAllocation;
    }

    public final String getStatus() {
        return status;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProposedAmount(float proposedAmount) {
        this.proposedAmount = proposedAmount;
    }

    public void setProjectedRevenue(float projectedRevenue) {
        this.projectedRevenue = projectedRevenue;
    }

    public void setProjectedExpenses(float projectedExpenses) {
        this.projectedExpenses = projectedExpenses;
    }

    public void setBudgetAllocation(float budgetAllocation) {
        this.budgetAllocation = budgetAllocation;
    }

    public final void approve() {
        status = "Approved";
    }

    public final void reject() {
        status = "Rejected";
    }
}

