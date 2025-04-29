package menuComponents;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: This program basically represents a list of transactions. There are various 
 *				you can use, such as, inserting, sorting, deleting transactions and more. 
 *				These transactions will also get put into files and holds several user information
 *				about the transactions they've made.
 * Methods - 
 * 	public TransactionList(int initialSize) - Initializes a new list based on the information provided
 *	private void increaseSize() - A method that increase the size of the current transactions array, when it reaches max size
 *	public void insertTransaction(Transaction transaction)  - Inserts a given transaction to the array
 *	public void insertTransaction(String transactionData) - Inserts a new transaction
 *	private int partition(int low, int high) - patitions the array lists and then swaps any elements
 *	private void quickSort(int low, int high) - Applies the QuickSort algorithm to sort the 'transactions' array based on final balances.
 *	public int search(double key) -  Searches for a final balance in the sorted 'transactions' array using binary search.
 *	public void delete(double key) - deletes a transaction based on the key
 *	public void replace(double key, Transaction newTransaction) - replaces transaction with a specified final balance
 *	public String toString() - Returns a string of the transaction in the 'transaction' array
 *	public String toFileString() - return a string representation for file storage
 *	public void saveToFile(String username) throws IOException - Saves the transactions to a file with the specified username.
 *	public boolean readFromFile(String username) throws IOException - Reads transactions from a file with the specified username.

 *
 */
public class TransactionList {
	//declare a list from the original class Transaction
	private Transaction[] transactions;
	private int maxSize;
	private int currentSize;

	/**
	 * Overloaded Constructor
	 * - Initializes a new list based on the information provided
	 */
	public TransactionList(int initialSize) {
		//Declare a list 
		this.maxSize = initialSize;
		this.transactions = new Transaction[maxSize];
		this.currentSize = 0;
	}

	/**
	 * Default constructor
	 */
	public TransactionList() {
		this.maxSize = 10;
		this.transactions = new Transaction[maxSize];
		this.currentSize = 0;
	}

	/**
	 * increaseSize method
	 * - A method that increase the size of the current transactions array, when it reaches max size
	 * 
	 */
	private void increaseSize() {
		//Double max size
		maxSize *= 2;
		//Create new array with updated size
		Transaction[] newTransactions = new Transaction[maxSize];
		//Take the existing transactions and input them int the new array
		System.arraycopy(transactions, 0, newTransactions, 0, transactions.length);
		//Update reference to the 'transactions' array
		transactions = newTransactions;
	}

	/**
	 * insertTransaction method
	 * - Inserts a given transaction to the array
	 */
	public void insertTransaction(Transaction transaction) {
		//Check if the current size is greater than or equal to the max size
		if (currentSize >= maxSize) {
			//Increase the size of the array
			increaseSize();
		}
		//Insert the transaction at the current size 
		transactions[currentSize++] = transaction;
	}
	/**
	 * insertTransaction method
	 * - Inserts a new transaction
	 */
	public void insertTransaction(String transactionData) {
		//Check if the current size is greater than or equal to the max size
		if (currentSize >= maxSize) {
			//Increase the size of the array
			increaseSize();
		}
		//Create new transaction
		Transaction newTransaction = new Transaction(' ', 0, 0); // Dummy initialization
		//Initialize new transaction from the provided string data
		newTransaction.readFromString(transactionData);
		//insert new transaction into the'transaction' array
		insertTransaction(newTransaction);
	}

	/**
	 * partition method
	 * - partitions the array lists and then swaps any elements
	 *
	 */
	private int partition(int low, int high) {
		// Select the last element as the pivot
		Transaction pivot = transactions[high];

		// Initialize the index of the smaller element
		int i = (low - 1);

		// Iterate through the array to partition elements
		for (int j = low; j < high; j++) {
			if (transactions[j].getFinalBalance() <= pivot.getFinalBalance()) {
				// Swap elements if they are smaller than or equal to the pivot
				i++;
				Transaction temp = transactions[i];
				transactions[i] = transactions[j];
				transactions[j] = temp;
			}
		}

		// Swap the pivot element with the element at (i + 1)
		Transaction temp = transactions[i + 1];
		transactions[i + 1] = transactions[high];
		transactions[high] = temp;

		// Return the index of the pivot after partitioning
		return i + 1;
	}

	/**
	 * quickSort method
	 * - Applies the QuickSort algorithm to sort the 'transactions' array based 
	 *   on final balances.
	 */
	private void quickSort(int low, int high) {
		if (low < high) {
			// Get the index for partition
			int pi = partition(low, high);

			// Sort the sub arrays
			quickSort(low, pi - 1);
			quickSort(pi + 1, high);
		}
	}

	/**
	 * search method
	 * - Searches for a final balance in the sorted 'transactions' array 
	 * 	 using binary search.
	 */
	public int search(double key) {
		// Sort the array first using QuickSort algorithm
		quickSort(0, currentSize - 1);

		// Initialize low and high indices for binary search
		int low = 0;
		int high = currentSize - 1;

		// Binary search loop
		while (low <= high) {
			int mid = low + (high - low) / 2;
			double midVal = transactions[mid].getFinalBalance();

			if (midVal < key) {
				low = mid + 1;
			} else if (midVal > key) {
				high = mid - 1;
			} else {
				return mid; // Key found
			}
		}

		// Key not found
		return -1;
	}

	/**
	 * delete method
	 * - deletes a transaction based on the key
	 */
	public void delete(double key) {
		//Find the index based on the key
		int index = search(key);
		//If transaction is found, remove it by shifting the elements
		if (index != -1) {

			for (int i = index; i < currentSize - 1; i++) {
				transactions[i] = transactions[i + 1];
			}
			//decrease the current size of the array
			currentSize--;
		}
	}

