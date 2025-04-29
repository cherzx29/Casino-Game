package skyfall;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import menu.MainMenu;
import menuComponents.Account;
import menuComponents.Transaction;
import menuComponents.TransactionList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: The following program represents a simple game called Skyfall. The game
 * 				is essentially about where the user has to collect a bunch of coins falling from the sky in order to get
 * 				their money. The way they can collect it is by moving their character, left to right. They also only have 3 lives
 * 				and each time they drop a coin, they lose a life. As their getting coins, their will be a bar on the side
 * 				show casing how much more percentage there is for them to get to their desired money. If the user percentage bar
 * 				is full, they win the game, and the money will then get transferred to their account. If the user loses and 
 * 				lost all his lives, he will end up with $0, in which he gets no money at all and a loss in money from his 
 * 				account. This program consists/handles all of the collisions that would occur, keeping track of scores,
 * 				managing what would happened if the user won or lost, and etc. This is also a refactored
 * 				class which follows the coding principles of the SignUpUI example.
 *
 * Method List: 
 *		public SkyfallUI(Account account, int desiredScore, double initialBet) - Set up the game interface based on the information provided 
 *		private void setupWindow() - Sets up the game window properties and components.
 *		private void initializeGameComponents() - Sets up the game components
 *		private void startGameLoop() - Initiates a time, delaying at 10 milliseconds
 *		private double calculateWinnings() - Default method(calculateWinnings)
 *		public static double calculateWinnings(int desiredScore, double initialBet) - calculate the winnings of the user but by getting in the desiredScore and initialBet values
 *		private void handleGameWin() - displays stuff on what would happen if user won
 *		private void handleGameOver() - displays stuff on what would happen if user lost
 *
 */
public class SkyfallUI extends JFrame implements KeyListener, ActionListener {

	/**
	 * Declare and initialize all private variables
	 */
	private static final int WIDTH = 800, HEIGHT = 600;
	private static final int BALLS_COUNT = 6;
	private GamePanel gamePanel;
	private Player player;
	private Coin[] balls;
	private ScoreManager scoreManager;
	private Timer timer;
	private Random random = new Random();
	private JProgressBar scoreProgressBar;
	private int desiredScore;
	private double initialBet;
	private Account account;
	private TransactionList list;


	/**
	 * Overloaded Constructor
	 * - Set up the game interface based on the information provided 
	 * 
	 */
	public SkyfallUI(Account account, int desiredScore, double initialBet) {
		// Initialize instance variables with provided information from the parameters
		this.desiredScore = desiredScore;
		this.account = account;
		this.initialBet = initialBet;
		// Create a TransactionList which it only has a size of 10
		list = new TransactionList(10);

		try {
			// Read the transaction history from file belonging to the username account
			list.readFromFile(account.getUsername());
		} catch (IOException e) {
			// Print stack trace if errors occur
			e.printStackTrace();
		}
		// Log in the user account
		this.account.login();
		// Call method, to set up the window
		setupWindow();
		// Call method, to initialize game components
		initializeGameComponents();
		// Call method, to start the game loop
		startGameLoop();
		// Make the UI visible
		setVisible(true);
	}

	// number formatting
    public NumberFormat formatter = NumberFormat.getCurrencyInstance();

	/**
	 * setupWindow method
	 * - Sets up the game window properties and components.
	 */
	private void setupWindow() {
		//Set title of window to "Falling Balls Game"
		setTitle("Falling Balls Game");
		//Set the size of the window
		setSize(WIDTH, HEIGHT);
		//Set it when the application will close, if you click on exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add a key listener to this JFrame
		addKeyListener(this);
		// Allow this JFrame to receive focus for keyboard events
		setFocusable(true);
		// User can't resize the JFrame
		setResizable(false);
		// Disable focus traversal keys
		setFocusTraversalKeysEnabled(false);
		// Center the JFrame on the screen
		setLocationRelativeTo(null);

		// Create a vertical progress bar for the game score
		scoreProgressBar = new JProgressBar(0, desiredScore);
		scoreProgressBar.setValue(0);
		scoreProgressBar.setStringPainted(true);
		scoreProgressBar.setOrientation(JProgressBar.VERTICAL);

		// Set the preferred thickness(width) of the progress bar
		int progressBarWidth = 50;
		// Adjust this value to your preference
		Dimension preferredSize = new Dimension(progressBarWidth, HEIGHT);
		//Set preferred size of the bar
		scoreProgressBar.setPreferredSize(preferredSize);

		// Add the progress bar to the left of the frame
		add(scoreProgressBar, BorderLayout.WEST);
	}

	/**
	 * initializeGameComponents()
	 * - Sets up the game components
	 */
	private void initializeGameComponents() {
		player = new Player(WIDTH, HEIGHT);
		balls = new Coin[BALLS_COUNT];
		// Initialize an array of Coin objects with random positions
		for (int i = 0; i < BALLS_COUNT; i++) {
			balls[i] = new Coin(WIDTH, random);
			//Make it inactive
			balls[i].setActive(false);
		}
		// Initialize a ScoreManager to track the game score
		scoreManager = new ScoreManager();
		// Create a GamePanel object based on the information
		gamePanel = new GamePanel(player, balls, scoreManager, WIDTH, HEIGHT);
		// Add gamePanel to frame
		add(gamePanel);
	}

