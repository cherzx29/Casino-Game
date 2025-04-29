package menuComponents;

import java.io.*;
import java.util.*;

/**
 * Name: Sanat Kanwal
 * Date:2024-01-19
 * Description: This class represents a user account and contains all their person information. Such as username, password, name, 
 * 				phone number, and balance. This class also provides functionality for creating accounts, performing login operation, 
 * 				and storing account information files. The base account is created through user as this class inherits from it.
 * 
 * Methods: 
 * 	public Account(String username, String password, String name, PhoneNumber phoneNumber, double balance) - Constructor for creating an account with initial data
 * 	public Account(String username, String password)- Creating an account with lesser data than the other one
 * 	public boolean isBalanceValid() - Checks if balance is greater than or equal to 0
 * 	public void saveToFile() - Save account data to a file
 * 	public boolean login() - Attempt to log in with the provided username and password
 * 	private void saveUsernameToFile() - Save the username to a file
 * 	public static boolean doesUsernameExist(String username) - checks if the username exists in the file
 * 	public boolean deposit(double deposit) - depsoit money into account
 * 	public boolean withdraw(double withdrawal) - Withdraw money from the account
 * 	public String toString() - displays a format of how the account information would look like
 * 	public double getBalance() - get the balance
 * 	public void setBalance(double balance) - set the balance to balance
 * 	public String getPassword() - get the password
 * 	public void setPassword(String password)  - set the password
 * 	public String getName()  - get the name
 * 	public void setName(String name) - set the name
 */
public class Account extends User {
	//Declare private variables
	private double balance; 
	private String username, password; 

	/**
	 * Overloaded constructor
	 * - Constructor for creating an account with initial data
	 */
	
	public Account(String username, String password, String name, PhoneNumber phoneNumber, double balance) {
		// Call parent constructor
		super(name, phoneNumber);
		//Set all values to their respective variables
		this.username = username;
		this.password = password;
		this.balance = balance;
		// Save the username given to the file
		saveUsernameToFile(); 
	}

	/**
	 * Overloaded constructor
	 *- Creating an account with lesser data than the other one
	 */
	public Account(String username, String password) {
		// Call super
		super();
		//Set all values to their respective variables
		this.username = username;
		this.password = password;
	}

	/**
	 * isBalanceValid method
	 * - Checks if balance is greater than or equal to 0
	 */
	public boolean isBalanceValid() {
		return this.balance >= 0;
	}

	/**
	 * saveToFile
	 * - Save account data to a file
	 */
	public void saveToFile() {
		
		try {
		    // Create a PrintWriter to write to a file named based on the username
		    PrintWriter writer = new PrintWriter(new FileOutputStream(this.username + "-data.txt", false));

		    // Write all the details about the account in the file
		    
		    //Write username
		    writer.println(this.username);
		    // Write password
		    writer.println(this.password);
		    // Write name
		    writer.println(getName()); 
		    // Write phone number as a string
		    writer.println(getPhoneNumber().toString()); 
		    // Write phone number as a string
		    writer.println(this.balance); // Write account balance
		    // Close the PrintWriter
		    writer.close();
		} catch (FileNotFoundException e) {
		    // If file not found, print the stack trace in case if errors occurs
		    e.printStackTrace();
		}
	}

	/**
	 * login method
	 * - Attempt to log in with the provided username and password
	 */
	
	public boolean login() {
		
		// Create a File object based on the account data file name
		File accountFile = new File(username + "-data.txt");

		// Check if the account data file exists
		if (accountFile.exists()) {
		    try {
		        // Create a Scanner which allows it to read from the account file
		        Scanner scanner = new Scanner(accountFile);

		        // Read the username from the file
		        if (scanner.hasNextLine()) this.username = scanner.nextLine();

		        // Check if the password matches the one stored in the file
		        if (scanner.hasNextLine() && password.equals(scanner.nextLine())) {

		        	String tempName = getName();
		        	PhoneNumber tempNum = getPhoneNumber();
		            // Only if the password matches, it can read the following stuff
		        	// Read the name
		            if (scanner.hasNextLine()) tempName = scanner.nextLine();
		            setName(tempName);
		            // Read and convert phone number
		            if (scanner.hasNextLine()) tempNum = getPhoneNumber().fromString(scanner.nextLine());
		            setPhoneNumber(tempNum);
		            // Read and convert balance
		            if (scanner.hasNextLine()) this.balance = Double.parseDouble(scanner.nextLine()); 
		            // Close the Scanner after reading
		            scanner.close();
		            
		            // Successful login
		            return true; 
		        }

		        // Close the Scanner after reading
		        scanner.close();
		    } catch (FileNotFoundException e) {
		        // Print the stack trace if the file is not found and if any errors occur
		        e.printStackTrace();
		    }
		}

		// Return false if login fails
		return false;
	}

	/**
	 * saveUserNameToFile method
	 * - Save the username to a file
	 */
	private void saveUsernameToFile() {
		try {
		    // Create a PrintWriter to write
		    PrintWriter writer = new PrintWriter(new FileOutputStream(new File("username-list.txt"), true));

		    // Write the current username to the file
		    writer.println(this.username);

		    // Close the PrintWriter to finalize writing
		    writer.close();
		} catch (FileNotFoundException e) {
		    // If file not found, print the stack trace in case of any errors occur
		    e.printStackTrace();
		}
	}

	/**
	 * doesUsernameExist
	 * - checks if the username exists in the file
	 */
	public static boolean doesUsernameExist(String username) {
		try {
		    // Create a Scanner to read from the file
		    Scanner scanner = new Scanner(new File("username-list.txt"));

		    // Iterate through each line in the file
		    while (scanner.hasNextLine()) {
		        // Check if the current lines in the files matches the username
		        if (scanner.nextLine().equals(username)) {
		            // If a match is found, close the Scanner and return true
		            scanner.close();
		            return true;
		        }
		    }

		    // Close the Scanner after checking all lines in the file
		    scanner.close();
		} catch (FileNotFoundException e) {
		    // Print the stack trace if the file is not found and if any errors occurs
		    e.printStackTrace();
		}

		// Return false if the username is not found
		return false;
	}

	// Deposit money into the account
	public boolean deposit(double deposit) {
		this.balance = this.balance + deposit;
		return true;
	}

	// Withdraw money from the account
	public boolean withdraw(double withdrawal) {
		// Check if user has enough money
		if (this.getBalance() >= withdrawal) {
			if (this.deposit(-withdrawal)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * toString method
	 * - displays a format of how the account information would look like
	 */
	@Override
	public String toString() {
		return "Account{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", name='" + getName() + '\'' +
				", phoneNumber=" + getPhoneNumber() +
				", balance=" + balance +
				'}';
	}

	/*
	 * Getters and setters methods
	 */
	
	/**
	 * 
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param set the balance to balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * 
	 * @return the username
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param set the username to username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param set password to password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	// Main method for self-testing
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create an Account object with initial data
		Account account1 = new Account("user1", "pass123", "John Doe", new PhoneNumber("+1-212-5551234"), 100.0);
		// Save account data to a file
		account1.saveToFile(); 

		// Create an Account object with minimal data and perform login
		Account account2 = new Account("user1", "pass123");
		account2.login();
		// Print the account information
		System.out.println(account2); 

		// Check if a username exists and if the balance is valid
		System.out.println("Does 'user1' exist? " + doesUsernameExist("user1"));
		System.out.println("Is the balance valid? " + account1.isBalanceValid());
	}
}