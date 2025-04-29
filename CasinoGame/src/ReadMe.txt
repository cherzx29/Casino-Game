Main Class: InitialUI (src -> startHere -> InitialUI.java)

	This class must be run inorder to use the program. Running this class will greet you with a welcome screen, giving three options to choose from:

		1. Create Account: Enter account creation (launches SignUpUI.java), where you are able to create an account if you enter the following information:

			- Name: Your name must consist of two words (first and last name) with no numbers or symbols (e.g Lebron James).
			- Phone Number: Your phone number must be entered in this format: +CountryCode-AreaCode-Number (e.g. +1-212-5551234).
			- Initial Balance: You must enter a valid double, which must be >= 0, to set up your starting balance (e.g. 1000 or 1000.50).
			- Username: Your username must be greater than 3 characters, can't contain special characters, and must be unique (e.g. L3br0nJ4m3s).
			- Password: Your password must be longer than 6 characters and contain atleast 1 lowercase character, uppercase character, and special character (e.g. Lebron1@).
			- Repeat Password: You must enter the same password entered in the last field.

			Once these fields are filled, you may press 'Create Account' to be greeted with the menu. If you wish to return to the initial UI, you may press 'Back' at any time.

		2. Sign In: Sign into an existing account (launches LoginUI.java), where you are able to sign into your account by entering the following information:

			- Username: Enter your username (e.g. L3br0nJ4m3s).
			- Password: Enter the account's password (e.g. Lebron1@).

			Once these fields are filled, you may press 'Login' to be greeted with the menu. If you wish to return to the initial UI, you may press 'Back' at any time.

		3. Help: Displays a user manual, telling you how to navigate through the program, use its features, and play the games.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Menu: MainMenu (src -> menu -> MainMenu.java)

	This class is run by the program once you create an account or sign in. Your name will be displayed in a welcome message on the top. In the middle,
	you will see two game options:
	
	1. SKYFALL: Displays a message giving a brief overview of the game, launching the game after you finish reading it (launches SkyFallBetUI.java).
	2. BOMBS TO RICHES: Displays a message giving a brief overview of the game, launching the game after you finish reading it (launches Mines.java).

	In the bottom, you will see three buttons, doing the following:
	
	1. Exit: Closes out of the program.
	2. Make Transaction: You may view your transaction history (e.g. bets and cashouts), being able to withdraw or deposit money into your account aswell.
	3. Settings: Enters the account's settings, where you can view your details (except for password), and change/update details (except for Username and Balance) by entering the account's password.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Game 1: Skyfall (src -> menu -> SkyfallUI.java)

	Pressing the "SKYFALL" button on the main menu will launch the bet window for this class (SkyFallBetUI.java), where the game will launch once a bet is placed.
	On the bet window, you will see the following:
	
		- Goal Score: Enter the an integer, representing what score you are hoping to achieve, which must be greater than 100 (e.g 150).
		- Initial Bet: Enter a double, representing the amount of money you will bet, which must be a valid number, and must be equal to or less than your balance (e.g. 100 or 100.50).
		- Win Sum: Displays your calculated win sum based on goal score and initial bet entered.
		- Exit: Returns back to the main menu.
		- Place Bet: Deducts bet from account and puts you into the game.

	Once the bet is placed, the game will start (launches SkyFallUI.java). In This game, the goal is to catch as many falling coins as you can in your basket, which can be controlled
	using the left and right arrows keys. Each coin caught will add onto your score by 10, where you can see your progress to reaching your goal score on the bar on the left. However,
	you are given 3 lives, where every time a coin touches the ground, a life is deducted, where you will lose the game once you reach 0 lives. If you end up winning, the win sum will 
	be added onto your balance.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Game 2: Bombs to Riches (src -> mines -> Mines.java) 

	This class is run by pressing the 'BOMBS TO RICHES' button on the main menu. On the left side of the window, you will see the following information:

		- Balance: Displays your current account balance.
		- Mines: Select between 3 mine options, being 6, 9, or 12.
		- Bet Amount ($): Enter a double, representing the amount of money you will bet on the current round (e.g. 50 or 50.25).
		- Bet: Confirm your bet amount and start the round.
		- Cashout: Cashout your current total, adding it onto your balance. This button appears once the round is started, and pressing it again will turn it back into the bet button.
		- Total: Displays total gain if you were to cashout.

	On the right side of the window, you will see a 6x6 grid of tiles. Once the round starts, you may click these tiles to reveal whats underneath, with the grid being filled with gems
	alongside the number of mines chosen. The objective is to reveal as many gems as possible while trying not to click on a bomb. The total gain is calculated by a formula using how many
	mines are on the field, and how many gems have been revealed. Every round, the grid will re-randomize. If a bomb is revealed, your total gain will be reset, but if you cashout early,
	the total gain will be added onto your balance. You may exit this game and return to the main menu at any given time by clicking the 'X' button on the top-right of the window. 

============================================================================================================================================================================================================================================

New Code Used:

	- WindowListener: This class was used to create a custom close operation, so exiting the program will display the main menu again instead of exiting the entire system.
			  This was used by removing the default close operation from the 'X' button and making it create a new MainMenu object with the account in use and
			  disposing of the current window.
			  Source: https://stackoverflow.com/questions/6084039/create-custom-operation-for-setdefaultcloseoperation

	- KeyListener:  This class was used to setup keyboard input for the Skyfall game inorder to move the basket. This was used to listen to keyboard input to move the basket
			in the directional arrow key pressed (left or right).
			Source: https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html

	- UIManager: This class was used to setup and make the GUIs look better. The primary use of this was to define the appearance of the GUI componenets (buttons, menus, etc.).
		     Source: https://docs.oracle.com/javase/8/docs/api/javax/swing/UIManager.html#:~:text=UIManager%20manages%20the%20current%20look,for%20obtaining%20various%20default%20values

	- AudioInputStream & Clip: These classes were used in combination to play a click sound everytime a button is pressed.
		     Sources: https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/AudioInputStream.html
			      https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/Clip.html

============================================================================================================================================================================================================================================

Known Errors/Bugs:

	*No known errors/bugs as of testing (1/19/24)*
