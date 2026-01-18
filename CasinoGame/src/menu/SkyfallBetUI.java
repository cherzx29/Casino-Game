package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import menuComponents.Account;
import menuComponents.BalanceField;
import menuComponents.SoundEffect;
import skyfall.SkyfallUI;

/*
 * Name: Cherith Boya
 * Date: 2024-01-19
 * Description: The user of this program will be able to wager on the Skyfall game.Input requirements for the user 
 * 				include a goal score (the better the score, the more money you can get) and a bet (the amount of 
 * 				money you are willing to wager on the game). The user interface displays the maximum amount of 
 * 				money that the user can win after placing their bets. They could then click "place bet" to start the 
 * 				game after that. It also has a lot of error trapping and information that would be useful to the user. 
 * 				For instance, if the user doesn't enter a number above 100 in the goal score, the program will tell
 * 				the user to fix it. 
 *
 */
public class SkyfallBetUI extends JFrame implements ActionListener, KeyListener {
	/*
	 * Declare Global Variables and define the UI components
	 */
	private JLabel backgroundLabel, betLabel, scoreLabel, winLabel, headerLabel;
	private BalanceField betField, scoreField;
	private JTextField winField;
	private Account account;
	private Font headerFont, labelFont, buttonFont;
	private JButton betButton, exitButton;
	/**
	 * Overload Constructor
	 * - Takes in the account in the parameters
	 */
	public SkyfallBetUI(Account account) {
		// Create the frame
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
		this.setSize(424, 308);
		//Set the frame where it can't be resized
		this.setResizable(false);

		// Set the account instance to account
		this.account = account;

		// Initialize Fonts with the same font, different thicknesses, and different sizes
		headerFont = new Font("Rockwell", Font.BOLD, 33);
		labelFont = new Font("Rockwell", Font.PLAIN, 17);
		buttonFont = new Font("Rockwell", Font.PLAIN, 12);

		// Create a label that displays a "Skyfall Bet" text
		headerLabel = new JLabel("Skyfall Bet");
		// Align the label to the center
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// Set the font for the label
		headerLabel.setFont(headerFont);
		// Make the text label white
		headerLabel.setForeground(Color.WHITE);
		// Set the position and size of the label
		headerLabel.setBounds(17, 11, 348, 40);
		// Add the label to the JFrame
		this.add(headerLabel);

		// Create a button that displays "Exit" text
		exitButton = new JButton("Exit");
		// A way to handle and listen to button clicks
		exitButton.addActionListener(this);
		// Set the font for the button
		exitButton.setFont(buttonFont);
		// Set the position and size of the button
		exitButton.setBounds(49, 208, 159, 23);
		// Add the button to the JFrame
		this.add(exitButton);

		// Create a button that displays "Place Bet" text
		betButton = new JButton("Place Bet");
		// Set the font for the button
		betButton.setFont(buttonFont);
		// Set the position and size of the button
		betButton.setBounds(218, 208, 159, 23);
		// Add an action listener to the button
		betButton.addActionListener(this);
		// Add the button to the JFrame
		this.add(betButton);

		// Create a balance field for entering the bet
		betField = new BalanceField();
		// Set the position and size of the field
		betField.setBounds(139, 105, 235, 22);
		// Add a key listener to the field
		betField.addKeyListener(this);
		// Add the field to the JFrame
		this.add(betField);

		// Create a balance field for entering the goal score
		scoreField = new BalanceField();
		// Set the position and size of the field
		scoreField.setBounds(139, 62, 235, 22);
		// Add a key listener to the field
		scoreField.addKeyListener(this);
		// Add the field to the JFrame
		this.add(scoreField);

		// Create a text field for displaying the win sum
		winField = new JTextField();
		// Set the position and size of the field
		winField.setBounds(139, 149, 235, 22);
		// Set the field to be non-editable
		winField.setEditable(false);
		// Add the field to the JFrame
		this.add(winField);

		// Create a label that displays "Initial Bet:"
		betLabel = new JLabel("Initial Bet:");
		// Align the label to the center
		betLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// Set the font for the label
		betLabel.setFont(labelFont);
		// Set the position and size of the label
		betLabel.setBounds(20, 105, 120, 23);
		// Make the text label white
		betLabel.setForeground(Color.WHITE);
		// Add the label to the JFrame
		this.add(betLabel);

		// Create a label that displays "Goal Score:"
		scoreLabel = new JLabel("Goal Score:");
		// Align the label to the center
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// Set the font for the label
		scoreLabel.setFont(labelFont);
		// Set the position and size of the label
		scoreLabel.setBounds(24, 62, 101, 23);
		// Make the text label white
		scoreLabel.setForeground(Color.WHITE);
		// Add the label to the JFrame
		this.add(scoreLabel);

		// Create a label that displays "Win Sum:"
		winLabel = new JLabel("Win Sum:");
		// Align the label to the center
		winLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// Set the font for the label
		winLabel.setFont(labelFont);
		// Set the position and size of the label
		winLabel.setBounds(20, 149, 112, 23);
		// Make the text label white
		winLabel.setForeground(Color.WHITE);
		// Add the label to the JFrame
		this.add(winLabel);

		// Add background label
		backgroundLabel = new JLabel(new ImageIcon(new ImageIcon("Background.png").getImage().getScaledInstance(424, 308, Image.SCALE_DEFAULT)));
		// Set the position and size of the label
		backgroundLabel.setBounds(0, 0, 424, 308);
		// Add the label to the JFrame
		this.add(backgroundLabel);

		setVisible(true); // Make visible
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
		new SkyfallBetUI(account);
	}

