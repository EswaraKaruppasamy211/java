import java.util.Scanner;


interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientBalanceException;
    void balanceEnquiry();
}


class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}


class BankAccount implements BankOperations {

    String accountHolderName;
    int accountNumber;
    double balance;

    BankAccount(String name, int number, double initialBalance) {
        accountHolderName = name;
        accountNumber = number;
        balance = initialBalance;
    }

    
    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Deposit Amount");
        }

        balance = balance + amount;

        System.out.println("Amount Deposited Successfully");
        System.out.println("Available Balance: Rs. " + balance);
    }

    
    public void withdraw(double amount)
            throws InsufficientBalanceException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Withdrawal Amount");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient Balance");
        }

        balance = balance - amount;

        System.out.println("Amount Withdrawn Successfully");
        System.out.println("Available Balance: Rs. " + balance);
    }

    
    public void balanceEnquiry() {
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Available Balance: Rs. " + balance);
    }
}


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        int number = sc.nextInt();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = sc.nextDouble();

        BankAccount account =
                new BankAccount(name, number, initialBalance);

        int choice;

        
        while (true) {

           
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Exit");
            System.out.print("Select Operation: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Enter Deposit Amount: ");
                        double depositAmount = sc.nextDouble();

                        account.deposit(depositAmount);

                    } catch (IllegalArgumentException e) {
                        System.out.println("Exception: "
                                + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter Withdrawal Amount: ");
                        double withdrawalAmount = sc.nextDouble();

                        account.withdraw(withdrawalAmount);

                    } catch (InsufficientBalanceException e) {
                        System.out.println("Exception: "
                                + e.getMessage());

                    } catch (IllegalArgumentException e) {
                        System.out.println("Exception: "
                                + e.getMessage());
                    }
                    break;

                case 3:
                    account.balanceEnquiry();
                    break;

                case 4:
                    System.out.println(
                            "Thank you for using Bank Services!");
                    sc.close();
                    return;

                default:
                    System.out.println(
                            "Invalid Choice! Please select 1 to 4.");
            }
        }
    }
}