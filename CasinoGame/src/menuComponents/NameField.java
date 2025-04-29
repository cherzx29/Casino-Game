package menuComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: During the sign up UI, the user will enter a first and last name. Hence, in this class, the program, 
 * 				is to detect if the user is actually following the criteria shown. If the user enters two words in the label
 * 				the background label will turn green indicating they are following the criteria. If the user only type in one 
 * 				word, the labels background will turn red. This sort of idea has also been applied to other field classes. 
 *
 * Method List:
 * 	public NameField() - Default constructor
 * 	private void updateFieldColor() - A method to update the field color based on validity of what the user types
 * 	public boolean isValidName(String name) - Take in a name and parts it so see if they followed the criteria
 * 	
 */
public class NameField extends JTextField {

	/**
	 * Default Constructor
	 * - Displays the criteria for user 
	 */
    public NameField() {
    	//A message to provide assistance to the user
        this.setToolTipText("Name must consist of two words (first name and last name) with no numbers or symbols.");
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
    	// Check if the text in the field represents a valid balance
        if (isValidName(this.getText())) {
        	 // If valid, set the background color to GREEN
            this.setBackground(Color.GREEN);
        } else {
        	// If not valid, set the background color to RED
            this.setBackground(Color.RED);
        }
    }

    /**
     * 
     * isValidName
     * - Take in a name and parts it so see if they followed the criteria
     */
    public boolean isValidName(String name) {
        // Split the name using parts
        String[] parts = name.trim().split("\\s+");
        // Check if the part aren't two of them
        if (parts.length != 2) {
        	//If they aren't return false
            return false;
        }
        // Check if each part consists only of alphabetical characters
        return parts[0].matches("[A-Za-z]+") && parts[1].matches("[A-Za-z]+");
    }

    // Test the component
    public static void main(String[] args) {
    	//Create a JFrame
        JFrame frame = new JFrame("Name Field Test");
        // Set default close operation for the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        //Create a new field
        NameField nameField = new NameField();
        //Set the number of columns for the balanceField
        nameField.setColumns(20);

        //Add balanceField to the frame
        frame.add(nameField);
        //Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }
}
