package Tanvir.Model_Class;

public class FinancialSummary {

    private final String category;
    private float income;
    private float expense;
    private float balance;
    private String remarks;

    public FinancialSummary(String category, float income, float expense,
                            float balance, String remarks) {

        this.category = category;
        this.income = income;
        this.expense = expense;
        this.balance = balance;
        this.remarks = remarks;
    }

    public String getCategory() {
        return category;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getExpense() {
        return expense;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "FinancialSummary{" +
                "category='" + category + '\'' +
                ", income=" + income +
                ", expense=" + expense +
                ", balance=" + balance +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
