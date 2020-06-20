package MyBank;

public class Customers {
	private String firstName;
	private String lastName;
	private Accounts account;
	
	Customers(String firstName, String lastName, Accounts account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
	}
	
	Accounts getAccount() {
		return account;
	}
	
	@Override
	public String toString() {
		return "Customer\n" +
				"Name: " + firstName + " " + lastName + "\n" +
				account;
	}
	
	public String customersInfoList() {
		return "Name: " + firstName + " " + lastName + " | Account Number: " + account.getAccountNumber();
	}
}