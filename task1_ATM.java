import java.util.*;

public class task1_ATM {

    public static class ATM {
        String UserID = "Name";
        int PIN = 1234;

        public void verifyUser() {
            // taking inputs from user
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter User ID: ");
            String enteredUserId = sc.nextLine();
            System.out.println("Enter your pin: ");
            int enteredPin = sc.nextInt();

            // verify user
            if (enteredUserId.equals(UserID) && enteredPin == PIN) {
                menu();
            } else if (enteredUserId.equals(UserID)) {
                System.out.println("Enter a valid pin!");
            } else if (enteredPin == PIN) {
                System.out.println("Enter a correct User-ID!");
            } else {
                System.out.println("Enter a valid User-ID and PIN!");
            }
            sc.close();
        }

        // displaying menu
        public static void menu() {
            System.out.println("Enter Your Choice: ");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Quit");
            System.out.println("---------------------------");
            Scanner sc = new Scanner(System.in);
            int opt = sc.nextInt();

            // choosing from menu
            if (opt == 1) {
                BankAccount.getTransactionHistory();
            } else if (opt == 2) {
                BankAccount.withdraw();
            } else if (opt == 3) {
                BankAccount.deposit();
            } else if (opt == 4) {
                BankAccount account1 = new BankAccount("123", 1000.0);
                account1.transfer("1234567", 500);
            } else if (opt == 5) {
                System.out.println("Thank you for using the ATM!");
                sc.close();
                return;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
                menu();
            }
            sc.close();
        }
    }

    // BankAccount class representing a bank account
    public static class BankAccount {
        private String accountNumber;
        private static double balance;
        static List<String> transactionHistory = new ArrayList<>();

        double initialBalance;
        public BankAccount(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            initialBalance = getBalance();
            initialBalance = balance;
        }

        // Method to transfer money from this account to another account
        public void transfer(String destinationAccount, double amount) {
            if (amount > 0 && amount <= initialBalance) {
                initialBalance -= amount;
                transactionHistory.add("Transferred $" + amount + " to account number: " + destinationAccount + " from account number: " + accountNumber);
                System.out.println("Transferred $" + amount + " to account number: " + destinationAccount + " from account number: " + accountNumber);
            } else {
                System.out.println("Invalid amount or insufficient funds for transfer.");
            }
            System.out.println(initialBalance);
            ATM.menu();
        }

        // Method to get the balance
        public double getBalance() {
            return balance;
        }

        // Method to get transaction details
        public static List<String> getTransactionHistory() {
            if (transactionHistory != null && !transactionHistory.isEmpty()) {
                System.out.println("Transaction History:");
                for (String transaction : transactionHistory) {
                    System.out.println(transaction);
                }
            } else {
                System.out.println("No transactions found.");
            }
            System.out.println();
            ATM.menu();
            return transactionHistory;
        }

        // Method to deposit money into the account
        public static void deposit() {
            System.out.println("Enter the Amount you want to Deposit: ");
            Scanner sc = new Scanner(System.in);
            double amount = sc.nextDouble();
            if (amount > 0) {
                balance += amount;
                if (transactionHistory != null) {
                    transactionHistory.add("Deposited: $" + amount);
                }
                System.out.println(" Successfully Deposited: $" + amount);
            } else {
                System.out.println("Invalid amount. Please deposit a positive amount.");
            }
            System.out.println();
            ATM.menu();
            sc.close();
        }

        // Method to withdraw money from the account
        public static void withdraw() {
            System.out.println("Enter Amount to Withdraw: ");
            Scanner sc = new Scanner(System.in);
            double amount = sc.nextDouble();
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                if (transactionHistory != null) {
                    transactionHistory.add("Withdrawn: $" + amount);
                }
                System.out.println("Successfully Withdrawn: $" + amount);
            } else {
                System.out.println("Insufficient funds or invalid amount for withdrawal.");
            }
            System.out.println();
            ATM.menu();
            sc.close();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome To ATM");
        ATM obj = new ATM();
        obj.verifyUser();
    }
}