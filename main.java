import java.util.HashMap;
import java.util.Scanner;

// Class to represent a bank account
class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    public BankAccount(String name, String number) {
        this.accountHolderName = name;
        this.accountNumber = number;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        }
    }

    public void showBalance() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Available Balance: ₹" + balance);
    }
}

// Main class to manage bank system operations
public class BankingSystemApp {
    private static HashMap<String, BankAccount> accountMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        System.out.println("====================================");
        System.out.println("     WELCOME TO THE BANK PORTAL     ");
        System.out.println("====================================");

        do {
            showMenu();
            choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    depositToAccount();
                    break;
                case 3:
                    withdrawFromAccount();
                    break;
                case 4:
                    checkAccountBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using the Bank App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }

        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Account Balance");
        System.out.println("5. Exit");
        System.out.println("----------------------------------");
    }

    private static void createNewAccount() {
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter a new Account Number: ");
        String accNo = scanner.nextLine();

        if (accountMap.containsKey(accNo)) {
            System.out.println(" Account number already exists. Please try a different number.");
            return;
        }

        BankAccount newAccount = new BankAccount(name, accNo);
        accountMap.put(accNo, newAccount);

        System.out.println(" Account created successfully!");
    }

    private static void depositToAccount() {
        String accNo = getAccountNumberInput();

        BankAccount account = accountMap.get(accNo);
        if (account != null) {
            double amount = getDoubleInput("Enter amount to deposit: ₹");
            account.deposit(amount);
        } else {
            System.out.println(" Account not found.");
        }
    }

    private static void withdrawFromAccount() {
        String accNo = getAccountNumberInput();

        BankAccount account = accountMap.get(accNo);
        if (account != null) {
            double amount = getDoubleInput("Enter amount to withdraw: ₹");
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkAccountBalance() {
        String accNo = getAccountNumberInput();

        BankAccount account = accountMap.get(accNo);
        if (account != null) {
            account.showBalance();
        } else {
            System.out.println(" Account not found.");
        }
    }

    // Helper methods to safely get input
    private static int getIntegerInput(String message) {
        int input = -1;
        while (true) {
            try {
                System.out.print(message);
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(" Please enter a valid number.");
            }
        }
        return input;
    }

    private static double getDoubleInput(String message) {
        double input = -1;
        while (true) {
            try {
                System.out.print(message);
                input = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("  Please enter a valid amount.");
            }
        }
        return input;
    }

    private static String getAccountNumberInput() {
        System.out.print("Enter your Account Number: ");
        return scanner.nextLine();
    }
}
