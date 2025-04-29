package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import menuComponents.Account;
import menuComponents.NameField;
import menuComponents.PasswordField;
import menuComponents.PhoneField;
import menuComponents.PhoneNumber;
import menuComponents.UsernameField;

/**
 * Name: Cherith Boya
 * Date: 2024 - 01 - 18
 * Description: In this UI, you can essentially make changes to your information have. 
 * 				Once you get in this page, you can only edit your phone number, your password, and your name just by clicking on their
 * 				labels. The rest however can't be edited since it's permanent and is something where it's unique compared to other users
 * 				info. It will also tell you what you can edit or not, but you have to click on the labels. Once done you can hit the 
 * 				exit button to go back to the main menu.
 * 
 *
 */
public class SettingsUI extends JFrame implements ActionListener, MouseListener{
	/*
	 * Declare Global Variables and define the UI components
	 */
	private JLabel backgroundLabel, headerLabel, footerLabel, usernameLabel, passwordLabel, nameLabel, phoneLabel, balanceLabel, 
	passwordSLabel, usernameSLabel, nameSLabel, balanceSLabel, phoneSLabel;
	private JButton exitButton;
	private Account account;
	private Font headerFont, labelFont, buttonFont, footerFont;
	
    // number formatting
    public NumberFormat formatter = NumberFormat.getCurrencyInstance();

