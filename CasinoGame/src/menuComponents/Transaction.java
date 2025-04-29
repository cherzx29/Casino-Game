package menuComponents;
import java.text.NumberFormat;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: This class represents a account records with properties such as account type, initial balance, amount, and final balance. You can change information and use 
 * 				various methods to print the records.
 * 
 * Methods:
 * 			public Transaction(char type, double initialBalance, double amount) - overload , gets all the properties of the account
 * 			public char getType() - get account type 
 * 			public void setType(char type) - set account type
 * 			public double getInitialBalance() - get initial balance
 * 			public void setInitialBalance(double initialBalance) - set initial balance
 * 			public double getFinalBalance() - get final balance
 * 			public double getAmount() - get amount
 * 			public void setAmount(double amount) - set amount
 * 			public void readFromString(String data) - Assumes: accountType, initialBalance, amount, and final balance
 * 			public String toString()  - toString method for visually appealing display
 * 			public String convertType(char type) - Converts the type into a string
 * 			public String toFileString()  - toString text for the file
 *
 */
public class Transaction {
	// Declare private Variables
    private char type; // 'W' for withdraw, 'D' for deposit
    private double initialBalance;
    private double finalBalance;
    private double amount;

    /**
     * Overloaded Constructor
     * - A constructor where you plug in the type of account, initialBalance, and the amount
     */
    public Transaction(char type, double initialBalance, double amount) {
        this.type = type;
        this.initialBalance = initialBalance;
        this.amount = amount;
        this.finalBalance = type == 'W' ? initialBalance - amount : initialBalance + amount;
    }

    // number formatting
    public NumberFormat formatter = NumberFormat.getCurrencyInstance();
    
    // Getters and Setters Methods
    
    /**
     * @return account type
     */
    public char getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * @return the intial balance
     */
    public double getInitialBalance() {
        return initialBalance;
    }

    /**
     * @param initialBalance the initialBalance to set
     */
    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    /**
     * @return the finalBalance
     */
    public double getFinalBalance() {
        return finalBalance;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    /**
     * Method readFromString
     * - Assumes: accountType, initialBalance, amount, and final balance
     */
    public void readFromString(String data) {
    	//Split the elememts by comma
        String[] parts = data.split(",");
        //Set each parts to its respective information
        this.type = parts[0].charAt(0);
        this.initialBalance = Double.parseDouble(parts[1]);
        this.amount = Double.parseDouble(parts[2]);
        this.finalBalance = Double.parseDouble(parts[3]);
    }

    /**
     * toString method
     * -toString method for visually appealing display
     */
    
    @Override
    public String toString() {
        return	"Type = " + convertType(type) +
                "; Initial Balance = " + formatter.format(initialBalance) +
                "; Amount = " + formatter.format(amount) +
                "; Final Balance = " + formatter.format(finalBalance);
    }
    
    /**
     * convertType method
     * - Converts the type into a string
     */
    public String convertType(char type) {
    	switch (type) {
    		//Case if the account type is D
    		case('D'): {
    			return "Deposit";
    		}
    		//Default if the account type anything besides D
    		default: {
    			return "Withdraw";
    		}
    	}
    }

    /**
     * toFileString method
     * - toString text for the file
     */
    public String toFileString() {
        return type + "," + initialBalance + "," + amount + "," + finalBalance;
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	// Test case 1: Create a transaction and display it
        System.out.println("Test Case 1: Creating and Displaying a Transaction");
        Transaction transaction1 = new Transaction('D', 1000.0, 500.0);
        System.out.println(transaction1);
        //Print the toString for the file
        System.out.println(transaction1.toFileString());

        // Test case 2: Read transaction data from a string
        System.out.println("\nTest Case 2: Reading Transaction Data from a String");
        Transaction transaction2 = new Transaction('D', 0.0, 0.0); // Initialize with dummy values
        transaction2.readFromString("W,1500.0,200.0,1300.0");
        System.out.println(transaction2);
        //Print the toString for the file
        System.out.println(transaction2.toFileString());
	}
}