	/**
	 * replace method
	 * - replaces transaction with a specified final balance
	 */
	public void replace(double key, Transaction newTransaction) {
		//delete the existing transaction with the specified final balance
		delete(key);
		// Insert the new transaction
		insertTransaction(newTransaction);
	}

	/**
	 * toString method
	 * - Returns a string of the transaction in the 'transaction' array
	 */
	@Override
	public String toString() {
		// Build a displayer form a string that uses StringBuilder
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < currentSize; i++) {
			sb.append(transactions[i].toString()).append("\n");
		}
		//return the string
		return sb.toString();
	}

	/**
	 * toFileString method
	 * - return a string representation for file storage
	 */
	public String toFileString() {
		// Build a string representation for file storage using StringBuilder
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < currentSize; i++) {
			sb.append(transactions[i].toFileString()).append("\n");
		}
		//return the string
		return sb.toString();
	}

	/**
	 * saveToFile method
	 * - Saves the transactions to a file with the specified username.
	 *
	 */
	public void saveToFile(String username) throws IOException {
		// Open a BufferedWriter to write to the file
		BufferedWriter writer = new BufferedWriter(new FileWriter(username + "-list.txt", false));

		// Write the string representation of transactions to the file
		writer.write(toFileString());

		// Close the BufferedWriter
		writer.close();
	}

	/**
	 * readFromFile method
	 * - Reads transactions from a file with the specified username.
	 *
	 */
	public boolean readFromFile(String username) throws IOException {
		// Create a File object with the specified filename
		File file = new File(username + "-list.txt");

		// If the file does not exist, return false
		if (!file.exists()) {
			return false;
		}

		// Open a FileReader and BufferedReader to read from the file
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;

		// Read each line from the file and insert transactions into the array
		while ((line = bufferedReader.readLine()) != null) {
			insertTransaction(line);
		}

		// Close the FileReader and return true (reading successful)
		reader.close();
		return true;
	}


	// main method for self-testing
	public static void main(String[] args) {
		TransactionList list = new TransactionList();

		//infinite loop to test
		while(true){
			char command;
			command = JOptionPane.showInputDialog(null, "i - Insert Transaction\n "
					+ "p - print\n"
					+ "I - Increase Size\n"
					+ "P - Parition\n "
					+ "q - Quicksort\n "
					+ "s - Search\n "
					+ "d - Delete\n "
					+ "r - Replace\n"
					+ "Q - quit").charAt(0);

			if(command == 'Q') {
				break;
			}
			switch(command) {
			
			case 'p':{
				//Print the list
				JOptionPane.showMessageDialog(null, list);
				break;
			}
			case 'i':{
				
				//Initialize and declare the variables
                char tempType = JOptionPane.showInputDialog("Enter a account type(W or D):").charAt(0); 
                double initialBalance = Double.parseDouble(JOptionPane.showInputDialog("Enter a initial balance(double):")); 
                double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter a amount(double):")) ;
                
                //Create an object passing in the variables
                Transaction accInfo = new Transaction(tempType, initialBalance, amount);
                // Insert the transaction into the TransactionList
                list.insertTransaction(accInfo);
                JOptionPane.showMessageDialog(null, "Transaction list has been inserted");
                break;
			}
			case 'I':{
				//Call increase size method
				list.increaseSize();
				//Increase the size
				JOptionPane.showMessageDialog(null, "Size has been increased");
				break;
			}
			case 'P':{
				//Init low to 0
				int low = 0;
				//Call the partition method passing in the lwo and high values
				list.partition(0, list.currentSize - 1);
				//Display list has been sorted
				JOptionPane.showMessageDialog(null, "The list has been sorted");
				break;
			}
			case 'q':{
				//Init low to 0
				int low = 0;
				//Call the quickSort method passing in the lwo and high values
				list.quickSort(0, list.currentSize - 1);
				//Display list has been sorted
				JOptionPane.showMessageDialog(null, "The list has been sorted");
				break;
			}
			case 's':{
				//Ask for the final balance which represents the key
				double key = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter a final balance to find: "));
				//Initialize index to the key 
				int index = list.search(key);
				
				//If the index is greater than or equal to 0
				if(index >= 0) {
					//Display transaction has been found
					JOptionPane.showMessageDialog(null, "Transaction has been found");
				}
				else {
					//Display transaction has not been found
					JOptionPane.showMessageDialog(null, "Transaction hasn't been found");
				}
				break;
			}
			case 'd':{
				//Ask for the final balance which represents the key
				double key = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter a final balance to delete: "));
				//Call delete method passing in the key
				list.delete(key);
				//Display transaction has been deleted
				JOptionPane.showMessageDialog(null, "Transaction has been deleted");
				break;
			}
			case 'r':{
				//Ask for the final balance which represents the key
				double key = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter a final balance to delete: "));
			
				//Initialize and declare the variables
                char tempType = JOptionPane.showInputDialog("Enter a account type(W or D):").charAt(0); 
                double initialBalance = Double.parseDouble(JOptionPane.showInputDialog("Enter a initial balance(double):")); 
                double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter a amount(double):")) ;
           
                //Create an object passing in the variables
                Transaction newTransaction = new Transaction(tempType, initialBalance, amount);
                
                //Call the replace method
                list.replace(key, newTransaction);
                
                //Display a message saying the transaction has been replaced
                JOptionPane.showMessageDialog(null, "Transaction has been replaced");
                
			}
			}
		}
	}
}
