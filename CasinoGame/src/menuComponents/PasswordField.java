package menuComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: During the sign up UI, the user will enter a password. Hence, in this class, the program, 
 * 				is to detect if the user is actually following the criteria shown. If the user enters at least one lower case letter, 
 *       		one upper case letter, one number, one special character, and be longer than 6 characters, the background label will 
 *       		turn green indicating they are following the criteria. If the user doesn't qualify for the criteria
 * 				, the labels background will turn red. This sort of idea has also been applied to other field classes.
 * 
 * Method List:
 * 	public PasswordField() - Displays the criteria for user 
 * 	private void updateFieldColor() - A method to update the field color based on validity of what the user types
 * 	public boolean isValidPassword() - Validates the password based on specified criteria.
 *
 */
public class PasswordField extends JPasswordField {

	/**
	 * Default Constructor
	 * - Displays the criteria for user 
	 */
	
    public PasswordField() {
    	//A message to provide assistance to the user
        this.setToolTipText("Password must contain at least one lowercase letter, "
        		+ "one uppercase letter, one number, one special character, and be longer than 6 characters.");
        //Adds a key listener to NameField
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            	//Call this method, to update the color of the background based on what the user types
                updateFieldColor();
            }
        });
    }
    /**
     * updateFieldColor method
     * - A method to update the field color based on validity of what the user types
     */
    private void updateFieldColor() {
    	//Check if the length of password is greater than 0
    	if (this.getPassword().length > 0) {
    		// Check if the text in the field represents a valid password
            if (isValidPassword()) {
            	// If valid, set the background color to GREEN
                this.setBackground(Color.GREEN);
            } else {
            	// If not valid, set the background color to RED
                this.setBackground(Color.RED);
            }
        } else {
        	//If not, then set the background color to white
            this.setBackground(Color.WHITE);
        }
    }
    /**
     * isValidPassword method
     * - Validates the password based on specified criteria.
     *
     */
    public boolean isValidPassword() {
        // Convert password to a string
        String password = new String(this.getPassword());

        //If password length is less than or equal to 6
        if (password.length() <= 6) {
        	//return false
            return false;
        }

        // Initialize boolean types
        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;

        //Go through each character in password
        for (char c : password.toCharArray()) {
        	//If character is lower case, return true
            if (Character.isLowerCase(c)) hasLower = true;
            //if character is upper case, return true
            else if (Character.isUpperCase(c)) hasUpper = true;
            //if character is a digit, return true
            else if (Character.isDigit(c)) hasDigit = true;
            //if character is not a letter or digit, return true
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        // Return true if the password meets all criteria, false otherwise
        return hasLower && hasUpper && hasDigit && hasSpecial;
    }

    // Test the component
    public static void main(String[] args) {
    	//Create a JFrame
        JFrame frame = new JFrame("Password Field Test");
        // Set default close operation for the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        //Create a new field
        PasswordField passwordField = new PasswordField();
        //Set the number of columns for the balanceField
        passwordField.setColumns(20);

        //Add passwordField to the frame
        frame.add(passwordField);
        //Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }
}
