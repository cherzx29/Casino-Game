package mine;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Rajveer Karotanian
 *
 * Date: 10/16/2023
 *
 * Description:	A class that defines a component - Picture
 * 				inherits from JComponent
 */
public class Picture extends JComponent{

	/*
	 * Instance Data - Attributes
	 */

	private Color color;
	private int xPos, yPos, myWidth, myHeight;

	/*
	 * Default Constructor
	 */

	public Picture() {
		// Initialize color, xPos, yPos, myWidth, and myHeight
		this.color = Color.RED;
		this.xPos = 0;
		this.yPos = 0;
		this.myWidth = 50;
		this.myHeight = 25;
	}

	/*
	 * Overloaded Constructor
	 */

	public Picture(int x, int y, int w, int h) {
		this.color = Color.RED;
		this.xPos = x;
		this.yPos = y;
		this.myWidth = w;
		this.myHeight = h;

	}


	public void setColor(Color color) {
		this.color = color;
	}

	/*
	 * Set color based on r, g, b
	 */
	public void setColor(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}

	public Color getColor() {
		return color;
	}
	
	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}


	public void setyPos(int yPos) {
		this.yPos = yPos;
	}


	public int getMyWidth() {
		return myWidth;
	}

	public int getMyHeight() {
		return myHeight;
	}

	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
	}


	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
	}



	/*
	 * our own paint method - Overrides JComponent's paint
	 */
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.drawRect(this.xPos, this.yPos, this.myWidth, this.myHeight);

	}

	/**
	 * Self-testing main method
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// create a frame and a picture object
		JFrame f = new JFrame("Testing");

		Picture p = new Picture();

		f.setSize(400, 350);

		f.add(p); // add my own object to the frame

		f.setVisible(true); // paint the JFrame

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		// change color
		p.setColor(Color.BLUE);

		// repaint the frame
		f.repaint();

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		// test the setters for position and size
		p.setxPos(10);
		p.setyPos(250);

		p.setMyWidth(25);
		p.setMyHeight(40);

		// repaint the frame
		p.repaint();

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		// create another object
		Picture p1 = new Picture(200, 50, 100, 50);

		f.add (p1);

		f.setVisible(true);

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		p.setColor(255, 200, 0);

		// repaint picture
		f.repaint();

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		// change dimensions of p1
		p1.setMyWidth(p1.getMyWidth() / 2);
		p1.setMyHeight(p1.getMyHeight() * 2);

		// repaint picture
		p1.repaint();

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		// make object p  move from left to right
		for (int i = 0; i < 5; i++) {
			p1.setxPos(p1.getxPos() + 10);
			Thread.sleep(50);
			p1.repaint();
		}
	}
}