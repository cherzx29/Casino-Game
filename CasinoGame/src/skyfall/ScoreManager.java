package skyfall;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: Manages the score of the player during the game. The user will end up with 3 lives, start with a score of 0.
 * 				If they don't get coins, a live decrease. If they get to 0, game over. Score will also increase by 10
 * 				each time they collect coins. 
 * 
 * Method List:
 * 		public ScoreManager() - Default constructor
 *		public void increaseScore() - Increase score by 10
 *		public void decreaseLife() - Decrease life if player loses live
 *		public boolean isGameOver() - return live if less than or equal to 0
 *		public int getScore() - return score
 *		public int getLives() - return lives
 *		
 */
public class ScoreManager {
	/**
	 * Declare all private variables
	 */
    private int score;
    private int lives;

    /**
     * Default Constructor
     */
    public ScoreManager() {
        this.score = 0;
        this.lives = 3; // Initial number of lives can be adjusted
    }

    /**
     * increaseScore method
     * - Increase score by 10
     */
    public void increaseScore() {
        score += 10; // Score increment can be adjusted
    }

    /**
     * decreaseLife method
     * - Decrease life if player loses live
     */
    public void decreaseLife() {
        lives--;
    }

    /**
     * isGameOver method
     * - return live if less than or equal to 0
     */
    public boolean isGameOver() {
        return lives <= 0;
    }

    /**
     * getScore method
     * - return score
     */
    public int getScore() {
        return score;
    }
    /**
     * getLives method
     * - return lives
     */

    public int getLives() {
        return lives;
    }
    
 // Self-Testing main method
    public static void main(String[] args) {
        // Create new score manager
        ScoreManager test = new ScoreManager();
        System.out.println("Score: " + test.getScore());
        System.out.println("Lives: " + test.getLives());

        // Test increase score and decrease life
        test.increaseScore();
        test.decreaseLife();
        System.out.println("\nScore: " + test.getScore());
        System.out.println("Lives: " + test.getLives());

        // set lives to zero
        test.decreaseLife();
        test.decreaseLife();
        System.out.println("\nLives: " + test.getLives());

        // Test if game is over
        System.out.println("Is game over?: " + test.isGameOver());
    }
}
