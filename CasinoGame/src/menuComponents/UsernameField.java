package menuComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: In this program, the user is supposed to type in a username meeting the criteria provided. If they aren't
 * 				meeting the criteria, the background turns red, and if they do, the background turns green. 
 * 
 * Methods:
 * 		private void updateFieldColor() - To update the field color based on validity of what the user type
 * 		public boolean isValidUsername(String username) - Takes in the username and checks if it's valid meeting the requirements
 *		private boolean containsSpecialCharacter(String str) - Takes in a string to check if it contains special characters
 */
public class UsernameField extends JTextField {

	/**
	 * Default Constructor
	 * - Displays the criteria for the user
	 */
    public UsernameField() {
    	//A message to provide assistance to the user
        this.setToolTipText("Username must be more than 3 characters, contain no special characters, and not be in use.");
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
    	//Get the text and declare it to the string
        String username = this.getText();
        //Check if the username is valid by calling in the isValidUsername method
        if (isValidUsername(username)) {
        	//If it is valid. turn background to green
            this.setBackground(Color.GREEN);
        } else {
        	//If it isn't valid. turn background to red
            this.setBackground(Color.RED);
        }
    }

    /**
     * Method isValidUsername
     * - Takes in the username and checks if it's valid meeting the requirements
     */
    public boolean isValidUsername(String username) {
    	//Checks if the username is meeting the certain criteria
        return username.length() > 3 && !containsSpecialCharacter(username) && !Account.doesUsernameExist(username);
    }

    /**
     * Method containsSpecialCharacter
     * - Takes in a string to check if it contains special characters
     */
    private boolean containsSpecialCharacter(String str) {
    	//returns true if it has special characters
        return !str.matches("[A-Za-z0-9]+");
    }

    // Test the component
    public static void main(String[] args) {
    	//Create a JFrame 
        JFrame frame = new JFrame("Username Field Test");
        // Set default close operation for the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        //Create a new object
        UsernameField usernameField = new UsernameField();
        //Set the number of columns for the usernameField
        usernameField.setColumns(20);

        //Add usernameField to the frame
        frame.add(usernameField);
        //Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }
}
