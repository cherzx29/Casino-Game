package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import menuComponents.Account;
import menuComponents.BalanceField;
import menuComponents.NameField;
import menuComponents.PasswordField;
import menuComponents.PhoneField;
import menuComponents.PhoneNumber;
import menuComponents.SoundEffect;
import menuComponents.UsernameField;
import startHere.InitialUI;

/**
 * Name: Cherith Boya
 * Date:2024-01-19
 * Description: In this UI, the user is going to register into the casino only if they hit the register button in the initial UI. 
 * 				Once you are brought upon in this page, you can start adding the information it asked for; first and last name, phone number
 * 				balance, user name and password. As you are filling in each detail, there will be a certain criteria you'd have to meet
 * 				, and just by hovering over the text labels(as you type), you can see the criteria. Fill in the criteria properly, a green
 * 				background appears in the label, filling it wrong, a red background appears. Once done filling in the information, click the
 * 				sign up button to proceed into the casino or hit the back button to go back to the initial page. 
 *
 */
public class SignUpUI extends JFrame implements ActionListener{
	/*
	 * Declare Global Variables and define the UI components
	 */
	private Font headerFont, labelFont, buttonFont;
	private JLabel headerLabel, usernameLabel, phoneLabel, nameLabel, passwordLabel, repeatLabel, backgroundLabel, balanceLabel;
	private PhoneField phoneField;
	private UsernameField userField;
	private PasswordField passwordField, repeatField;
	private NameField nameField;
	private BalanceField balanceField;
	private JButton backButton, signUpButton;

