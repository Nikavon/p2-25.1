import java.util.Locale;
import java.util.Scanner;

public class BankAccount {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter account data:");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Holder: ");
        sc.nextLine();
        String holder = sc.nextLine();
        System.out.print("Initial balance: ");
        double balance = sc.nextDouble();
        System.out.print("Withdraw limit: ");
        double withdrawLimit = sc.nextDouble();
        
        Account account = new Account(number, holder, balance, withdrawLimit);
        
        System.out.print("\nEnter amount for withdraw: ");
        double amount = sc.nextDouble();
        
        try {
            account.withdraw(amount);
            System.out.printf("New balance: %.2f%n", account.getBalance());
        } catch (InsufficientBalanceException e) {
            System.out.println("Withdraw error: " + e.getMessage());
        } catch (WithdrawLimitExceededException e) {
            System.out.println("Withdraw error: " + e.getMessage());
        }
        
        sc.close();
    }
}

class Account {
    private int number;
    private String holder;
    private double balance;
    private double withdrawLimit;
    
    public Account(int number, String holder, double balance, double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (amount > withdrawLimit) {
            throw new WithdrawLimitExceededException("The amount exceeds withdraw limit");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Not enough balance");
        }
        balance -= amount;
    }
    
    public double getBalance() {
        return balance;
    }
}

class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class WithdrawLimitExceededException extends RuntimeException {
    public WithdrawLimitExceededException(String message) {
        super(message);
    }
}