	//Create a constructor for the Settings UI class and defines the UI components
	public SettingsUI(Account account) {
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
		this.setSize(337, 323);
		//Set the frame where it can't be resized
		this.setResizable(false);

		// Set the account instance to account
		this.account = account;

		//// Initialize Fonts with the same font, different thicknesses, and different sizes
		headerFont = new Font("Rockwell", Font.BOLD, 33);
		labelFont = new Font("Rockwell", Font.BOLD, 16);
		buttonFont = new Font("Rockwell", Font.PLAIN, 12);
		footerFont = new Font("Rockwell", Font.BOLD, 13);

		// Create a label that displays a "Account Settings" text
		headerLabel = new JLabel("Account Settings");
		//Align the label to the center
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//Set the label to its certain font
		headerLabel.setFont(headerFont);
		//Make the text label white
		headerLabel.setForeground(Color.WHITE);
		//Set the position and size of the button
		headerLabel.setBounds(-11, 11, 348, 40);
		//Add the label to the JFrame
		this.add(headerLabel);

		//Create a button call that displays "Exit" text
		exitButton = new JButton("Exit");
		//A way to handle and listen to button clicks
		exitButton.addActionListener(this);
		//Set the button to its certain font
		exitButton.setFont(buttonFont);
		//Set the position and size of the button
		exitButton.setBounds(32, 250, 257, 23);
		//Add the button to the JFrame
		this.add(exitButton);

		// Create a label that displays a "Username: " text
		usernameLabel = new JLabel("Username: ");
		//Align the label to the center
		usernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		//Set the label to its certain font
		usernameLabel.setFont(labelFont);
		//Make the text label white
		usernameLabel.setForeground(Color.WHITE);
		//Set the position and size of the button
		usernameLabel.setBounds(32, 62, 112, 23);
		//Add the label to the JFrame
		this.add(usernameLabel);

		// Create a label that displays a "Password: " text
		passwordLabel = new JLabel("Password: ");
		//Align the label to the center
		passwordLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		//Set the label to its certain font
		passwordLabel.setFont(labelFont);
		//Make the text label white
		passwordLabel.setForeground(Color.WHITE);
		//Set the position and size of the button
		passwordLabel.setBounds(32, 96, 112, 23);
		//Add the label to the JFrame
		this.add(passwordLabel);

		// Create a label that displays a "Name: " text
		nameLabel = new JLabel("Name:");
		//Align the label to the center
		nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		//Set the label to its certain font
		nameLabel.setFont(labelFont);
		//Make the text label white
		nameLabel.setForeground(Color.WHITE);
		//Set the position and size of the button
		nameLabel.setBounds(32, 130, 112, 23);
		//Add the label to the JFrame
		this.add(nameLabel);

		// Create a label that displays "Phone Number:"
		phoneLabel = new JLabel("Phone Number:");
		// Align the label to the right
		phoneLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Set the font for the label
		phoneLabel.setFont(labelFont);
		// Set the position and size of the label
		phoneLabel.setBounds(10, 164, 134, 23);
		// Set the text color to white
		phoneLabel.setForeground(Color.WHITE);
		// Add the label to the frame
		this.add(phoneLabel);

		// Create a label that displays the account's username
		usernameSLabel = new JLabel(account.getUsername());
		// Align the label to the right
		usernameSLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Add a mouse listener to the label
		usernameSLabel.addMouseListener(this);
		// Set the font for the label
		usernameSLabel.setFont(labelFont);
		// Set the position and size of the label
		usernameSLabel.setBounds(177, 62, 112, 23);
		// Set the text color to white
		usernameSLabel.setForeground(Color.WHITE);
		// Add the label to the frame
		this.add(usernameSLabel);

		// Create a label that displays the covered password
		passwordSLabel = new JLabel(cover(account.getPassword().length()));
		// Align the label to the right
		passwordSLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Add a mouse listener to the label
		passwordSLabel.addMouseListener(this);
		// Set the font for the label
		passwordSLabel.setFont(labelFont);
		// Set the position and size of the label
		passwordSLabel.setBounds(177, 96, 112, 23);
		// Set the text color to white
		passwordSLabel.setForeground(Color.WHITE);
		// Add the label to the frame
		this.add(passwordSLabel);

		// Create a label that displays the account's name
		nameSLabel = new JLabel(account.getName());
		// Align the label to the right
		nameSLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Add a mouse listener to the label
		nameSLabel.addMouseListener(this);
		// Set the font for the label
		nameSLabel.setFont(labelFont);
		// Set the position and size of the label
		nameSLabel.setBounds(177, 130, 112, 23);
		// Set the text color to white
		nameSLabel.setForeground(Color.WHITE);
		// Add the label to the frame
		this.add(nameSLabel);

		// Create a label that displays the account's phone number
		phoneSLabel = new JLabel(account.getPhoneNumber() + "");
		// Align the label to the right
		phoneSLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Add a mouse listener to the label
		phoneSLabel.addMouseListener(this);
		// Set the font for the label
		phoneSLabel.setFont(labelFont);
		// Set the position and size of the label
		phoneSLabel.setBounds(155, 164, 134, 23);
		// Set the text color to white
		phoneSLabel.setForeground(Color.WHITE);
		// Add the label to the frame
		this.add(phoneSLabel);

		// Create a label that displays "Balance:"
		balanceLabel = new JLabel("Balance:");
		// Align the label to the right
		balanceLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Set the font for the label
		balanceLabel.setFont(labelFont);
		// Set the position and size of the label
		balanceLabel.setBounds(10, 198, 134, 23);
		// Set the text color to white
		balanceLabel.setForeground(Color.WHITE);
		// Add the label to the frame
		this.add(balanceLabel);

		// Create a label that displays the account's balance
		balanceSLabel = new JLabel(formatter.format(account.getBalance()) + "");
		// Align the label to the right
		balanceSLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		// Add a mouse listener to the label
		balanceSLabel.addMouseListener(this);
		// Set the font for the label
		balanceSLabel.setFont(labelFont);
		// Set the position and size of the label
		balanceSLabel.setBounds(155, 198, 134, 23);
		// Set the text color to white
		balanceSLabel.setForeground(Color.WHITE);
		// Add the label to the frame
		this.add(balanceSLabel);

		// Create a label that displays "Click on the label you want to edit!"
		footerLabel = new JLabel("Click on the label you want to edit!");
		// Align the label to the center
		footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// Set the font for the label
		footerLabel.setFont(new Font("Rockwell", Font.BOLD, 13));
		// Set the text color to white
		footerLabel.setForeground(Color.WHITE);
		// Set the position and size of the label
		footerLabel.setBounds(-18, 216, 348, 40);
		// Add the label to the frame
		this.add(footerLabel);

		// Add background image to the label and size it in a deault way
		backgroundLabel = new JLabel(new ImageIcon(new ImageIcon("Background.png").getImage().getScaledInstance(337, 323, Image.SCALE_DEFAULT)));
		// Set the position and size of the label
		backgroundLabel.setBounds(0, 0, 337, 323);
		this.add(backgroundLabel);

		// Make frame visible 
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

		// Create an account object
		Account account = new Account("Apple", "Banana@1");
		//Initiate the MainMenu
		account.login();
		//Send account to the SettingsUI constructor
		new SettingsUI(account);

	}

