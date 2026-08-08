package Tanvir.Model_Class;

public class DepartmentPerformance {

    private final String department;
    private final float target;
    private final float achievement;
    private String status;

    public DepartmentPerformance(String department,
                                 float target,
                                 float achievement,
                                 String status) {

        this.department = department;
        this.target = target;
        this.achievement = achievement;
        this.status = status;
    }

    public final String getDepartment() {
        return department;
    }

    public final float getTarget() {
        return target;
    }

    public final float getAchievement() {
        return achievement;
    }

    public final String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}