	public String checkState() {
		// Create a sound effect for when the buttons are clicked
		new SoundEffect("click.wav");
		
		
		// Check if the bet field is empty
		if (betField.getText().isEmpty()) {
		    // Return a message indicating an empty bet field
		    return "Apple";
		}

		// Check if the score field is empty
		if (scoreField.getText().isEmpty()) {
		    // Return a message
		    return "Apple";
		}

		// Check if the bet field is valid
		if (betField.isValid()) {
		    // Check if the score field is valid
		    if (scoreField.isValid()) {
		        // Check if the bet amount is not greater than the account balance
		        if (!(betField.getBalance() > account.getBalance())) {
		            // Check if the score is an integer
		            if ((scoreField.getBalance() % 1) == 0) {
		                // Check if the score is greater than 100
		                if (scoreField.getBalance() > 100) {
		                    // Return null
		                    return null;
		                } else {
		                    // Return a message
		                    return "Please enter a score greater than 100";
		                }
		            } else {
		                // Return a message
		                return "Please enter an integer for score!";
		            }
		        } else {
		            // Return a message
		            return "Can't bet more than account balance!\nYour Account balance is: " + formatter.format(account.getBalance());
		        }
		    } else {
		        // Return a message
		        return "Please ensure a valid score has been placed";
		    }
		} else {
		    // Return a message 
		    return "Please ensure a valid bet amount has been placed";
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//If this button is clicked, do the following
		if(e.getSource() == exitButton) {
			//Send account to the MainMenu constructor
			new MainMenu(account);
			// Dispose of the current SkyfallBetUI JFrame
			this.dispose();
		}
		//If this button is clicked, do the following
		if(e.getSource() == betButton) {
			//If checkstate equals to null, do the following
			if(checkState() == null) {
				//Call SkyfallUI constructor and input the information needed for the game to operate
				new SkyfallUI(account, scoreField.getBalance().intValue(), betField.getBalance());
				// Dispose of the current SkyfallBetUI JFrame
				dispose();
			}
			//If checkstate equals to "Apple", do the following
			else if(checkState() == "Apple") {
				// Do nothing
			}
			//Else, do the following
			else {
				//Display message
				JOptionPane.showMessageDialog(null, checkState());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// number formatting
    public NumberFormat formatter = NumberFormat.getCurrencyInstance();
    
	@Override
	public void keyReleased(KeyEvent e) {
		//Check if the state is null
		if(checkState() == null) {
			//Calculate the score and set it in the winField
			winField.setText("" + formatter.format(SkyfallUI.calculateWinnings(scoreField.getBalance().intValue(), betField.getBalance())));
		}
	}
}
