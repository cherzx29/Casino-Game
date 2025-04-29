package menuComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: In this program, the user is supposed to type in a balance amount meeting the criteria provided. If they aren't
 * 				meeting the criteria; for instance the user has to type a number greater than 0 and if they do, 
 * 				the background turns green, and if they don't, the background turns red. 
 *
 *Method:
 *		public BalanceField() - Default constructor
 *		private void updateFieldColor() - A method to update the field color based on validity of what the user type
 *		public boolean isValidBalance(String balanceStr) - A method to update the field color based on validity of what the user type
 *		public Double getBalance() - Get the balance 
 *
 */
public class BalanceField extends JTextField {

	/**
	 * Default Constructor
	 * - Displays the criteria for the user
	 */
    public BalanceField() {
    	//A message to provide assistance to the user
        this.setToolTipText("Value must be a valid number (double) and be equal to or greater than 0.");
        //Adds a key listener to UsernameField
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            	//Call this method, to update the color of the background based on what the user types
                updateFieldColor();
            }
        });
    }
    /**
     * Method updateFieldColor
     * - A method to update the field color based on validity of what the user type
     */
    
    private void updateFieldColor() {
    	// Check if the text in the field represents a valid balance
    	if (isValidBalance(this.getText())) {
    	    // If valid, set the background color to GREEN
    	    this.setBackground(Color.GREEN);
    	} else {
    	    // If not valid, set the background color to RED
    	    this.setBackground(Color.RED);
    	}
    }
    /**
     * Method isValidBalance
     * - Takes in the balance and checks if it's valid meeting the requirements
     */
    public boolean isValidBalance(String balanceStr) {
    	try {
    	    // Attempt to add the provided balance string as a double
    	    double balance = Double.parseDouble(balanceStr);

    	    // Check if the  balance is non-negative
    	    return balance >= 0;

    	} catch (NumberFormatException e) {
    	    // If a NumberFormatException occurs during parsing, consider the balance string invalid
    	    return false;
    	}
    }
    
    /**
     * getBalance method
     * - Get the balance 
     */
    public Double getBalance() {
    	return Double.parseDouble(this.getText());
    }
    
    /*
     * Indicate to users that getText should not be used 
     */
    @Deprecated
    @Override
    public String getText() {
        return super.getText();
    }

    // Test the component
    public static void main(String[] args) {
    	//Create a JFrame
        JFrame frame = new JFrame("Balance Field Test");
        // Set default close operation for the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        //Create a new field
        BalanceField balanceField = new BalanceField();
        //Set the number of columns for the balanceField
        balanceField.setColumns(20);

        //Add balanceField to the frame
        frame.add(balanceField);
        //Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }
}
