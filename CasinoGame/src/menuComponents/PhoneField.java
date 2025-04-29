package menuComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: During the sign up UI, the user will enter a phone number. Hence, in this class, the program, 
 * 				is to detect if the user is actually following the criteria shown. If the user enters it in this format
 * 				; +CountryCode-AreaCode-Number, the background label will turn green indicating they are following the criteria. 
 * 				If the user doesn't qualify for the criteria, the labels background will turn red. This sort of idea has also been applied to other field classes.
 *
 * Method List:
 * 	public PhoneField() - Displays the criteria for user 
 * 	private void updateFieldColor() - A method to update the field color based on validity of what the user types
 * 	public boolean isValidPhoneNumber(String text) - Validates the phone number based on specified criteria.
 */
public class PhoneField extends JTextField {

	/**
	 * Default Constructor
	 * - Displays the criteria for user 
	 */
	
    public PhoneField() {
    	//A message to provide assistance to the user
        this.setToolTipText("Enter phone number in the format: +CountryCode-AreaCode-Number");
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
    	//Gets the text
        String text = this.getText();
        //Check if the text is not empty
        if (!text.isEmpty()) {
        	// Check if the text in the field represents a valid phone number
            if (isValidPhoneNumber(text)) {
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
     * isValidPhoneNumber method
     * - Validates the phone number based on specified criteria.
     *
     */
    public boolean isValidPhoneNumber(String text) {
    	//If the text doesn't match the format
        if (!text.matches("\\+\\d+-\\d+-\\d+")) {
        	//return false
            return false;
        }

        //Else, return true
        return true;
    }

    // Test the component
    public static void main(String[] args) {
    	//Create a JFrame
        JFrame frame = new JFrame("Phone Field Test");
        // Set default close operation for the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        //Create a new field
        PhoneField phoneField = new PhoneField();
        //Set the number of columns for the balanceField
        phoneField.setColumns(20);

        //Add passwordField to the frame
        frame.add(phoneField);
        //Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }
}
