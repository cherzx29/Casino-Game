package skyfall;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
/**
 * Name: Sanat Kanwal
 * Date: 2024 - 01 - 19
 * Description: This class is responsible for drawing the game panel. It's responsible for drawing the player, the coins
 * 				, the scoreManager, and sets background image. By making sure this game panel shows up, the paint component 
 * 				is called(with an object being passed in it)
 *
 * Method List:
 * 		public GamePanel(Player player, Coin[] balls, ScoreManager scoreManager, int width, int height) - create a game panel by getting in information within the parameters
 * 		protected void paintComponent(Graphics g) - renders and paints the game elements of the panel
 */
public class GamePanel extends JPanel {
	/*
	 * Declare private Variables and define the UI components
	 */
	private Player player;
	private Coin[] balls;
	private ScoreManager scoreManager;
	private Image backgroundImage;

	/**
	 * Overloaded constructor
	 * - create a game panel by getting in information within the parameters
	 */
	public GamePanel(Player player, Coin[] balls, ScoreManager scoreManager, int width, int height) {
		//Initialize the information from the parameters to its respective variables
		this.player = player;
		this.balls = balls;
		this.scoreManager = scoreManager;

		// Load the background image
		backgroundImage = new ImageIcon("SkyfallBackground.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
	
	/**
	 * paintComponent method
	 * - renders and paints the game elements of the panel
	 */
	protected void paintComponent(Graphics g) {
		//Call the paintComponent method passing the 'g' object
		super.paintComponent(g);
		//Set the font size of the object
		g.setFont(g.getFont().deriveFont(g.getFont().getSize() * 1.4F)); 
		
		// Draw the background image
        g.drawImage(backgroundImage, 0, 0, this);

		// Draw player using their image
		g.drawImage(player.getImage(), player.getRectangle().x, player.getRectangle().y, this);

		// Draw each ball using their image
		for (Coin ball : balls) {
	        if (ball.isActive()) {
	            g.drawImage(ball.getImage(), ball.getRectangle().x, ball.getRectangle().y, this);
	        }
	    }
		
		//Set the color to white
		g.setColor(Color.WHITE);
		// Display the score and lives
		g.drawString("Score: " + scoreManager.getScore(), 10, 20);
		g.drawString("Lives: " + scoreManager.getLives(), 10, 40);
	}
	
	// Self testing main method
    public static void main(String[] args) {
        // create new JFrame
        JFrame j = new JFrame();


        // Create and initialize coin array
        Coin[] balls = new Coin[6];
        Random random = new Random();
        // Initialize an array of Coin objects with random positions
        for (int i = 0; i < 6; i++) {
            balls[i] = new Coin(800, random);
            //Make it inactive
            balls[i].setActive(true);
        }

        // Create gamepanel
        GamePanel p = new GamePanel(new Player(800, 600), balls, new ScoreManager(), 800, 600);

        // add panel to JFrame
        j.add(p);

        // initialize JFrame
        j.setSize(800, 600);
        j.setVisible(true);
    }
}
