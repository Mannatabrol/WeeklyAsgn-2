//Q)Develop an employee payroll management system. Define abstract classes for Employee
// and concrete subclasses for different employee types like FullTimeEmployee and ContractEmployee.
// Implement interfaces for calculations like Payable and Taxable. Utilize collections to store employee records.


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface for Payable
interface Payable {
    double calculateSalary();
}

// Interface for Taxable
interface Taxable {
    double calculateTax();
}

// Abstract class for Employee implementing Payable and Taxable interfaces
abstract class Employee implements Payable, Taxable {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + "]";
    }
}

// Concrete subclass for FullTimeEmployee
class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }

    @Override
    public double calculateTax() {
        return 0.15 * monthlySalary; // Dummy tax calculation
    }
}

// Concrete subclass for PartTimeEmployee
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }

    @Override
    public double calculateTax() {
        // Implement tax calculation logic for PartTimeEmployee
        return 0.1 * calculateSalary(); // Dummy tax calculation
    }
}

// PayrollSystem class to manage employees
class PayrollSystem {
    private List<Employee> employeeList;


    //Collections used
    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        employeeList.removeIf(employee -> employee.getId() == id);
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public void displayEmployeeSalaries() {
        for (Employee employee : employeeList) {
            System.out.println("Employee: " + employee.getName() + ", Salary: " + employee.calculateSalary());
        }
    }

    public void displayEmployeeTaxes() {
        for (Employee employee : employeeList) {
            System.out.println("Employee: " + employee.getName() + ", Tax: " + employee.calculateTax());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        Scanner scanner = new Scanner(System.in);

        // Input for Full-Time Employee
        System.out.println("Enter details for Full-Time Employee:");
        System.out.print("Enter name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter id: ");
        int id1 = scanner.nextInt();
        System.out.print("Enter monthly salary: ");
        double monthlySalary = scanner.nextDouble();
        FullTimeEmployee emp1 = new FullTimeEmployee(name1, id1, monthlySalary);
        payrollSystem.addEmployee(emp1);

        // Input for Part-Time Employee
        System.out.println("Enter details for Part-Time Employee:");
        scanner.nextLine(); // Consume newline
        System.out.print("Enter name: ");
        String name2 = scanner.nextLine();
        System.out.print("Enter id: ");
        int id2 = scanner.nextInt();
        System.out.print("Enter hours worked: ");
        int hoursWorked = scanner.nextInt();
        System.out.print("Enter hourly rate: ");
        double hourlyRate = scanner.nextDouble();
        PartTimeEmployee emp2 = new PartTimeEmployee(name2, id2, hoursWorked, hourlyRate);
        payrollSystem.addEmployee(emp2);

        // Display Employee Details
        System.out.println("Employee Details:");
        payrollSystem.displayEmployees();

        // Display Employee Salaries
        System.out.println("\nEmployee Salaries:");
        payrollSystem.displayEmployeeSalaries();

        // Display Employee Taxes
        System.out.println("\nEmployee Taxes:");
        payrollSystem.displayEmployeeTaxes();

        scanner.close();
    }
}
