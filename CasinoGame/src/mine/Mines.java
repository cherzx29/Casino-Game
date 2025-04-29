package mine;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import menu.MainMenu;
import menuComponents.Account;
import menuComponents.PhoneNumber;
import menuComponents.Transaction;
import menuComponents.TransactionList;
/**
 *	Name: Rajveer Karotanian
 *
 * 	Date: 1/11/2024
 *
 * 	Description:This program serves as the "Mines" game for the Casino project. The program
 * 				will take in an account, displaying the balance on a frame. The frame will
 * 				also give the user the ability to pick a number of mines on the board, and
 * 				they will be able to enter a bet amount. An increased number of mines will
 *   			result in a higher base multiplier for money return. Their current profit
 *  		 	will display on the bottom of the side panel. To start, the user will have
 *   			to press the bet button, withdrawing the amount of money bet from the balance,
 *   			where appropriate error messages will be displayed when invalid information is
 *   			entered. Pressing this button will start the round, where the amount of mines
 *   			picked will be scattered across the 6 x 6 grid, where every object not a mine
 *   			will be a gem. The objective of this game is to pick as many gems as possible,
 *   			trying to avoid clicking on a bomb. The amount of gems picked will add onto the
 *   			multiplier on the bet amount. The total cashout value will update every time a
 *   			gem is clicked, where the user will be able to cashout at any point in the game
 *   			as long as atleast one gem is clicked. If a bomb is clicked before the user is
 *   			able to cashout, they will lose all the money. Upon cashing out or a bomb blowing
 *     			up, the entire screen will be revealed. The side panel, will become interactive
 *     			again, allowing the user to bet again and repeat the process.
 *  
 *   			Method List:
 *   			- public void reset() - resets the mine game
 *   			- public void randomize(int numOfBombs) - takes in a number of bombs and randomizes them on the board
 *   			- public void gameOver(int x, int y) - Displays an explosion when a bomb is clicked and ends the game
 *   			- public void actionPerformed(ActionEvent e)  - Runs code based on button pressed
 *   			- public void updateProfit() - Updates total on frame
 *   			- public boolean tryParse() - Checks if entered bet Amount is a valid number, returning a boolean value
 */
public class Mines extends JFrame implements ActionListener{

	/**
	 * Private Instance variables
	 */
	private JButton tiles[][], sideButton;
	private ImagePicture objects[][], explosion;
	private int width, height, randRow, randColumn;
	private double bet, gemCount, numBombs, total;
	private ImageIcon gem, bomb;
	private JTextField betAmount;
	private JTextArea balanceAmount, totalAmount;
	private JComboBox<String> minesAmount;
	private TextPicture betText, balanceText, totalText, minesText, sideButtonText, error;
	private NumberFormat formatter;
	private Account account;
	private TransactionList list;

