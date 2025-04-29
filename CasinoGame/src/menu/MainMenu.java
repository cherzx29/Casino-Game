package menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import menuComponents.Account;
import menuComponents.PhoneNumber;
import menuComponents.SoundEffect;
import mine.Mines;

/**
 * Name: Cherith Boya
 * Date: 2024-01-18
 * Description: This UI, is basically the menu of the casino. Once the user finished logging in or finished registering
 * 				they will be brought up to this page. This menu consists of 3 buttons which can take you to the designated 
 * 				locations if clicked on. First button takes you to the settings, the second button takes you to the transactions, 
 * 				and the third allows you to exit the program. However, most importantly, it contains the games in which you can
 * 				play by clicking on images of them. This program also has a description for each game when the game is clicked on
 * 				, in order for the user to understand the game.
 *
 */
public class MainMenu extends JFrame implements ActionListener{

	/*
	 * Declare private Variables and define the UI components
	 */
	private Font headerFont, buttonFont;
	private JButton skyfallButton, mineButton, exitButton, transactionButton, settingsButton;
	private JLabel headerLabel, buttonsLabel;
	private Account account;

	/**
	 * overload constructor
	 * - Get account within the parameters
	 */
	public MainMenu(Account account) {
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
		this.setSize(571, 413);
		//Set the background color
		this.getContentPane().setBackground(new Color(15, 15, 15));
		//Set the frame where it can't be resized
		this.setResizable(false);

		// Set the account instance to account
		this.account = account;

		// Initialize Fonts with the same font, different thicknesses, and different sizes
		headerFont = new Font("Rockwell", Font.BOLD, 33);
		buttonFont = new Font("Rockwell", Font.PLAIN, 12);

		//Create a button that displays a image
		skyfallButton = new JButton(new ImageIcon(new ImageIcon("Skyfall.png").getImage().getScaledInstance(243, 166, Image.SCALE_DEFAULT)));
		//Make the borders of the button empty
		skyfallButton.setBorder(BorderFactory.createEmptyBorder());
		//Make it a transparent button
		skyfallButton.setContentAreaFilled(false);
		//A way to handle and listen to button clicks
		skyfallButton.addActionListener(this);
		//Set the position and size of the button
		skyfallButton.setBounds(29, 92, 243, 166);
		//Add the button to the frame
		this.add(skyfallButton);

		// Create a label that displays a "welcome" text
		headerLabel = new JLabel("Welcome, " + this.account.getName());
		//Align the label to the center
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//Set the label to its certain font
		headerLabel.setFont(headerFont);
		//Make the text label white
		headerLabel.setForeground(Color.WHITE);
		//Set the position and size of the button
		headerLabel.setBounds(65, 22, 422, 40);
		//Add the label to the JFrame
		this.add(headerLabel);

		//Create a button where theres's an image displaying on it and is scaled in a default way
		mineButton = new JButton(new ImageIcon(new ImageIcon("Bombs.png").getImage().getScaledInstance(243, 166, Image.SCALE_DEFAULT)));
		//Create an empty border for the button
		mineButton.setBorder(BorderFactory.createEmptyBorder());
		//Make the button transparent
		mineButton.setContentAreaFilled(false);
		//A way to handle and listen to button clicks
		mineButton.addActionListener(this);
		//Set the position and size of the button
		mineButton.setBounds(299, 92, 243, 166);
		//Add the button to the JFrame
		this.add(mineButton);

		// Create a button that displays an "exit" text
		exitButton = new JButton("Exit");
		//Set it to a specific type of font
		exitButton.setFont(buttonFont);
		//Set the position and size of the button
		exitButton.setBounds(29, 307, 159, 23);
		//A way to handle and listen to button clicks
		exitButton.addActionListener(this);
		//Add the button to the JFrame
		this.add(exitButton);

		// Create a button that displays a "Make Transaction" text
		transactionButton = new JButton("Make Transaction");
		// Set it to a specific type of font
		transactionButton.setFont(buttonFont);
		//A way to handle and listen to button clicks
		transactionButton.addActionListener(this);
		// Set the position and size of the button
		transactionButton.setBounds(198, 307, 159, 23);
		// Add the button to the frame
		this.add(transactionButton);

		// Create a button that displays a "Settings" text
		settingsButton = new JButton("Settings");
		// Set it to a specific type of font
		settingsButton.setFont(buttonFont);
		// Set the position and size of the button
		settingsButton.setBounds(367, 307, 159, 23);
		//A way to handle and listen to button clicks
		settingsButton.addActionListener(this);
		// Add the button to the frame
		this.add(settingsButton);

		// Create a JLabel 
		buttonsLabel = new JLabel("");
		// Set the background color of the label
		buttonsLabel.setBackground(new Color(204, 51, 51));
		// Set the position and size of the label
		buttonsLabel.setBounds(0, 278, 555, 96);
		// Make the label opaque
		buttonsLabel.setOpaque(true);
		// Add the label to the frame
		this.add(buttonsLabel);

		// Make visible 
		this.setVisible(true);
	}

	// Main method for running the application
	public static void main(String[] args) {
		// Set the look and feel of the UI
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
		new MainMenu(account);
	}

	// ActionPerformed method to handle button clicks
	@Override
	public void actionPerformed(ActionEvent e) {
		// Create a sound effect for when the buttons are clicked
		new SoundEffect("click.wav");

		//If this button is clicked, do the following
		if (e.getSource() == skyfallButton) {
			// Display a description of how the game should work
			JOptionPane.showMessageDialog(null, "Welcome! The Skyfall game is a coin-collecting game.\nIn this game, "
					+ "you will bet an initial amount of money and aim to reach a certain score."
					+ "\nIf desired score is reached in the game, you will cash out with your prize\nScore goes up by 10 for each coin collected and you have 3 lives\n"
					+ "If you lose all three lives, you will lose your initial bet\nGood Luck!");
			
			//Send the account to the SkyfallBetUI constructor
			new SkyfallBetUI(account);
			// Dispose of the current MainMenu JFrame
			dispose(); 
		}
		//If this button is clicked, do the following
		if (e.getSource() == mineButton) {
			// Display a description of how the game should work
			JOptionPane.showMessageDialog(null, "Welcome! The Bombs to Riches game is a game of luck.\nIn this game, "
					+ "you will be given a 6 x 6 grid of tiles,\nwhere you can click them to reveal what's underneath,"
					+ " whether it be a bomb or riches!\nYou will have the choice of how many bombs you'd like on the "
					+ "board,\nwhere a higher amount of bombs will result in a higher multiplier on your bet amount.\n"
					+ "The more gems you have, the higher the payout,\nbut be careful... clicking on a bomb will reset"
					+ " your payout!");
			
			//Send the account to the Mines constructor
			new Mines(account);
			// Dispose of the current MainMenu JFrame
			dispose(); 
		}
		//If this button is clicked, do the following
		if (e.getSource() == transactionButton) {
			// Open the TransactionUI and dispose of the current MainMenu JFrame
			try {
				new TransactionUI(account);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// Dispose of the current MainMenu JFrame
			dispose(); 
		}
		if (e.getSource() == exitButton) {
			// Dispose of the current MainMenu JFrame
			dispose(); 
		}
		if (e.getSource() == settingsButton) {
			//Open the SettingsUI 
			new SettingsUI(account);
			//Dispose of the current MainMenu JFrame
			dispose();
		}
	}
}