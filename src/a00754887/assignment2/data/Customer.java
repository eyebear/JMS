package a00754887.assignment2.data;

/**
 * this is the data class for Customer
 * 
 * @author AoAo_Feng
 * 
 */
public class Customer {
	private String customerNumber;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private int cardType;
	private String creditCardNumber;
	private String street;
	private String city;
	private String postalCode;

	/**
	 * default customer constructor
	 */
	public Customer() {

	}

	/**
	 * @param customerNumber
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param cardType
	 * @param creditCardNumber
	 * @param street
	 * @param city
	 * @param postalCode
	 */
	public Customer(String customerNumber, String firstName, String lastName,
			String phoneNumber, int cardType, String creditCardNumber,
			String street, String city, String postalCode) {
		this.customerNumber = customerNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.cardType = cardType;
		this.creditCardNumber = creditCardNumber;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
	}

	/**
	 * @return the customerNumber
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber
	 *            the customerNumber to set
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the cardType
	 */
	public int getCardType() {
		return cardType;
	}

	/**
	 * @return the creditCardNumber
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	/**
	 * @param creditCardNumber
	 *            the creditCardNumber to set
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", cardType=" + cardType
				+ ", creditCardNumber=" + creditCardNumber + ", street="
				+ street + ", city=" + city + ", postalCode=" + postalCode
				+ "]";
	}

}
