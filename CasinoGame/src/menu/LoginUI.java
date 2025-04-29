package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import menuComponents.Account;
import menuComponents.SoundEffect;
import startHere.InitialUI;

/**
 * Name: Cherith Boya
 * Date: 2024-01-18
 * Description: In this class, this is where the user will login to their account. The user
 *              will then have to input two pieces of information; their username and their password
 *              Once they are done, they can press the login button to enter the casino or they can 
 *              press back if they want to create an account or ask for help. If the user was to login, 
 *              they have to remember their username and password. If they forget, they have to create a
 *              account again.
 *              
 *              
 */
public class LoginUI extends JFrame implements ActionListener {
    /*
     * Declare private Variables and define the UI components
     */
    private Font headerFont, labelFont, buttonFont;
    private JLabel headerLabel, backgroundLabel, usernameLabel, passwordLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton backButton, loginButton;

    // Constructor for the LoginUI class
    public LoginUI() {
        // Create the frame
        super("Welcome!");
        // Set the default close operation for the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sets the location of the frame
        this.setLocationRelativeTo(null);
        // No automatic layout and only manual
        this.setLayout(null); // Set layout to null
        // Set an icon image to the JFrame
        this.setIconImage(new ImageIcon("LuckyQueenNoText.png").getImage()); // Set image icon
        // Set the size of the frame
        this.setSize(488, 374);
        // Make frame unresizable
        this.setResizable(false);

        // Initialize Fonts with the same font, thickness(beside headerFont), but everything has different sizes
        headerFont = new Font("Rockwell", Font.BOLD, 33);
        labelFont = new Font("Rockwell", Font.PLAIN, 18);
        buttonFont = new Font("Rockwell", Font.PLAIN, 12);

        // Create a JLabel containing "Welcome Back!" as text
        headerLabel = new JLabel("Welcome Back!");
        // Align the label to the center
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Set the text of a font style called (headerFont)
        headerLabel.setFont(headerFont);
        // Set the color of the text to white
        headerLabel.setForeground(Color.WHITE);
        // Position where the text should be and adjust the size
        headerLabel.setBounds(106, 61, 276, 40);
        // Add the headerLabel to the class
        this.add(headerLabel);

        // Create a JLabel containing "Username: " as text
        usernameLabel = new JLabel("Username:");
        // Align the label in a trailing manner
        usernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        // Set the text of a font style called (labelFont)
        usernameLabel.setFont(labelFont);
        // Set the color of the text to white
        usernameLabel.setForeground(Color.WHITE);
        // Position where the text should be and adjust the size
        usernameLabel.setBounds(101, 121, 110, 14);
        // Add the usernameLabel to the class
        this.add(usernameLabel);

        // Create a JLabel containing "Password" as text
        passwordLabel = new JLabel("Password:");
        // Align the label in a trailing way
        passwordLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        // Set the text of a font style called (labelFont)
        passwordLabel.setFont(labelFont);
        // Set the color of the text to white
        passwordLabel.setForeground(Color.WHITE);
        // Position where the text should be and adjust the size
        passwordLabel.setBounds(101, 147, 110, 14);
        // Add the passwordLabel to the JFrame
        this.add(passwordLabel);

        
        //Create a new JTextField "userField"
        userField = new JTextField();
        // Set the number of columns for the JTextField
        userField.setColumns(10);
        // Position where the field should be and adjust the size
        userField.setBounds(221, 121, 161, 16 + 6);
        // Add the userField JTextField to the JFrame
        this.add(userField);

        // Create a new JPasswordField "passwordField"
        passwordField = new JPasswordField();
        // Set the number of columns for the JPasswordField
        passwordField.setColumns(10);
        // Position where the text should be and adjust the size
        passwordField.setBounds(221, 147, 161, 16 + 6);
        // Add the passwordField to the JFrame
        this.add(passwordField);

        //Create a JButton  containing "Back" as text
        backButton = new JButton("Back");
        // Set the text of a font style called (buttonFont)
        backButton.setFont(buttonFont);
        //A way to handle and listen to button clicks
        backButton.addActionListener(this);
        //Position where the button should be located and adjust the size
        backButton.setBounds(122, 189, 119, 23);
        //Add the button to the JFrame
        this.add(backButton);

        // Create a JButton containing "Login" as text
        loginButton = new JButton("Login");
        // Set the font style for the "Login" button text
        loginButton.setFont(buttonFont);
        // A way to handle and listen to button clicks
        loginButton.addActionListener(this);
        //Position where the button should be located and adjust the size
        loginButton.setBounds(263, 189, 119 + 1, 23);
        //Add the button to the JFrame
        this.add(loginButton);

        // Add a background label with an ImageIcon created from an image file named "Background.png"
        backgroundLabel = new JLabel(new ImageIcon(new ImageIcon("Background.png").getImage().getScaledInstance(488, 374, Image.SCALE_DEFAULT)));
        // Set the bounds (position and size) of the background label
        backgroundLabel.setBounds(0, 0, 488, 374);
        // Add the background label to the JFrame
        this.add(backgroundLabel);
        // Make visible
        this.setVisible(true);
    }

    // Main method to run the application
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
        // Create an object of LoginUI
        new LoginUI();
    }

    // ActionPerformed method for handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // A sound effect when the buttons are clicked
        new SoundEffect("click.wav");
        
        //If this button was clicked, do the following
        if (e.getSource() == backButton) {
            // Create a InitialUI object and call it
            new InitialUI();
            // Dispose of the LoginUI
            this.dispose();
        }
        //If this button was clicked, do the following
        if (e.getSource() == loginButton) {
            // Create an Account object with the  username and password as inputs
            Account account = new Account(userField.getText(), passwordField.getText());
            // Check if the login is successful
            if (account.login()) {
                //Create MainMenu object, account passed in to it
                new MainMenu(account);
                // Dispose of the LoginUI
                this.dispose();
                
            } else {
                // Show a message dialog for incorrect username or password
                JOptionPane.showMessageDialog(null, "Incorrect username or password!");
            }
        }
    }
}