	/*
	 * Method to cover password
	 */
	public String cover(int length) {
		String passwordLength = "";
		for(int i = 0; i < length; i++) {
			passwordLength += "•";
		}
		return passwordLength;
	}

	/*
	 * Class for mini frame to change settings
	 */
	private class ChangeSettingsUI extends JFrame{
		public ChangeSettingsUI(){
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
			this.setSize(337, 323);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton) {
			new MainMenu(account);
			dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	    // If the button is clicked, they can change their username
	    if (e.getSource() == usernameSLabel) {
	        // Display a message
	        JOptionPane.showMessageDialog(null, "You cannot change your username!");
	    }
	    // If the button is clicked, do the following
	    else if (e.getSource() == balanceSLabel) {
	        // Display a message
	        JOptionPane.showMessageDialog(null, "Please make a transaction to change balance!");
	    } else {
	        // Create a temporary string to store input
	        String temp = "";
	        // Ask for the password in order to make changes
	        String password = JOptionPane.showInputDialog("Please enter your password to change setting: ");
	        // Check if the password inputed matches the account password
	        if (account.getPassword().equals(password)) {
	            // If the password label is clicked,  user can make changes to password
	            if (e.getSource() == passwordSLabel) {
	                // Ask for the new password
	                temp = JOptionPane.showInputDialog("Enter new password: ");
	                // Create a PasswordField for validation
	                PasswordField passwordField = new PasswordField();
	                passwordField.setText(temp);
	                // Check if the password enetered is valid
	                if (passwordField.isValidPassword()) {
	                    //Set the new password
	                    account.setPassword(temp);
	                    //Save to file
	                    account.saveToFile();
	                    //Update the displayed password
	                    passwordField.setText(cover(temp.length()));
	                } else {
	                    JOptionPane.showMessageDialog(null, "Please enter a valid password!");
	                }
	            }
	            // If the label is clicked, they can make changes to their phone number
	            if (e.getSource() == phoneSLabel) {
	                // Ask for the new phone number and store in temp value
	                temp = JOptionPane.showInputDialog("Enter new phone number: ");
	                // Create a PhoneField for validation
	                PhoneField phoneField = new PhoneField();
	                // Check if entered phone number is valid
	                if (phoneField.isValidPhoneNumber(temp)) {
	                    //Set the new phone number
	                    account.setPhoneNumber(new PhoneNumber(temp));
	                    //Save to file,
	                    account.saveToFile();
	                    //Update the displayed phone number
	                    phoneSLabel.setText(temp);
	                } else {
	                	//Ask user to enter valid phone number
	                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number!");
	                }
	            }
	            // If the label is clicked, they can make changes to their name
	            if (e.getSource() == nameSLabel) {
	                // Ask for the new name
	                temp = JOptionPane.showInputDialog("Enter new name: ");
	                // Create a NameField to check if it's valid
	                NameField nameField = new NameField();
	                // Check if the entered name is valid
	                if (nameField.isValidName(temp)) {
	                    //Set the new name
	                    account.setName(temp);
	                    //Save to file
	                    account.saveToFile();
	                    //Update the displayed name
	                    nameSLabel.setText(temp);
	                } else {
	                	//Display a message
	                    JOptionPane.showMessageDialog(null, "Please enter a valid name!");
	                }
	            }
	        } else {
	        	//Display a message
	            JOptionPane.showMessageDialog(null, "Incorrect Password!");
	        }
	    }
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
