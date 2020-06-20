package MyBank;

public class Accounts {
	private static int accountBase = 1000;
	private int accountNumber;
	private double balance;
	private double interest;
	
	Accounts() {
		// accountBase is 1000, the first account created will start with this number
		// the next account created will be 1000 + 1 which is 1001
		// and so on...
		accountNumber = accountBase++;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getInterest() {
		return interest * 100;
	}
	
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	// Interest rates
	public void checkInterest() {
		// Users gets a larger interest rate for having a larger balance
		if(balance > 10000) {
			interest = 0.03;
		}
		else {
			interest = 0.01;
		}
	}
	
	// Deposit Method
	public void deposit(double amount) {
		if(amount <= 0){
			System.out.println("Error. Please deposit a valid amount.");
			return;
		}
		checkInterest(); // Method called to check and adjust interest depending on balance
		amount = amount + (amount * interest);
		balance += amount;
		System.out.println("You have deposited $" + amount + " (You have an interest rate of " + (interest * 100) + "%)");
		System.out.println("You now have a balance of $" + balance);
	}
	
	// Withdraw Method
	public void withdraw(double amount) {
		if(amount > balance) {
			System.out.println("You have insufficient funds.");
			return;
		}
		balance -= amount;
		System.out.println("You have withdrawn $" + amount);
		System.out.println("You now have a balance of $" + balance);
	}
}