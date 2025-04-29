package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import menuComponents.Account;
import menuComponents.BalanceField;
import menuComponents.SoundEffect;
import menuComponents.Transaction;
import menuComponents.TransactionList;

/**
 * Name: Sanat Kanwal
 * Date:2024-01-19
 * Description: In this class, this is where the user can see all of their transactions. They can see how much money they started with 
 * 				(by looking at top) and how much has been gained or lost as they played the games. The transaction is formatted in a way
 * 				like this; Account(type:, initial balance:, amount(gained/lost):, final balance: ). You can also make adjustments to your 
 * 				final balance. If you want to add more money to be applicable to play the games again, then you can deposit money(unlimited)
 * 				by clicking on the deposit button and then 'make transaction' button. If you want to remove money from your final balance, 
 * 				then you can click on the withdraw button and then 'make transaction' button. Once viewing, you can then click on exit button
 * 				to go back to main menu. 
 *
 */
public class TransactionUI extends JFrame implements ActionListener{
	/*
	 * Declare private Variables and define the UI components
	 */
	private JLabel backgroundLabel, headerLabel;
	private TransactionList list;
	private JTextArea historyArea;
	private BalanceField amountArea;
	private JScrollPane scrollPane;
	private JButton exitButton, transactionButton;
	private Font headerFont, labelFont, buttonFont;
	private JRadioButton withdrawRadio, depositRadio;
	private ButtonGroup radio;
	private Account account;

	/**
	 * 
	 * Overload Constructor
	 * - Takes in the account in the parameters
	 */