	/**
	 * startGameLoop method
	 * - Initiates a time, delaying at 10 milliseconds
	 */
	private void startGameLoop() {
		timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Activate coins with a random chance and reset their positions
		for (int i = 0; i < BALLS_COUNT; i++) {
			if (!balls[i].isActive() && random.nextInt(200) < 2) {
				balls[i].setActive(true);
				balls[i].resetPosition(random);
			}
		}
		// Move active coins, update scores, and check for collisions or out-of-screen events
		for (int i = 0; i < BALLS_COUNT; i++) {
			if (balls[i].isActive()) {
				balls[i].move();
				// Check if ball and player intersects
				if (balls[i].intersects(player.getRectangle())) {
					scoreManager.increaseScore();
					// Deactivate and respawn later
					balls[i].setActive(false); 
				} else if (balls[i].isOutOfScreen(HEIGHT)) {
					// Decrease life when a coin goes off-screen
					scoreManager.decreaseLife();
					// Deactivate balls
					balls[i].setActive(false); 
					// Handle game over if lives are done
					if (scoreManager.isGameOver()) {
						handleGameOver();
						return; // Exit method
					}
				}
			}
		}
		// Update the score progress bar
		scoreProgressBar.setValue(scoreManager.getScore());
		// Check if the score is greater than or equal to desired score
		if (scoreManager.getScore() >= desiredScore) {
			//Call handleGameWin method
			handleGameWin();
		}
		//repaint the game panel
		gamePanel.repaint();
	}

	/**
	 * Default method(calculateWinnings)
	 */
	private double calculateWinnings() {
		// Base multiplier
		double winningMultiplier = 1.5; 
		// Assuming 100 is a standard score
		double difficultyFactor = (double) desiredScore / 100;

		// return winnings, whcih is the initial bet multiplying it by the difficulty and by the winning multiplier
		return initialBet * winningMultiplier * difficultyFactor;
	}
	/**
	 * calculateWinnings
	 * - calculate the winnings of the user but by getting in the desiredScore and initialBet values
	 */

	public static double calculateWinnings(int desiredScore, double initialBet) {
		// Base multiplier
		double winningMultiplier = 1.5; 
		// Assuming 100 is a standard score
		double difficultyFactor = (double) desiredScore / 100;

		// return winnings, whcih is the initial bet multiplying it by the difficulty and by the winning multiplier
		return initialBet * winningMultiplier * difficultyFactor;
	}

	/**
	 * handleGameWin method
	 * - displays stuff on what would happen if user won
	 */
	private void handleGameWin() {
		//repaint the gamePanel
	    gamePanel.repaint();
	    //stop the timer
	    timer.stop();
	    
	    // Record the winning transaction and deposit winnings into the account
	    list.insertTransaction(new Transaction('D', account.getBalance(), calculateWinnings()));
	    //Deposit the winnings to his account
	    account.deposit(calculateWinnings());
	    
	    // Display a message stating the amount he heard
	    JOptionPane.showMessageDialog(this, "Congratulations! You've earned " + formatter.format(calculateWinnings()) + 
	            "\nTransaction has been added to your account!");
	    
	    // Save the account to the files
	    account.saveToFile();
	    try {
	    	//save transaction list to file based on the accounts username
	        list.saveToFile(account.getUsername());
	    } catch (IOException e) {
	    	//print stack trace if any error occurs
	        e.printStackTrace();
	    }
	    
	    // Open the main menu by passing in an account object
	    new MainMenu(account);
	    //dispose the window
	    dispose();
	}

	/**
	 * handleGameOver
	 * - displays stuff on what would happen if user lost
	 */
	private void handleGameOver() {
		//repaint the game panel
		gamePanel.repaint();
		//stop the time
		timer.stop();
		//Record the loss transaction and Withdraw amount from the user account balance
		list.insertTransaction(new Transaction('W', account.getBalance(), this.initialBet));
		//Withdraw the money from his account
		account.withdraw(this.initialBet);
		//Display a message saying there score and the money they've lost
		JOptionPane.showMessageDialog(this, "Game Over! Your score: " + scoreManager.getScore() + "\nYou lost " + formatter.format(this.initialBet) 
				+ "\nTransaction has been added to your account!");
		//Save the transaction of the account to the file
		account.saveToFile();
		
		try {
			//Save the account transaction to the file based on the persons username
			list.saveToFile(account.getUsername());
		} catch (IOException e) {
			// print a stack trace in case if any error occurs
			e.printStackTrace();
		}
		// Open the main menu by passing in an account object
	    new MainMenu(account);
	    //dispose the window
	    dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used, but required by KeyListener interface
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.handleKeyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not used, but required by KeyListener interface
	}

	public static void main(String[] args) {
		// try to set the look and feel to Nimbus, 
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform look and feel
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // If setting the cross-platform look and feel fails, ignore and continue with default
		    }
		}
		//Call the SkyfallUI sending in a account object
		new SkyfallUI(new Account("Apple", "Banana@1"), 100, 100);
	}
}