	// Constructor for mines gui
	public Mines(Account account) {
		// Call super
		super("Mines");

		// initialize account
		this.account = account;

		// initialize list
		list = new TransactionList(10);
		try {
			list.readFromFile(account.getUsername());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.account.login();

		// Initialize width and height of frame
		width = 800;
		height = 550;

		// set layout of frame
		setLayout(null);

		// initialize currency formatting
		formatter = NumberFormat.getCurrencyInstance();

		// set gem and bomb image
		gem = new ImageIcon("Gem.png");
		bomb = new ImageIcon("Bomb.png");

		// Initialize gem count
		gemCount = 0;

		// Initialize buttons
		tiles = new JButton[6][6];
		for (int i = 0; i < tiles.length; i++) { // loop to go through columns
			for (int j = 0; j < tiles[i].length; j++) { // loop to go through rows
				// Change button appearance
				tiles[i][j] = new JButton();
				tiles[i][j].setIcon(new ImageIcon("Tile.png"));
				tiles[i][j].setRolloverIcon(new ImageIcon("TileHover.png"));
				tiles[i][j].setPressedIcon(new ImageIcon("TileHover.png"));
				tiles[i][j].setDisabledIcon(new ImageIcon("TileHover.png"));
				tiles[i][j].setOpaque(false);
				tiles[i][j].setContentAreaFilled(false);
				tiles[i][j].setBorderPainted(false);
				tiles[i][j].addActionListener(this);
			}
		}
		sideButton = new JButton("Bet");
		sideButton.setIcon(new ImageIcon("SideButton.png"));
		sideButton.setRolloverIcon(new ImageIcon("SideButtonHover.png"));
		sideButton.setDisabledIcon(new ImageIcon("SideButtonHover.png"));
		sideButton.setOpaque(false);
		sideButton.setContentAreaFilled(false);
		sideButton.setBorderPainted(false);
		sideButton.addActionListener(this);

		// Initialize objects
		objects = new ImagePicture[6][6];
		for (int i = 0; i < objects.length; i++) { // loop to go through columns
			for (int j = 0; j < objects[i].length; j++) { // loop to go through rows
				// set image
				objects[i][j] = new ImagePicture(gem);
			}
		}

		// Initialize JTextField
		betAmount = new JTextField();
		betAmount.setBackground(Color.LIGHT_GRAY);
		betAmount.setFont(new Font("Comic Sans", Font.PLAIN, 20));

		// Initialize JTextArea
		balanceAmount = new JTextArea(formatter.format(Math.floor(account.getBalance() * 100) / 100));
		balanceAmount.setBackground(Color.LIGHT_GRAY);
		balanceAmount.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		balanceAmount.setEditable(false);
		totalAmount = new JTextArea("$0.00");
		totalAmount.setBackground(Color.LIGHT_GRAY);
		totalAmount.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		totalAmount.setEditable(false);


		// Initialize JComboBox
		String[] options = {"6", "9", "12"};
		minesAmount = new JComboBox<String>(options);
		minesAmount.setBackground(Color.LIGHT_GRAY);

		// Initialize text
		balanceText = new TextPicture("Balance", new Font("Comic Sans", Font.BOLD, 20), 20, 50);
		balanceText.setColor(Color.pink);
		betText = new TextPicture("Bet Amount ($)", new Font("Comic Sans", Font.BOLD, 20), 20, 230);
		betText.setColor(Color.pink);
		minesText = new TextPicture("Mines", new Font("Comic Sans", Font.BOLD, 20), 20, 140);
		minesText.setColor(Color.pink);
		totalText = new TextPicture("Total", new Font("Comic Sans", Font.BOLD, 20), 20, 410);
		totalText.setColor(Color.pink);
		sideButtonText = new TextPicture("Bet", new Font("Comic Sans", Font.BOLD, 20), 85, 340);
		sideButtonText.setColor(Color.BLACK);
		error = new TextPicture("", new Font("Comic Sans", Font.BOLD, 10), 20, 280);

		// Set button and objects bounds
		int rowPos = 50;
		int columnPos = 283;
		for (int i = 0; i < tiles.length; i++) { // loop to go through columns
			for (int j = 0; j < tiles[i].length; j++) { // loop to go through rows
				// set bounds
				objects[i][j].setBounds(columnPos, rowPos, 64, 64);
				tiles[i][j].setBounds(columnPos, rowPos, 64, 64);

				// add onto frame
				add(tiles[i][j]);
				add(objects[i][j]);

				// start with buttons disabled
				tiles[i][j].setEnabled(false);

				rowPos += 74; // increment column position
			}
			rowPos = 50; // reset row position once last row reached
			columnPos += 74; // increment column position
		}

		// create and initialize explosion gif
		explosion = new ImagePicture(new ImageIcon("Explosion.gif"));

		// Create sidepanel
		balanceText.setBounds(0, 0, width, height);
		add(balanceText);
		balanceAmount.setBounds(20, 60, 150, 30);
		add(balanceAmount);
		betText.setBounds(0, 0, width, height);
		add(betText);
		betAmount.setBounds(20, 240, 150, 30);
		add(betAmount);
		error.setBounds(0, 0, width, height);
		minesText.setBounds(0, 0, width, height);
		add(minesText);
		minesAmount.setBounds(20, 150, 60, 30);
		add(minesAmount);
		sideButtonText.setBounds(0, 0, width, height);
		add(sideButtonText);
		sideButton.setBounds(20, 305, 170, 60);
		add(sideButton);
		totalText.setBounds(0, 0, width, height);
		add(totalText);
		totalAmount.setBounds(20, 420, 150, 30);
		add(totalAmount);


		// set size and location of frame
		setSize(width, height);  
		getContentPane().setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		/*
		 * custom close operation to reopen main menu after 'X' is pressed
		 * Source: https://stackoverflow.com/questions/6084039/create-custom-operation-for-setdefaultcloseoperation
		 */
		WindowListener exitListener = new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	// redisplay main menu when closing and dispose of this window
				new MainMenu(account);
				dispose();
		    }
		};
		
