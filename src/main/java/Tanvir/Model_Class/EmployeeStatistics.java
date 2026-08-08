package Tanvir.Model_Class;

public class EmployeeStatistics {

        private final String department;
        private int employeeCount;
        private float attendance;
        private float performance;
        private String remarks;

        public EmployeeStatistics(String department,
                                  int employeeCount,
                                  float attendance,
                                  float performance,
                                  String remarks) {

            this.department = department;
            this.employeeCount = employeeCount;
            this.attendance = attendance;
            this.performance = performance;
            this.remarks = remarks;
        }

        // Getter for department

        public String getDepartment() {
            return department;
        }

        // Getter and Setter for employee count

        public int getEmployeeCount() {
            return employeeCount;
        }

        public void setEmployeeCount(int employeeCount) {
            this.employeeCount = employeeCount;
        }

        // Getter and Setter for attendance

        public float getAttendance() {
            return attendance;
        }

        public void setAttendance(float attendance) {
            this.attendance = attendance;
        }

        // Getter and Setter for performance

        public float getPerformance() {
            return performance;
        }

        public void setPerformance(float performance) {
            this.performance = performance;
        }

        // Getter and Setter for remarks

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        @Override
        public String toString() {
            return "EmployeeStatistics{" +
                    "department='" + department + '\'' +
                    ", employeeCount=" + employeeCount +
                    ", attendance=" + attendance +
                    ", performance=" + performance +
                    ", remarks='" + remarks + '\'' +
                    '}';
        }
    }

