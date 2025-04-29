package skyfall;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Name: Sanat Kanwal
 * Date: 2024  - 01 - 19
 * Description: This class represents the coins object within the game and the functionalities it has in order to
 * 				move the coin. Stuff like where the coins should drop, how fast should it move, where should it stop moving
 * 				, are all took into consideration. 
 * 
 * Method List:
 * 		public Coin(int screenWidth, Random random) - Takes in the screens width and a random
 * 		public void move() - A method to move the position of the coin
 * 		public void resetPosition(Random random) - A method to that resets the position of the ball
 * 		public boolean intersects(Rectangle other) - Checks if the ball intersects with the rectangle
 * 		public boolean isOutOfScreen(int screenHeight) - Checks if the y-position of the ball is going out the screen
 * 		public Rectangle getRectangle() - get rectangle
 * 		public Image getImage() - get the image
 * 		public boolean isActive() - to check if the coin is active
 * 		public void setActive(boolean active) - set the coin's active state
 *
 */
public class Coin {
	//Declare all private variables
    private static final int BALL_DIAMETER = 80;
    private Rectangle ball;
    private int velocity;
    private int screenWidth;
    private Image image;
    private int moveCounter; // Counter to track the number of moves
    private boolean isActive;
    

    /**
     * Overloaded constructor
     *  - Takes in the screens width and a random
     */
    public Coin(int screenWidth, Random random) {
    	//Initialize screenWidth to screenWidth
        this.screenWidth = screenWidth;
        // Start with a velocity of 1
        this.velocity = 1; 
        // Initialize move counter
        this.moveCounter = 0; 
        // Initially, the coin is not active
        this.isActive = false; // Initially, the coin is not active

        // Replace with your image path
        ImageIcon icon = new ImageIcon("coin.png");
        //Scale the image based on it's diameter(widht, height) and it should be smooth
        image = icon.getImage().getScaledInstance(BALL_DIAMETER, BALL_DIAMETER, Image.SCALE_SMOOTH);

        //Generate a random integer that should be within the screens width and the balls diameter
        int x = random.nextInt(screenWidth - BALL_DIAMETER);
        //Generate a random y-coordinate, that restricts the coin from the top 50 pixels 
        int y = random.nextInt(50);
        //Create a rectangle object, putting in certain dimensions
        ball = new Rectangle(x, y, BALL_DIAMETER, BALL_DIAMETER);
        
    }

    /**
     * move method
     * - A method to move the position of the coin
     */
    public void move() {
    	//The velocity is added to the y position of the ball, moving it
        ball.y += velocity;
        // Increment the move counter
        moveCounter++; 

        // Increase velocity after every 50 moves
        if (moveCounter % 50 == 0) { 
        	//Increment velocity
            velocity++;
        }
    }

    /**
     * resetPosition
     * - A method to that resets the position of the ball
     */
    public void resetPosition(Random random) {
    	//Generate a random integer that should be within the screens width and the balls diameter
        ball.x = random.nextInt(screenWidth - BALL_DIAMETER - 25);
        //Reset y ball position to 0
        ball.y = 0;
        // Reset velocity to 1
        velocity = 1; 
        // Reset the move counter
        moveCounter = 0; 
    }

    /**
     * intersects method
     * - Checks if the ball intersects with the rectangle
     */
    public boolean intersects(Rectangle other) {
        // Check for intersection with another rectangle (e.g., the player)
        return ball.intersects(other);
    }

    /**
     * isOutOfScreen
     * - Checks if the y-position of the ball is going out the screen
     */
    public boolean isOutOfScreen(int screenHeight) {
        // Check if the ball is out of the screen (below the bottom)
        return ball.y > screenHeight;
    }

    /**
     * getRectangle method
     * - get rectangle
     */
    public Rectangle getRectangle() {
    	//return ball
        return ball;
    }
    
    /**
     * getImage method
     * - get the image
     */
    public Image getImage() {
        return image;
    }
    
 /**
  * isActive method
  * - to check if the coin is active
  */
    public boolean isActive() {
    	//return is active
        return isActive;
    }

    /**
     * setActive method
     * - set the coin's active state
     */
    public void setActive(boolean active) {
    	//set active to equal to isActive
        isActive = active;
    }
}
