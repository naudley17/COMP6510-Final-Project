package MyBank;

public class Savings extends Accounts{
	private static String accountType = "Savings";
	
	Savings(double initialDeposit) {
		super();
		this.setBalance(initialDeposit);
		this.checkInterest();
	}
	
	@Override
	public String toString() {
		return "Account Type: " + accountType + " Account\n" +
				"Account Number: " + this.getAccountNumber() + "\n" +
				"Balance: " + this.getBalance() + "\n" +
				"Interest Rate: " + this.getInterest() + "%";
	}
}