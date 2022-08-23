package CZ2002;
/**
 * The Customer class that holds all the details of the Customer
 * @author cheryl
 * @version 1.0
 * @since 2021-11-13
 */
public class Customer {
	/**
	 * name of the customer
	 */
	private String custName;
	/**
	 * the contact number of the customer,which will be used to identify each customer
	 */
	private int contact;
	/**
	 * the membership status of the customer, to see if they are applicable for membership discount
	 * values can be true or false
	 * true meaning the customer is a member
	 * false meaning the customer is not a member
	 */
	private boolean membership;
	/**
	 * the identification number for customer
	 */
	private int custID;
	/**
	 * the constructor for the customer having customer name, customer contact, membership, customer id
	 * @param custName the name of the customer
	 * @param contact the contact number of the customer
	 * @param membership the membership status of the customer
	 * @param custID the identification number for customer
	 */
	public Customer(String custName, int contact, boolean membership, int custID) {
		this.custName = custName;
		this.contact = contact;
		this.membership = membership;
		this.custID = custID;
	}
	/**
	 * prints out the customer name, contact number, membership and customer id of this customer
	 */
	public void getCustomerDetails() {
		System.out.printf("Customer name:%-13s ", custName);
		System.out.printf("Contact number:%-8d ", contact);
		System.out.printf("Membership status:%-5B ", membership);
		System.out.printf("Customer ID:%2d\n", custID);
	}
	/**
	 * returns the id of the customer
	 * @return the id of the customer
	 */
	public int getCustID() {
		return custID;
	}
	/**
	 * returns the contact number of the customer
	 * @return the contact number of the customer
	 */
	public int getCustContact() {
		return contact;
	}
	/**
	 * returns the membership status of the customer, can be true or false
	 * @return the membership status of customer
	 */
	public boolean getCustStatus() {
		return membership;
	}
	/**
	 * returns the name of the customer
	 * @return the name of the customer
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * set the membership status, depending on the choice of the customer, can be true or false
	 * true means they want to become a member
	 * false means they do not want to become a member
	 * @param choice the choice of customer if they want to be a member
	 */
	public void setCustStatus(boolean choice) {
		this.membership = choice;
	}

}


