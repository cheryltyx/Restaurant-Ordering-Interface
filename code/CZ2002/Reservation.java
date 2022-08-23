package CZ2002;

import java.time.LocalDateTime;

/**
 * This class is to model a reservation
 * 
 * @author Li Han
 * @version 1.0
 * @since 2021-11-13
 */
public class Reservation {
	/**
	 * the date of the reservation in LocalDateTime format
	 */
	private LocalDateTime date;
	/**
	 * the date of the reservation in String format
	 */
	private String dateTime;
	/**
	 * the pax size of the reservation
	 */
	private int pax;
	/**
	 * the name of the customer making the reservation
	 */
	private String customerName;
	/**
	 * the contact number of the customer
	 */
	private int contact;
	/**
	 * the table number the customer dined at
	 */
	private int tableNo;

	/**
	 * Create a new reservation with the date and time in LocalDateTime format and
	 * in string format, pax size, customer name, contact number and the table the
	 * customer dined at
	 * 
	 * @param date          the date in LocalDateTime format
	 * @param dateTime      the date in String format
	 * @param pax           the pax of reservation
	 * @param customerName  the name of the customer
	 * @param contact       the contact of the customer
	 * @param tableNo		the table the customer dined at
	 */
	public Reservation(LocalDateTime date, String dateTime, int pax, String customerName, int contact, int tableNo) {
		super();
		this.date = date;
		this.dateTime = dateTime;
		this.pax = pax;
		this.customerName = customerName;
		this.contact = contact;
		this.tableNo = tableNo;
	}

	/**
	 * get the date of reservation in String format
	 * @return the date of reservation in String format
	 */
	public String getDateTime() {
		return dateTime;
	}
	/**
	 * change the date of reservation in String format
	 * @param dateTime New date of reservation in String format
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * get the date of reservation in LocalDateTime format
	 * @return the date of reservation in LocalDateTime format
	 */
	public LocalDateTime getDate() {
		return date;
	}
	
	/**
	 * change the date of reservation in LocalDateTime format
	 * @param date New date of reservation in LocalDateTime format
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * get the pax of reservation
	 * @return the pax of reservation
	 */
	public int getPax() {
		return pax;
	}
	
	/**
	 * change the pax of reservation
	 * @param pax New pax of reservation
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * get the customer name
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * change the customer name
	 * @param customerName New customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * get the contact of the customer
	 * @return the contact of the customer
	 */
	public int getContact() {
		return contact;
	}
	
	/**
	 * change the contact of the customer
	 * @param contact New contact of the customer
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}

	/**
	 * get the table where the customer dined at
	 * @return the table where the customer dined at
	 */
	public int getTableNo() {
		return tableNo;
	}
	
	/**
	 * change the table where the customer dined at
	 * @param tableNo New table number where the customer dined at
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

}
