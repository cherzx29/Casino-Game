package skyfall;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: In this class, the program is supposed to move the player. In order to do that, we take the screens width and
 * 				height, and the keys the user presses into consideration. The player is also represented as a basket, which
 * 				can be shown in the SkyFall game. 
 * 
 * Method List:
 *		public Player(int screenWidth, int screenHeight) - Takes the screen width and height into consideration for the rectangle object
 *		public void handleKeyPress(KeyEvent e) - Movements for when you click left or right key
 *		private void moveLeft() - Moves the object left
 *		private void moveRight() - Moves the object right
 *		public Rectangle getRectangle() - returns the rectangle object
 *		public int getScreenWidth() - returns the screen width
 *		public void setScreenWidth(int screenWidth) - set the screens width
 *		public int getScreenHeight() - Set the screenHeight to the screenHeight
 *		public Image getImage() - return image
 *		public void setImage(Image image) - set image to the image
 *		
 */
public class Player {

	/**
	 * Declare all private variables
	 */
	private static final int RECT_WIDTH = 130;
	private static final int RECT_HEIGHT = 130;
	private static final int MOVEMENT_SPEED = 25;

	private Rectangle rectangle;
	private int screenWidth;
	private int screenHeight;
	private Image image; 

	/**
	 * Overloaded constructor
	 * - Takes the screen width and height into consideration for the rectangle object
	 */
	public Player(int screenWidth, int screenHeight) {
		//Set screenWdith and screenHeight to its respective variables
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		// Initialize player's rectangle in the bottom middle of the screen
		int x = screenWidth / 2 - RECT_WIDTH / 2;
		int y = screenHeight - RECT_HEIGHT - 30;
		rectangle = new Rectangle(x, y, RECT_WIDTH, RECT_HEIGHT);

		// Load image from the specified path
		ImageIcon icon = new ImageIcon("basket.png");
		//Scale the image based on the information provided
		image = icon.getImage().getScaledInstance(RECT_WIDTH, RECT_HEIGHT, Image.SCALE_SMOOTH);
	}

	/**
	 * handleKeyPress method
	 * - Movements for when you click left or right key
	 */
	public void handleKeyPress(KeyEvent e) {

		// Get the key code from the KeyEvent
		int keyCode = e.getKeyCode();
		// Check if the key pressed is the left key
		if (keyCode == KeyEvent.VK_LEFT) {
			// If true, call the moveLeft() that move the object left
			moveLeft();
		} 
		// Check if the key pressed is the right key
		else if (keyCode == KeyEvent.VK_RIGHT) {
			// If true, call the moveRight() that moves the object right
			moveRight();
		}
	}

	/**
	 * moveLeft method
	 * - Move the object left
	 */
	private void moveLeft() {
		// Move the rectangle left, ensuring it doesn't go off-screen
		rectangle.x = Math.max(rectangle.x - MOVEMENT_SPEED, 0);
	}

	/**
	 * moveRight method
	 * - moves the object right
	 */
	private void moveRight() {
		// Move the rectangle right, ensuring it doesn't go off-screen
		rectangle.x = Math.min(rectangle.x + MOVEMENT_SPEED, screenWidth - RECT_WIDTH);
	}

	/**
	 * getRectangle method
	 * - return the rectangle object
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

	/**
	 * getScreenWidth
	 * - return the screens width
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * screenWidth method
	 * - set the screenWidth to screenWidth
	 */
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	/**
	 * getScreenHeight method
	 * - return the screens height
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * setScreenHeight method
	 * - Set the screenHeight to the screenHeight
	 */
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	/**
	 * getImage method
	 * - return the image 
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * setImage method
	 * - set the image to the image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	public static void main(String[] args) {
		// Create a Player instance for testing
		Player player = new Player(800, 600); // Assuming screen size is 800x600

		// Create a JFrame for testing
		JFrame frame = new JFrame("Player Self-Test");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the JFrame visible
		frame.setVisible(true);

		// Create a JPanel for rendering the player
		JPanel panel = new JPanel();

		// Add the JPanel to the JFrame
		frame.add(panel);
	}

}
