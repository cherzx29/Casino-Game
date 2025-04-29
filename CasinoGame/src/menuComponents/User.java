/**
 * 
 */
package menuComponents;

/**
 * @author Rajveer Karotanian
 * 
 * Date: 1/19/2024
 * 
 * Description: This class will be used to store basic user data,
 * 				storing their name and phone number.
 * 
 *  			Method List:
 *  			- public String getName() - returns name string
 *  			- public void setName(String name) - sets name to given string
 *  			- public PhoneNumber getPhoneNumber() - returns phone number
 *  			- public void setPhoneNumber(PhoneNumber phoneNumber) - sets phone number
 *  			- public String toString() - returns formatted string output
 */
public class User {

	// Declare private variables
	private String name;
	private PhoneNumber phoneNumber;
	
	// Constructor
	public User() {
		name = "John Doe";
		phoneNumber = new PhoneNumber("+1-111-1111111");
	}
	
	// Overloaded Constructor
	public User(String name, PhoneNumber number) {
		this.name = name;
		this.phoneNumber = number;
	}

	// method to get name
	public String getName() {
		return name;
	}

	// method to set name
	public void setName(String name) {
		this.name = name;
	}

	// method to get phone number
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	// method to set phone number
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// Overloaded toString method
	public String toString() {
		return "Name: " + name + "\nPhone Number: " + phoneNumber; 
	}
	
	// self testing main method
	public static void main(String[] args) {
		// test default constructor
		User test1 = new User();
		System.out.println(test1);
		
		// test overloaded constructor
		User test2 = new User("Rajveer K", new PhoneNumber("+1-212-5551234"));
		System.out.println("\n" + test2);
		
		// test setters
		test2.setName("Sanat K");
		test2.setPhoneNumber(new PhoneNumber("+2-222-2222222"));
		
		// test getters
		System.out.println("\n"  + test2.getName());
		System.out.println(test2.getPhoneNumber());
	}
}