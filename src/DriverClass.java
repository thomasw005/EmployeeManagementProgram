import java.util.ArrayList;
import java.util.Objects;

public class DriverClass {
    public static void main(String[] args) {
        String fullName = "Erika T. Jones";
        String employeeNumber = "ej789";
        double payRate = 100.0, hoursWorked = 1.0;
// TA will change the payrate and the hours worked to test your code
        Employee e;
        e = new Employee(fullName, employeeNumber, payRate, hoursWorked);
        System.out.println(e); // To Test your toString method
        e.printCheck(); // This prints the check of Erika T. Jones
        Company company = new Company();
        company.hire ( new Employee ("Saeed Happy", "sh895" , 2 , 200) );
        company.hire (e);
        company.printCompanyInfo();
        company.hire( new Employee("Enrico Torres" , "et897" , 3 , 150) );
//You may add as many employees to company as you want.
//The TAs will add their own employees
//Make sure that each employee of company has a unique employeeNumber
        company.printCheck("ab784");
        company.deleteEmployeesBySalary(256.36);
        company.reverseEmployees();
        System.out.println( company.SearchByName("WaLiD WiLLiAms") );
        company.printEmployees();
        System.out.println("Bye!");
    }
}

class Employee {
    private String fullName;
    private String employeeNumber;
    private double payRate;
    private double hoursWorked;
    private double grossPay = payRate * hoursWorked;
    private double taxAmount = grossPay * 0.06;

    public Employee(String fullName, String employeeNumber, double payRate, double hoursWorked) {
        this.fullName = fullName;
        this.employeeNumber = employeeNumber;
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public double getPayRate() {
        return payRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String toString() {
        return "[" + employeeNumber + "/" + fullName + ", " + hoursWorked + " Hours @ " + payRate + " per hour]";
    }

    private double netPay() {
        return (grossPay - taxAmount);
    }

    public void printCheck() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("\t Employee's name: " + fullName);
        System.out.println("\t Employee's number: " + employeeNumber);
        System.out.println("\t Hourly rate of pay: " + payRate);
        System.out.println("\t Hours worked: " + hoursWorked);

        System.out.println();

        System.out.println("\t Total Gross Pay: " + "$" + grossPay);

        System.out.println();

        System.out.println("\t Deductions");
        System.out.println("\t Tax (6 %): " + "$" + taxAmount);

        System.out.println();

        System.out.println("\t Net Pay: " + netPay());
        System.out.println("---------------------------------------------------------------------------");
    }
}

class Company {
    private ArrayList<Employee> employeeList;
    private String companyName;
    private static String companyTaxId;

    public Company() {
        employeeList = new ArrayList<>();
        companyName = "People's Place";
        companyTaxId = "v1rtua7C0mpan1";
    }

    public static String getCompanyTaxId() {
        return companyTaxId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyTaxId(String companyTaxId) {
        this.companyTaxId = companyTaxId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean hire (Employee employee ) {
        for (Employee currentEmployee : employeeList) {
            if (Objects.equals(currentEmployee.getEmployeeNumber(), employee.getEmployeeNumber())) {
                return false;
            }
        }

        employeeList.add(employee);
        return true;
    }

    public void printCompanyInfo() {
        System.out.println("Company Name: " + companyName);
        System.out.println("Company Tax ID: " + companyTaxId);
        System.out.println("Number of Employees: " + employeeList.size());
    }

    public void printEmployees() {
        for (Employee currentEmployee : employeeList) {
            System.out.println(currentEmployee);
        }
    }

    public int countEmployees( double maxSalary ) {
        int count = 0;
        for (Employee currentEmployee : employeeList) {
            if (currentEmployee.getGrossPay() < maxSalary) {
                count++;
            }
        }
        return count;
    }

    public boolean SearchByName (String fullName ) {
        for (Employee currentEmployee : employeeList) {
            if (currentEmployee.getFullName().equalsIgnoreCase(fullName)) {
                return true;
            }
        }
        return false;
    }

    public void reverseEmployees () {
        int left = 0;
        int right = employeeList.size() - 1;

        while (left < right) {
            Employee temp = employeeList.get(left);
            employeeList.set(left, employeeList.get(right));
            employeeList.set(right, temp);

            left++;
            right--;
        }
    }

    public void deleteEmployeesBySalary (double targetSalary ) {
        employeeList.removeIf(employee -> employee.getGrossPay() == targetSalary);
    }

    public void printCheck ( String employeeNumber) {
        for (Employee employee : employeeList) {
            if (String.valueOf(employee.getEmployeeNumber()).equals(employeeNumber)) {
                employee.printCheck();
                return;
            }
        }
        System.out.println("NO SUCH EMPLOYEE EXISTS");
    }
}