	/**
	 * Default Constructor
	 */
	public SignUpUI() {
		//Create the frame
		super("Welcome!");
		// Set default close operation for the JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Sets the location of the frame
		this.setLocationRelativeTo(null);
		// No automatic layout and only manual
		this.setLayout(null); // Set layout to null
		// Set an icon image to the JFrame
		this.setIconImage(new ImageIcon("LuckyQueenNoText.png").getImage()); // Set image icon
		// Set the size of the frame
		this.setSize(488, 374);
		//Set the frame where it can't be resized
		this.setResizable(false);

		// Initialize Fonts with the same font, different thicknesses, and different sizes
		headerFont = new Font("Rockwell", Font.BOLD, 33);
		labelFont = new Font("Rockwell", Font.PLAIN, 18);
		buttonFont = new Font("Rockwell", Font.PLAIN, 12);

		// Create a label that displays a "welcome" text
		headerLabel = new JLabel("Welcome!");
		//Align the label to the center
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//Make the text label white
		headerLabel.setForeground(Color.WHITE);
		//Set the label to its certain font
		headerLabel.setFont(headerFont);
		//Set the position and size of the button
		headerLabel.setBounds(114, 20, 276, 40);
		//Add the label to the JFrame
		this.add(headerLabel);

		// Create a label that displays a "Name: " text
		nameLabel = new JLabel("Name:");
		//Align the label to the center
		nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		//Set the label to its certain font
		nameLabel.setFont(labelFont);
		//Make the text label white
		nameLabel.setForeground(Color.WHITE);
		//Set the position and size of the button
		nameLabel.setBounds(101, 82, 110, 14);
		//Add the label to the JFrame
		this.add(nameLabel);

		//Create a button where theres's an image displaying on it and is scaled in a default way
		signUpButton = new JButton("Create Account");
		//Set it to a specific type of font
		signUpButton.setFont(buttonFont);
		//Set the position and size of the button
		signUpButton.setBounds(258, 252, 119 + 6, 23);
		//A way to handle and listen to button clicks
		signUpButton.addActionListener(this);
		//Add the button to the JFrame
		this.add(signUpButton);

		// Create a new JNameField "nameField", for entering the password
		nameField = new NameField();
		// Position where the text should be and adjust the size
		nameField.setBounds(223, 79, 161, 16 + 6);
		// Set the number of columns for the JNameField
		nameField.setColumns(10);
		// Add the nameField to the JFrame
		this.add(nameField);

		//Create a JButton labeled "backButton" which has text 
		backButton = new JButton("Back");
		// Set the text of a font style called (buttonFont)
		backButton.setFont(buttonFont);
		//A way to handle and listen to button clicks
		backButton.addActionListener(this);
		//Position where the button should be located and adjust the size
		backButton.setBounds(129, 252, 119, 23);
		this.add(backButton);

		// Create a label for "Phone Number:"
		phoneLabel = new JLabel("Phone Number:");
		// Align the label to the right
		phoneLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Set the font, color, and position of the label
		phoneLabel.setFont(labelFont);
		// Position where the text should be and adjust the size
		phoneLabel.setBounds(65, 107, 146, 14);
		// Set the color of the text to white
		phoneLabel.setForeground(Color.WHITE);
		// Add the label to the frame
		this.add(phoneLabel);

		// Create a text field for the phone number
		phoneField = new PhoneField();
		// Set the number of columns for the JTextField
		phoneField.setColumns(10);
		// Position where the field should be and adjust the size
		phoneField.setBounds(223, 106, 161, 16 + 6);
		// Add the text field to the frame
		this.add(phoneField);

		// Create a label for "Initial Balance:"
		balanceLabel = new JLabel("Initial Balance:");
		// Align the label to the right
		balanceLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Set the font, color, and position of the label
		balanceLabel.setFont(labelFont);
		// Set the color of the text to white
		balanceLabel.setForeground(Color.WHITE);
		// Position where the text should be and adjust the size
		balanceLabel.setBounds(65, 132, 146, 14);
		// Add the label to the frame
		this.add(balanceLabel);

		// Create a text field for the initial balance
		balanceField = new BalanceField();
		// Set the number of columns for the JTextField
		balanceField.setColumns(10);
		// Position where the field should be and adjust the size
		balanceField.setBounds(223, 131, 161, 16 + 6);
		// Add the text field to the frame
		this.add(balanceField);

		// Create a label for "Username:"
		usernameLabel = new JLabel("Username:");
		// Align the label to the right
		usernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Set the font, color, and position of the label
		usernameLabel.setFont(labelFont);
		// Set the color of the text to white
		usernameLabel.setForeground(Color.WHITE);
		// Position where the text should be and adjust the size
		usernameLabel.setBounds(101, 157, 110, 14);
		// Add the label to the frame
		this.add(usernameLabel);

		// Create a label for "Password:"
		passwordLabel = new JLabel("Password:");
		// Align the label to the right
		passwordLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Set the font, color, and position of the label
		passwordLabel.setFont(labelFont);
		// Set the color of the text to white
		passwordLabel.setForeground(Color.WHITE);
		// Position where the text should be and adjust the size
		passwordLabel.setBounds(101, 182, 110, 14);
		// Add the label to the frame
		this.add(passwordLabel);

		// Create a label for "Repeat Password:"
		repeatLabel = new JLabel("Repeat Password:");
		// Align the label to the right
		repeatLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Set the font, color, and position of the label
		repeatLabel.setFont(labelFont);
		// Set the color of the text to white
		repeatLabel.setForeground(Color.WHITE);
		// Position where the text should be and adjust the size
		repeatLabel.setBounds(65, 207, 146, 14);
		// Add the label to the frame
		this.add(repeatLabel);

		// Create a text field for the username
		userField = new UsernameField();
		// Set the number of columns for the JTextField
		userField.setColumns(10);
		// Position where the field should be and adjust the size
		userField.setBounds(223, 156, 161, 16 + 6);
		// Add the text field to the frame
		this.add(userField);

		// Create a text field for the password
		passwordField = new PasswordField();
		// Set the number of columns for the JTextField
		passwordField.setColumns(10);
		// Position where the field should be and adjust the size
		passwordField.setBounds(223, 181, 161, 16 + 6);
		// Add the text field to the frame
		this.add(passwordField);

		// Create a text field for repeating the password
		repeatField = new PasswordField();
		// Set the number of columns for the JTextField
		repeatField.setColumns(10);
		// Position where the field should be and adjust the size
		repeatField.setBounds(223, 206, 161, 16 + 6);
		// Add the text field to the frame
		this.add(repeatField);

		// Add background label with a scaled image
		backgroundLabel = new JLabel(new ImageIcon(new ImageIcon("Background.png").getImage().getScaledInstance(488, 374, Image.SCALE_DEFAULT)));
		backgroundLabel.setBounds(0, 0, 488, 374);
		// Add the background label to the frame
		this.add(backgroundLabel);

		// Make the frame visible
		this.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, fall back to cross-platform
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				// Not worth my time
			}
		}
		new SignUpUI();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//A sound effect when the buttons are clicked
		new SoundEffect("click.wav");
		
		//If this button is clicked, do the following
		if(e.getSource() == backButton) {
			// Open the InitialUI class when the button is clicked
			new InitialUI();
			// Dispose of the LoginUI
			this.dispose();
		}
		//If this button is clicked, do the following
		if (e.getSource() == signUpButton) {
		    try {
		        // Check if name is valid
		        if (nameField.isValid()) {
		            // Check if username is valid
		            if (userField.isValid()) {
		                // Check if password is valid
		                if (passwordField.isValid()) {
		                    // Check if repeated password is valid
		                    if (repeatField.isValid()) {
		                        // Check if passwords and repeated password matches
		                        if (passwordField.getText().equals(repeatField.getText())) {
		                            // Check if balance is valid
		                            if (balanceField.isValid()) {
		                                // Check if phone number is valid
		                                if (phoneField.isValid()) {
		                                    // Create an account with the provided information
		                                    Account account = new Account(userField.getText(), passwordField.getText(), nameField.getText(),
		                                            new PhoneNumber(phoneField.getText()), Double.parseDouble(balanceField.getText()));
		                                    // Save the account to a file
		                                    account.saveToFile();
		                                    // Open the main menu with the created account
		                                    new MainMenu(account);
		                                    // Dispose of the SignUpUI
		                                    this.dispose();
		                                } else {
		                                	//display message
		                                    JOptionPane.showMessageDialog(null, "Please ensure phone number is valid!");
		                                }
		                            } else {
		                            	//display message
		                                JOptionPane.showMessageDialog(null, "Please ensure balance is valid!");
		                            }
		                        } else {
		                        	//display message
		                            JOptionPane.showMessageDialog(null, "Passwords do not match!");
		                        }
		                    } else {
		                    	//display message
		                        JOptionPane.showMessageDialog(null, "Please ensure repeated password is valid!");
		                    }
		                } else {
		                	//display message
		                    JOptionPane.showMessageDialog(null, "Please ensure password is valid!");
		                }
		            } else {
		            	//display message
		                JOptionPane.showMessageDialog(null, "Please ensure username is valid!");
		            }
		        } else {
		        	//Display message
		            JOptionPane.showMessageDialog(null, "Please ensure name is valid");
		        }
		    } catch (Exception e1) {
		    	//Display message
		        JOptionPane.showMessageDialog(null, "Please fill in all fields!");
		    }
		}

	}

}
