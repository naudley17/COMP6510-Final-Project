package MyBank;
import java.util.ArrayList;

public class Bank {
	ArrayList<Customers> customers = new ArrayList<Customers>();
	
	// Create customer account
	void addCustomer(Customers customer) {
		customers.add(customer);
	}
	
	Customers getCustomer(int account) {
		return customers.get(account);
	}
	
	// List customer accounts
	ArrayList<Customers> getCustomers() {
		return customers;
	}
}