		addWindowListener(exitListener);
	}

	// Method to reset game
	public void reset() {
		// Reset gemCount
		gemCount = 0;
		// remove game-over effects
		remove(explosion);
		// re-add tiles
		for (int i = 0; i < tiles.length; i++) { // loop to go through columns
			for (int j = 0; j < tiles[i].length; j++) { // loop to go through rows
				// add onto frame
				add(tiles[i][j]);
				// reset bombs to diamonds
				objects[i][j].setImage(gem);
				add(objects[i][j]); // put objects behind buttons
			}
		}
		// randomize bomb locations
		randomize(numBombs);
		repaint();
	}

	// Method to randomize bomb locations
	public void randomize(double numOfBombs) {
		// for loop to create a bomb until numOfBombs is met
		for (int i = 0; i < numOfBombs; i++) {
			// replace bomb if there is already a bomb at the location
			do {
				// randomize row and column
				randRow = (int) (Math.random() * tiles.length);
				randColumn = (int) (Math.random() * tiles.length);
			} while(objects[randRow][randColumn].getImage().equals(bomb));

			// set image of random object to bomb
			objects[randRow][randColumn].setImage(bomb);
		}
	}

	// Game over method
	public void gameOver(int x, int y) {
		explosion.setBounds(x, y, 64, 64);
		add(explosion); // display explosion


		// reset gemCount, change button to bet, and re-enable side panel features
		sideButtonText.setTitle("Bet");
		sideButtonText.setxPos(85);
		gemCount = 0;
		total = 0;
		sideButton.setEnabled(true);
		betAmount.setEnabled(true);
		minesAmount.setEnabled(true);

		// remove all tiles
		for (int i = 0; i < tiles.length; i++) { // loop to go through columns
			for (int j = 0; j < tiles[i].length; j++) { // loop to go through rows
				remove(tiles[i][j]);
				// remove and re-add objects to display game-over effects on top
				remove(objects[i][j]);
				add(objects[i][j]);
			}
		}
		repaint();
	}

	// Method to calculate profit
	private void updateProfit() {
		if (gemCount != 0) {
			total = bet * (1 + (numBombs / 100) + (gemCount * (0.25 + numBombs / 100)));
			totalAmount.setText(formatter.format(total));
		}
		else {
			total = 0;
			totalAmount.setText("$0.00");
		}
	}

	// method to check if input is valid
	private boolean tryParse() {
		try {
			Double.parseDouble(betAmount.getText());
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	// Method to run code based on what's been pressed
	public void actionPerformed(ActionEvent e) {
		// check if any of the tiles were pressed
		for (int i = 0; i < tiles.length; i++) { // loop to go through columns
			for (int j = 0; j < tiles[i].length; j++) { // loop to go through rows
				if (e.getSource() == tiles[i][j]) {
					remove(tiles[i][j]);
					repaint();
					// end game if bomb under tile
					if (objects[i][j].getImage().equals(bomb)) {
						gameOver(74 * i + 283, 74 * j + 50); // call game over method
					}
					else {
						// Increment gem count
						gemCount++;
					}
				}
			}
		}

		// update profit
		updateProfit();

		// Enable/Disable side button based on number of gems
		if (gemCount == 0 && sideButton.getText().equals("Cashout")) {
			sideButton.setEnabled(false);
		}
		else {
			sideButton.setEnabled(true);
		}

		// If side button pressed
		if (e.getSource() == sideButton) { // if bet pressed
			if(sideButtonText.getTitle().equals("Bet")) {
				// check if number in textbox is valid
				if (betAmount.getText().equals("")) { // if nothing entered
					error.setTitle("Error! Please enter an amount!");
					add(error);
					repaint();
				}
				else if (tryParse() || Double.parseDouble(betAmount.getText()) <= 0) { // if invalid number
					error.setTitle("Error! Please enter a valid number!");
					add(error);
					repaint();
				}
				else if (Double.parseDouble(betAmount.getText()) > Math.floor(account.getBalance() * 100) / 100) { // if not enough money
					error.setTitle("Error! Insufficient Funds!");
					add(error);
					repaint();
				}
				else {
					// Remove any lingering error messages
					remove(error);

					// set bet amount
					bet = Double.parseDouble(betAmount.getText());

					
					// add transaction and update list
					list.insertTransaction(new Transaction('W', account.getBalance(), bet));
					account.withdraw(Math.round(bet * 100.0) / 100.0); // withdraw bet amount from balance
					account.saveToFile();
			        try {
						list.saveToFile(account.getUsername());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// update balance on screen
					balanceAmount.setText(formatter.format(Math.floor(account.getBalance() * 100) / 100));

					// set numBombs to selected item in combo box
					numBombs = Integer.parseInt(minesAmount.getSelectedItem().toString());
					reset();
					for (int i = 0; i < tiles.length; i++) { // loop to go through columns
						for (int j = 0; j < tiles[i].length; j++) { // loop to go through rows
							tiles[i][j].setEnabled(true);
						}
					}

					// Change button text
					sideButtonText.setTitle("Cashout");
					sideButtonText.setxPos(65);
					sideButton.setEnabled(false);

					// Disable all side panel features
					betAmount.setEnabled(false);
					minesAmount.setEnabled(false);
				}
			}
			else { // if cashout pressed
				// Change button text
				sideButtonText.setTitle("Bet");
				sideButtonText.setxPos(85);

				// update total
				updateProfit();
				gemCount = 0;

				// add transaction and update list
				list.insertTransaction(new Transaction('D', account.getBalance(), total));
				account.deposit(Math.round(total * 100.0) / 100.0); // update balance
				balanceAmount.setText(formatter.format(Math.floor(account.getBalance() * 100) / 100));
				account.saveToFile();
		        try {
					list.saveToFile(account.getUsername());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
				// reveal objects by removing all tiles
				for (int i = 0; i < tiles.length; i++) { // loop to go through columns
					for (int j = 0; j < tiles[i].length; j++) { // loop to go through rows
						remove(tiles[i][j]);
					}
				}

				// enable side panel features
				betAmount.setEnabled(true);
				minesAmount.setEnabled(true);
			}
			repaint();
		}
	}

	// Self-Testing main method to test all methods through GUI
	public static void main(String[] args) {
		// Create test account
		Account account = new Account("Apple", "Banana@1", "Test", new PhoneNumber("+1-212-5551234"), 1000.00);
		// Create GUI
		Mines GUI = new Mines(account);
	}
}