package menuComponents;

/**
 * Name: Sanat Kanwal
 * Date: 2024-01-19
 * Description: This class represents a phone number with the information; country code, area code, and number. 
 * 				It has methods for setting, getting, and parsing phone numbers.
 * 
 * Method List:
 * 	public PhoneNumber(String countryCode, String areaCode, String number) - Takes in information like the countryCode, areaCode, and number
 * 	public PhoneNumber() - Default Constructor
 *  public PhoneNumber(String phone) - Take in the string of the phone and parse it
 * 	public String getCountryCode() - return countryCode
 * 	public String getAreaCode() - return areaCode
 *  public String getNumber() - return number
 *  public void setCountryCode(String countryCode) -  set country code to country code
 *  public void setAreaCode(String areaCode) - set areaCode to areaCode
 *  public void setNumber(String number) - set Number to Number
 *  public String toString() - Formats the string in a certain way
 *  public PhoneNumber fromString(String phoneNumberString) - Take in a phone unmber string and parts it like thia; +CountryCode-AreaCode-Number
 * 
 */
public class PhoneNumber {
	/**
	 * Declare all private variables
	 */
	private String countryCode;
	private String areaCode;
	private String number;

	/**
	 * Overloaded Constructor
	 * - Takes in information like the countryCode, areaCode, and number
	 */
	public PhoneNumber(String countryCode, String areaCode, String number) {
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.number = number;
	}
	
	/**
	 * Default Constructor
	 */
	public PhoneNumber() {
		
	}

	/**
	 * Overloaded Constructor
	 * - Take in the string of the phone and parse it
	 */
	public PhoneNumber(String phone) {
		fromString(phone);
	}

	/**
	 * getCountryCode method
	 * - return countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * getAreaCode method
	 * - return areaCode
	 * @return
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * getNumber
	 * - return number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * setCountryCode method
	 * - set country code to country code
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * setAreaCode
	 * - set areaCode to areaCode
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * setNumber
	 * - set Number to Number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	/**
	 * toString Method
	 * - Formats the string in a certain way
	 */
	public String toString() {
		//return the string
		return "+" + countryCode + "-" + areaCode + "-" + number;
	}

	/**
	 * fromString method
	 * - Take in a phone unmber string and parts it based on the format; +CountryCode-AreaCode-Number
	 */
	public PhoneNumber fromString(String phoneNumberString) {
		//parts the string by using '-'
		String[] parts = phoneNumberString.split("-");

		// Assuming the string is in the format: +CountryCode-AreaCode-Number
		this.setCountryCode(parts[0].substring(1));// Remove the '+' sign
		this.setAreaCode(parts[1]);
		this.setNumber(parts[2]);

		//Return the object
		return new PhoneNumber(countryCode, areaCode, number);
	}
	
	public static void main(String[] args) {
		//Create an object sending in a number and print it
		PhoneNumber myNumber = new PhoneNumber("+1-212-5551234");
		System.out.println("My phone number is: " + myNumber);
	}
}
