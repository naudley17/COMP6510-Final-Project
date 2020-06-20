package MyBank;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	// Instance Variables
	Scanner sc = new Scanner(System.in);
	boolean exit = false;
	Bank bank = new Bank();
	
	public static void main(String[] args) {
		Main screen = new Main();
		screen.launchApp();
	}
	
	public void launchApp() {
		printGreeting();
		while(!exit) {
			printMenu();
			int choice = getInput();
			performSelection(choice);
		}
	}
	
	// Method to display a welcome message
	private void printGreeting() {
		System.out.println("Welcome. Thank you for choosing MyBank.");
	}
	
	// Method to display a menu of selectable actions
	private void printMenu() {
		System.out.println("\nPlease select an action:");
		System.out.println("[1] Create a New Account");
		System.out.println("[2] Make a Deposit");
		System.out.println("[3] Make a Withdrawal");
		System.out.println("[4] Check Account");
		System.out.println("[5] Exit Application");
	}
	
	// Method to receive user inputs
	private int getInput() {
		int choice = 0;
		do {
			System.out.print("\nEnter your choice: ");
			try {
				// Takes user input -> Converts it to Int -> Stores it in choice
				choice = Integer.parseInt(sc.nextLine());
			}
			catch(NumberFormatException e) {
				// Display error message if user input is not a number
				System.out.println("Invalid key press. Please press a number key.");
			}
			if(choice < 1 || choice > 5) {
				// Check if user input is within range
				System.out.println("Invalid key press. Valid number keys: 1 - 5.");
			}
		} 
		while(choice < 1 || choice > 5);
		return choice;
	}
	
	// Method to perform action selected by user
	private void performSelection(int choice) {
		switch(choice) {
			case 1:
				// Creates an Account
				createAccount();
				break;
			case 2:
				// Makes a Deposit
				makeDeposit();
				break;
			case 3:
				// Makes a Withdrawal
				makeWithdrawal();
				break;
			case 4:
				// Checks Balance
				checkAccount();
				break;
			case 5:
				// Exits the App
				System.out.println("\nThank you for using the MyBank app.");
				System.exit(0);
				break;
			default:
				System.out.println("Error. Please try again.");
		}
	}
	
	// Method for Account Creation
	private void createAccount() {
		String firstName, lastName, accountType = "";
		double initialDeposit = 0;
		boolean valid = false;
		while(!valid) {
			// Handling user input for account type. Not case-sensitive
			System.out.print("Please enter an account type [Checking / Savings]: ");
			accountType = sc.nextLine();
			if(accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")) {
				valid = true;
			}
			else {
				System.out.println("\nInvalid account type. Please enter [Checking / Savings].");
			}
		}
		// Handling user input for account name
		System.out.print("Please enter your first name: ");
		firstName = sc.nextLine();
		System.out.print("Please enter your last name: ");
		lastName = sc.nextLine();
		
		valid = false;
		while(!valid) {
			// Handling user input for first deposit
			System.out.print("Please enter your first deposit: ");
			try {
				// Takes user input -> Converts it to Double -> Stores it in initialDeposit
				initialDeposit = Double.parseDouble(sc.nextLine());
			}
			catch(NumberFormatException e) {
				// Display message if user input is not a number
				System.out.println("\nInvalid input. Deposit must be a number.");
			}
			if(accountType.equalsIgnoreCase("checking")) {
				if(initialDeposit < 10) {
					// Minimum amount of money to open a Checking account
					System.out.println("\nCheckings account require a minimum of $10 to open.");
				}
				else {
					valid = true;
					// Display a message for successful account creation
					System.out.println("Thank you for making an account. $" + initialDeposit + " has been deposited into your account.");
				}
			}
			else if(accountType.equalsIgnoreCase("savings")) {
				if(initialDeposit < 10) {
					// Minimum amount of money to open a Savings account
					System.out.println("Savings account require a mininum of $50 to open.");
				}
				else {
					valid = true;
					// Display a message for successful account creation
					System.out.println("Thank you for making an account. $" + initialDeposit + " has been deposited into your account.");
				}
			}
		}
		
		// Creating an account
		Accounts account;
		// If input is "checking", created checking account, else create a savings account
		// (making sure the input is valid is already done in the first while loop)
		if(accountType.equalsIgnoreCase("checking")) {
			account = new Checking(initialDeposit);
		}
		else {
			account = new Savings(initialDeposit);
		}
		
		Customers customer = new Customers(firstName, lastName, account);
		bank.addCustomer(customer);
	}
	
	// Method for Account Selection
	private int selectAccount() {
		ArrayList<Customers> customers = bank.getCustomers();
		if(customers.size() <= 0) {
			// Display a message if the bank has 0 customer accounts
			System.out.println("No customers to display. \n");
			return -1;
		}
		// Handling user input for account selection
		System.out.println("Select an account:");
		for(int i = 0; i < customers.size(); i++) {
			// User account list starts with the number [1]
			System.out.println("[" + (i + 1) + "] " + customers.get(i).customersInfoList());
		}
		int account = 0;
		System.out.print("Please enter your selection: ");
		try {
			// However, account indexes start at 0, so -1 to the number inputed by the user
			account = Integer.parseInt(sc.nextLine()) - 1;
		}
		catch(NumberFormatException e) {
			account = -1;
		}
		if(account < 0 || account >= customers.size()) {
			account = -1;
			// Display message if user input is not valid
			System.out.println("Invalid account. Please retry.");
		}
		return account;
	}
	
	// Method for Making a Deposit
	private void makeDeposit() {
		int account = selectAccount();
		if(account >= 0 ) {
			System.out.print("Enter a deposit amount: ");
			double amount = 0;
			try {
				amount = Double.parseDouble(sc.nextLine());
			}
			// If user enters invalid amount, sets it to 0
			catch(NumberFormatException e) {
				amount = 0;
			}
			// Customer -> Account -> Deposit
			bank.getCustomer(account).getAccount().deposit(amount);
		}
	}
	
	// Method for Making a Withdrawal
	private void makeWithdrawal() {
		int account = selectAccount();
		if(account >= 0 ) {
			System.out.print("Enter withdrawal amount: ");
			double amount = 0;
			try {
				amount = Double.parseDouble(sc.nextLine());
			}
			// If user enters invalid amount, sets amount to 0
			catch(NumberFormatException e) {
				amount = 0;
			}
			// Customer -> Account -> Withdraw
			bank.getCustomer(account).getAccount().withdraw(amount);
		}
	}
	
	// Method for Checking Balance
	private void checkAccount() {
		int account = selectAccount();
		if(account >= 0 ) {
			// Print Account Details
			System.out.println(bank.getCustomer(account).getAccount());
		}
	}
}