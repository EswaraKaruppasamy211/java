import java.util.Scanner;

class Employee {
    String empName, address, mailId, mobileNo;
    int empId;
    double basicPay;

    Employee(String name, int id, String address, String mail, String mobile, double bp) {
        empName = name;
        empId = id;
        this.address = address;
        mailId = mail;
        mobileNo = mobile;
        basicPay = bp;
    }

    void calculateSalary(double daPer, double hraPer, double pfPer, double staffPer) {
        double da = basicPay * daPer / 100;
        double hra = basicPay * hraPer / 100;
        double pf = basicPay * pfPer / 100;
        double staff = basicPay * staffPer / 100;

        double gross = basicPay + da + hra;
        double net = gross - pf - staff;

        System.out.println("\nEmployee Name : " + empName);
        System.out.println("Employee ID   : " + empId);
        System.out.println("Basic Pay     : " + basicPay);
        System.out.println("DA            : " + da);
        System.out.println("HRA           : " + hra);
        System.out.println("PF            : " + pf);
        System.out.println("Staff Club    : " + staff);
        System.out.println("Gross Salary  : " + gross);
        System.out.println("Net Salary    : " + net);
    }
}

class Programmer extends Employee {
    Programmer(String name, int id, String address, String mail, String mobile, double bp) {
        super(name, id, address, mail, mobile, bp);
    }

    void calculateSalary() {
        super.calculateSalary(97, 10, 12, 1);
    }
}

class AssistantProfessor extends Employee {
    AssistantProfessor(String name, int id, String address, String mail, String mobile, double bp) {
        super(name, id, address, mail, mobile, bp);
    }

    void calculateSalary() {
        super.calculateSalary(110, 20, 12, 5);
    }
}

class AssociateProfessor extends Employee {
    AssociateProfessor(String name, int id, String address, String mail, String mobile, double bp) {
        super(name, id, address, mail, mobile, bp);
    }

    void calculateSalary() {
        super.calculateSalary(130, 30, 12, 10);
    }
}

class Professor extends Employee {
    Professor(String name, int id, String address, String mail, String mobile, double bp) {
        super(name, id, address, mail, mobile, bp);
    }

    void calculateSalary() {
        super.calculateSalary(140, 40, 12, 15);
    }
}

public class EmployeeSalary {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Mail ID: ");
        String mail = sc.nextLine();

        System.out.print("Enter Mobile No: ");
        String mobile = sc.nextLine();

        System.out.print("Enter Designation: ");
        String des = sc.nextLine();

        System.out.print("Enter Basic Pay: ");
        double bp = sc.nextDouble();

        Employee emp = null;

        switch (des.toLowerCase()) {
            case "programmer":
                emp = new Programmer(name, id, address, mail, mobile, bp);
                break;

            case "assistantprofessor":
                emp = new AssistantProfessor(name, id, address, mail, mobile, bp);
                break;

            case "associateprofessor":
                emp = new AssociateProfessor(name, id, address, mail, mobile, bp);
                break;

            case "professor":
                emp = new Professor(name, id, address, mail, mobile, bp);
                break;

            default:
                System.out.println("Invalid Designation!");
                sc.close();
                return;
        }

        if (emp instanceof Programmer)
            ((Programmer) emp).calculateSalary();
        else if (emp instanceof AssistantProfessor)
            ((AssistantProfessor) emp).calculateSalary();
        else if (emp instanceof AssociateProfessor)
            ((AssociateProfessor) emp).calculateSalary();
        else if (emp instanceof Professor)
            ((Professor) emp).calculateSalary();

        sc.close();
    }
}