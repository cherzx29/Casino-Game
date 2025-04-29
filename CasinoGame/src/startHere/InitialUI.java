/**
 * 
 */
package startHere;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import menu.LoginUI;
import menu.SignUpUI;
import menuComponents.SoundEffect;

/**
 * Name: Cherith Boya
 * Date: 2024-01-19
 * Description: This class is the start up screen for when the user start the program. In this class, the user can click on three buttons
 * 				; sign in, register, or help. Each button will have a designated class it will go to. If the user clicks on the help button
 * 				, they can read how this casino game works and can then register and login in when they read it. Feature; they click on the
 * 				button it will have a sound effect. Other than that, it's a pretty straight forward class.
 *
 */
public class InitialUI extends JFrame implements ActionListener{
	/*
	 * Initialize Global Variables
	 */
	JLabel welcomeLabel, backgroundLabel;
	JButton createBtn, signInBtn, helpBtn;
	Font buttonFont, labelFont;
	JTextArea helpText;
	JScrollPane scroll;

	/**
	 * Default Constructor 
	 */
	public InitialUI(){
		//Create the frame
		super("Welcome!");
		// Set default close operation for the JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Sets the location of the frame
		this.setLocationRelativeTo(null);
		// Sets the location of the frame
		this.setResizable(false);
		// No automatic layout and only manual
		this.setLayout(null); // Set layout to null
		// Set an icon image to the JFrame
		this.setIconImage(new ImageIcon("LuckyQueenNoText.png").getImage()); // Set image icon
		// Set the size of the frame
		this.setSize(550, 400);

		// Initialize Font with the certain font, size, and thickness
		labelFont = new Font("Rockwell", Font.BOLD, 20);

		// Create a label that displays a "Welcome  to  Lucky  Queen  Online  Casino!" text
		welcomeLabel = new JLabel("Welcome  to  Lucky  Queen  Online  Casino!");
		//Set the label to its certain font
		welcomeLabel.setFont(labelFont);
		//Align the label to the center
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//Set the position and size of the button
		welcomeLabel.setBounds(37, 47, 455, 28);
		//Make the text label white
		welcomeLabel.setForeground(Color.WHITE);
		//Add the label to the JFrame
		this.add(welcomeLabel);

		// Create Create Button
		createBtn = new JButton("Create Account");
		// Set the font for the button
		createBtn.setFont(buttonFont);
		// Set the position and size of the button
		createBtn.setBounds(158, 125, 207, 42);
		// A way to handle and listen to button clicks
		createBtn.addActionListener(this);
		// Add the button to the frame
		this.add(createBtn);

		// Create Sign In Button
		signInBtn = new JButton("Sign In");
		// Set the font for the button
		signInBtn.setFont(buttonFont);
		// Set the position and size of the button
		signInBtn.setBounds(158, 176, 207, 42);
		// A way to handle and listen to button clicks
		signInBtn.addActionListener(this);
		// Add the button to the frame
		this.add(signInBtn);

		// Create Help Button
		helpBtn = new JButton("Help");
		// Set the font for the button
		helpBtn.setFont(buttonFont);
		// Set the position and size of the button
		helpBtn.setBounds(158, 229, 207, 42);
		// A way to handle and listen to button clicks
		helpBtn.addActionListener(this);
		// Add the button to the frame
		this.add(helpBtn);

		// Add background label with a scaled image
		backgroundLabel = new JLabel(new ImageIcon(new ImageIcon("Background.png").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT)));
		backgroundLabel.setBounds(0, 0, 550, 400);
		// Add the background label to the frame
		this.add(backgroundLabel);