	public TransactionUI(Account account) throws IOException {
		// Create the frame
		super("Welcome!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Set default close operation for the JFrame
		this.setLocationRelativeTo(null);
		// Sets the location of the frame
		this.setResizable(false);
		// No automatic layout and only manual
		this.setLayout(null); // Set layout to null
		// Set an icon image to the JFrame
		this.setIconImage(new ImageIcon("LuckyQueenNoText.png").getImage()); // Set image icon
		// Set the size of the frame
		this.setSize(460, 412);

		//Set the account instance to account
		this.account = account;

		// Create a TransactionList with an initial capacity of 10
		list = new TransactionList(10);

		// Initialize Fonts with the same font, different thicknesses, and different sizes
		headerFont = new Font("Rockwell", Font.BOLD, 33);
		labelFont = new Font("Rockwell", Font.PLAIN, 18);
		buttonFont = new Font("Rockwell", Font.PLAIN, 12);

		// Create a label that displays a "Skyfall Bet" text
		headerLabel = new JLabel("Transaction History");
		// Align the label to the center
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// Set the font for the label
		headerLabel.setFont(headerFont);
		// Make the text label white
		headerLabel.setForeground(Color.WHITE);
		// Set the position and size of the label
		headerLabel.setBounds(49, 11, 348, 40);
		// Add the label to the JFrame
		this.add(headerLabel);

		// Create a button that displays "Exit" text
		exitButton = new JButton("Exit");
		// Set the font for the button
		exitButton.setFont(buttonFont);
		// A way to handle and listen to button clicks
		exitButton.addActionListener(this);
		// Set the position and size of the button
		exitButton.setBounds(59, 340, 159, 23);
		// Add the button to the JFrame
		this.add(exitButton);

		// Create a button that displays "Make Transaction" text
		transactionButton = new JButton("Make Transaction");
		// Set the font for the button
		transactionButton.setFont(buttonFont);
		// A way to handle and listen to button clicks
		transactionButton.addActionListener(this);
		// Set the position and size of the button
		transactionButton.setBounds(250, 340, 159, 23);
		// Add the button to the JFrame
		this.add(transactionButton);

		// Create a scroll panel
		scrollPane = new JScrollPane();
		// Set the position and size of the scroll panel
		scrollPane.setBounds(59, 52, 328, 221);
		// Add the scroll panel to the JFrame
		this.add(scrollPane);

		// Create a JTextArea
		historyArea = new JTextArea();
		// Set the font for the JTextArea
		historyArea.setFont(labelFont);
		//User can't make changes
		historyArea.setEditable(false);
		// Set the view for the scroll pane to the history area
		scrollPane.setViewportView(historyArea);

		// If there are transactions in the list
		if(list.readFromFile(account.getUsername())) { 
			// Set text area to list
			historyArea.setText(list.toString()); 
		}

		//CCreate a BalanceField
		amountArea = new BalanceField();
		// Set the position and size of the field
		amountArea.setBounds(112, 307, 235, 22);
		// Add the field to the JFrame
		this.add(amountArea);

		// Create a button that displays "Withdraw" text
		withdrawRadio = new JRadioButton("Withdraw");
		//Set the button in which it can be selected
		withdrawRadio.setSelected(true);
		// Make the text button white
		withdrawRadio.setForeground(Color.WHITE);
		// Set the position and size of the label
		withdrawRadio.setBounds(109, 280, 109, 23);
		// Add the button to the JFrame
		this.add(withdrawRadio);

		// Create a button that displays "Deposit" text
		depositRadio = new JRadioButton("Deposit");
		// Make the text button white
		depositRadio.setForeground(Color.WHITE);
		// Make the text button white
		depositRadio.setBounds(238, 280, 109, 23);
		// Add the button to the JFrame
		this.add(depositRadio);

		// Create a ButtonGroup(group for buttons) and add radio buttons to it
		radio = new ButtonGroup();
		radio.add(depositRadio);
		radio.add(withdrawRadio);

		// Add background label
		backgroundLabel = new JLabel(new ImageIcon(new ImageIcon("Background.png").getImage().getScaledInstance(488, 374, Image.SCALE_DEFAULT)));
		// Set the position and size of the label
		backgroundLabel.setBounds(0, 0, 488, 374);
		// Add the label to the JFrame
		this.add(backgroundLabel);

		// Make visible 
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
		// Create an account object
		Account account = new Account("Apple", "Banana@1");
		//Initiate the MainMenu
		account.login();
		try {
			//Create new TransactionUI with an account
			new TransactionUI(account);
		} catch (IOException e) {
			//Tool to handle the exceptions and errors
			e.printStackTrace();
		}
	}

	public void reset(Transaction transaction) {
		//Insert transactions in list
		list.insertTransaction(transaction);
		//Set text for the updated transaction list
		historyArea.setText(list.toString()); // Set text area to list
		//Save account to the files
		account.saveToFile();

		try {
			//Save transactions list to a file, based on the accounts username
			list.saveToFile(account.getUsername());
		} catch (IOException e) {
			//Tool to handle the exceptions and errors
			e.printStackTrace();
		}
		//Clear the amountArea
		amountArea.setText("");
	}

	// number formatting
	public NumberFormat formatter = NumberFormat.getCurrencyInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		// Create a sound effect for when the buttons are clicked
		new SoundEffect("click.wav");

		//If the user clicks on this button, do the following
		if(e.getSource() == transactionButton) {
			//Check if amountArea is valid
			if(amountArea.isValid()) {
				//Check if amountArea is not less than 0
				if(!(amountArea.getBalance() < 0)) {
					//Check if the withdrawRadio is selected
					// Check if the withdraw radio button is selected
					if (withdrawRadio.isSelected()) {
						// Check if the withdrawal amount is not greater than the account balance
						if (!(Math.round(amountArea.getBalance() * 100.0) / 100.0 > Math.round(account.getBalance() * 100.0) / 100.0)) {
							//Get current balance in the account
							double balance = account.getBalance();
							// Perform the withdrawal operation
							account.withdraw(amountArea.getBalance());
							// Create a withdrawal transaction record with information in it
							Transaction record = new Transaction('W', balance, amountArea.getBalance());
							// Reset the transaction and update it in the UI
							this.reset(record);
						} else {
							// Display an error message if withdrawal amount exceeds account balance
							JOptionPane.showMessageDialog(null, "You cannot withdraw more money than you have!\nYou have: " + formatter.format(account.getBalance()));
						}
					} else {
						//Get current balance in the account
						double balance = account.getBalance();
						// Perform the deposit operation
						account.deposit(amountArea.getBalance());
						// Create a deposit transaction record
						Transaction record = new Transaction('D', balance, amountArea.getBalance());
						// Reset the transaction and update UI
						this.reset(record);

					}
				}
				else {
					//Display message
					JOptionPane.showMessageDialog(null, "Please enter balance greater then 0!");
				}
			}
			else {
				//Display message
				JOptionPane.showMessageDialog(null, "Please enter a valid balance!");
			}
		}
		//If button is clicked, do the following
		if(e.getSource() == exitButton) {
		///Send account to the MainMenuUI constructor
			new MainMenu(account);
			// Dispose of the current TransactionUI JFrame
			dispose();
		}
	}

}