		//Create JTextArea of 30 rows and 50 columns
		helpText = new JTextArea(30, 50);
		// Enable line wrapping in the JTextArea
		helpText.setLineWrap(true);
		//User can't edit anything
		helpText.setEditable(false);
		// Enable word wrapping in the JTextArea
		helpText.setWrapStyleWord(true);
		// Set the text content of the JTextArea
		helpText.setText("User Manual:\r\n"
				+ "\nIn this project, there are various classes in which the user has to navigate through. Therefore,\r\n"
				+ "this user manual will take you through several UI(s) and will give you instructions on how it\r\n"
				+ "works.\r\n\n"
				+ "InitialUI: In this welcome page, you will be greeted with a welcome title and will be addressed to\r\n"
				+ "four buttons\r\n"
				+ "\n- “Sign in” Button → Refers you to the sign page\r\n"
				+ "- “Create Account” Button → Refers you to the login page\r\n"
				+ "Sign Up UI: If you click on the “Create Account” button, you will be entitled to fill in various\r\n"
				+ "information, with certain criterias in each category\r\n"
				+ "\n(If you hover the bar of where your supposed to type, a certain criteria for each category will\r\n"
				+ "then get showcased in which you can fill in the information according to it)\r\n"
				+ "\n- Name: Required to type in your first and last name but containing no symbols and\r\n"
				+ "numbers.\r\n"
				+ "- Phone Number: Required to fill in your phone number in the format of\r\n"
				+ "(+CountryCode-AreaCode-Number)\r\n"
				+ "- Initial Balance: Required to input the amount of money to start of with(has to be greater\r\n"
				+ "than 0)\r\n"
				+ "- Username: Required to create a username that is more than 3 characters long, it\r\n"
				+ "contains no special characters, and it shouldn’t be already in use from another account\r\n"
				+ "- Password: Create any password but it SHOULD contain at least one lowercase letter,\r\n"
				+ "one uppercase letter, one number, one special character, and be longer than 6\r\n"
				+ "characters.\r\n"
				+ "- Repeat Password: Input the same EXACT password you chose to create.\r\n"
				+ "\nYou will then get a choice of two buttons to click.\r\n"
				+ "- “Back” Button - If you click on this button, you will go back to the Initial UI\r\n"
				+ "- “Create Account” Button - If you click on this button, you will then proceed to the casino\r\n"
				+ "(More information will be provided further on)\r\n"
				+ "\nLogin UI: If you click on the ”Sign In” button, you will be entitled to fill in two categories of\r\n"
				+ "information.\r\n"
				+ "- Username: Enter the username you created before\r\n"
				+ "- Password: Enter the password you created before\r\n"
				+ "\r\n"
				+ "\nYou will then get a choice of two buttons to click.\r\n"
				+ "- “Back” Button - If you click on this button, you will go back to the Initial UI\r\n"
				+ "- “Login” Button - If you click on this button, you will then proceed to the casino (More\r\n"
				+ "information will be provided further on)\r\n"
				+ "\nMain Menu UI: Once you are in this UI, you will get greeted with you name shown, and you will\r\n"
				+ "have 3 buttons, and two pictures(games)\r\n"
				+ "\n- “Make Transaction” button: If you click this button, you will then get loaded into a\r\n"
				+ "different page where you can make transactions(More information provided further on).\r\n"
				+ "- “Settings” button: If you click this button, you will get open to your personal\r\n"
				+ "information(More information provided further on).\r\n"
				+ "- “Exit” button: If you click on this button, you will exit the Casino with all of your\r\n"
				+ "information saved in a file.\r\n"
				+ "\n------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "\n- “SKYFALL” Game: Once you click on the SKYFALL game(image), instructions on how to\r\n"
				+ "play the game will pop up, in order for you to analyze. Once done understanding it, click\r\n"
				+ "on “ok” and it will then proceed you to the game(More information provided further on)\r\n"
				+ "- “BOMBS and RICHES” Game: Once you click on the BOMBS and RICHES\r\n"
				+ "game(image), instructions on how to play the game will pop up, in order for you to\r\n"
				+ "analyze. Once done understanding it, click on “ok” and it will then proceed you to the\r\n"
				+ "game(More information provided further on).\r\n"
				+ "\nMake Transaction UI: Once you click on the “Make Transaction” button in the main menu, you\r\n"
				+ "can see all the money you gained/lost and, there are 2 buttons and 2 selections.\r\n"
				+ "\n● In this UI, you can see your transaction history. It includes the transaction type, initial\r\n"
				+ "balance, amount lost or gained, and the final balance.\r\n"
				+ "\n- Withdraw Selection: If you select this option, you input money to get out from your final\r\n"
				+ "balance(If there’s a balance of $0, then a withdrawal can not be made). Then click on the\r\n"
				+ "“Make Transaction” button for your withdrawal to work.\r\n"
				+ "- Deposit Selection: If you select this option, you input money to add to your final\r\n"
				+ "balance(You can add an unlimited amount of money). Then click on the “Make\r\n"
				+ "Transaction” button for your deposit to work.\r\n"
				+ "- “Exit” button: If you click on this button, you will exit the Casino with all of your\r\n"
				+ "information saved in a file.\r\n"
				+ "- “Make Transaction” button: Once you input the amount of money for withdrawal or\r\n"
				+ "deposit, click on the button for it to occur.\r\n"
				+ "\r\n"
				+ "\nSettings UI: Once you click on the “Settings” button, you can make changes to the information\r\n"
				+ "you provided us.\r\n"
				+ "- “Username”: You can’t change your username so whatever you have is what you will\r\n"
				+ "stick with.\r\n"
				+ "- “Password”- In order to change your password, you have to first input your original\r\n"
				+ "password, and can then proceed forward.\r\n"
				+ "- “Name”- In order to change your name, you have to first input your original password,\r\n"
				+ "and can then proceed forward.\r\n"
				+ "- “Phone Number”- In order to change your phone number, you have to first input your\r\n"
				+ "original password, and can then proceed forward.\r\n"
				+ "- “Balance”- You can’t change your balance but can make withdrawals and deposits to\r\n"
				+ "change it by clicking on the “Make Transaction” button on the main menu.\r\n"
				+ "\nSKYFALL Bet UI: Once you click on the game, it will give you the instructions on how to play\r\n"
				+ "the game, and then proceed you to a UI to make bets. You’ll also see a goal score input, and\r\n"
				+ "initial bet input. There will also be two buttons to choose from.\r\n"
				+ "\n- Goal Score: This is where you have to input what score you think you will get(Number\r\n"
				+ "has to be greater than 100)\r\n"
				+ "- Initial Bet: Input the amount of money to bet on the game. This money will get deducted\r\n"
				+ "from your balance amount.\r\n"
				+ "\nIn this Win Sum category, you don’t add anything since it will give you a number in which\r\n"
				+ "\n- “Place Bet” button: Once you input the first two values, and a value shows up in the Win\r\n"
				+ "Sum category, click “place bet” in order to play the game.\r\n"
				+ "- “Exit” button: If you click on this button, you will exit the game and go back to the main\r\n"
				+ "menu\r\n"
				+ "\nSKYFALL Game UI: After you read the instructions, and placed your bets, you will then play the\r\n"
				+ "game.\r\n"
				+ "\nObjective:\r\n\n"
				+ "\n- Your main objective is to collect as many coins as possible which would result in\r\n"
				+ "reaching your desired goal faster\r\n"
				+ "- You’ll have 3 lives. A life will get lost if you lose a coin. Lose all three, it’s game over and\r\n"
				+ "you will lose all your money\r\n"
				+ "- Percentage bar indicates how far you are to your goal. Once it reaches the goal, the\r\n"
				+ "game stops and you essentially win. You will also gain money from the win.\r\n"
				+ "- Once you lose or win, you’ll go back to the main menu and you can see your recent\r\n"
				+ "transaction that occurred.\r\n"
				+ "\r\n"
				+ "Movements: Use the left and right keys in order to move the basket to collect as many coins as\r\n"
				+ "possible.\r\n"
				+ "\nBOMBS and RICHES UI: Once you click on the game, it will give you the instructions on how to\r\n"
				+ "play the game, and then proceed you to a UI to play the game. There will be an input of Mines\r\n"
				+ "and Bet Amount. There will also be a “bet” button\r\n"
				+ "\nObjective:\r\n"
				+ "- You will first select how many mines you want there to be in the game as well as how\r\n"
				+ "much money you want to be.\r\n"
				+ "- Once you click the “Bet” button, you will start to select random square\r\n"
				+ "- Each time you click on a square and a diamond pops up, you get money. You can see\r\n"
				+ "how much you gain just by looking at the Total label(bottom left). You can cash out\r\n"
				+ "anytime you want or if you want to keep going, you can. However, make sure you don't\r\n"
				+ "hit a bomb!\r\n"
				+ "- If you click on a square and a bomb appears, then you lose all your money you earned.\r\n"
				+ "Once you lose, you can instantly place your bets and the amount of mines you want\r\n"
				+ "again. Your balance will also get updated if you want to play again.\r\n"
				+ "- If you want to exit, click on the x button(top right) and you will go back to the main menu.\r\n"
				+ "\nInputs:\r\n"
				+ "\n- Mines: In the Mines input, you’ll select how many mines you’d like to add in the game.\r\n"
				+ "- Bet Amount: In the bet amount, you add a certain amount of money you’d like to bet in\r\n"
				+ "which it will also deduct from your balance amount.\r\n"
				+ "\nButton:\r\n"
				+ "- “Bet” button: Once you click on this button after you’ve selected the amount of mines\r\n"
				+ "you want and how much money you bet, then you can proceed to play the game.");
		// Create a JScrollPane to allow scrolling in the JTextArea
		scroll = new JScrollPane(helpText);
		// Make visible
		setVisible(true); 
	}

	/**
	 * @param args
	 */
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
		//Call InitialUI
		new InitialUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//A sound effect when the buttons are clicked
		new SoundEffect("click.wav");
		
		//If this button is clicked, do the following
		if(e.getSource() == createBtn) {
			//Call the SignUpUI
			new SignUpUI();
			// Dispose of the InitialUI
			this.dispose();
		}
		//If this button is clicked, do the following
		if(e.getSource() == signInBtn) {
			//Call the LoginUI
			new LoginUI();
			// Dispose of the InitialUI
			this.dispose();
		}
		//If this button is clicked, do the following
		if (e.getSource() == helpBtn) {
			//Display message
			JOptionPane.showInternalMessageDialog(null, scroll);
		}
	}
}