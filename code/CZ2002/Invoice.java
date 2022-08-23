package CZ2002;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This program holds the time where order is created and the order id
 * 
 * @author lihan,shiyuan
 * @version 1.0
 * @since 2021-11-13
 */

public class Invoice {
	/**
	 * unique identifier for the invoice
	 */
	private int invoiceID;
	/**
	 * the date and time found on the invoice
	 */
	private String receiptDateTime;
	/**
	 * unique identifier for the order
	 */
	private int orderId;
	/**
	 * the year the order was made
	 */
	private int year;
	/**
	 * the month the order was made
	 */
	private int month;
	/**
	 * the day the order was made
	 */
	private int day;

	/**
	 * This method initializes the invoiceID
	 * 
	 * @param invoiceID This holds the ID of the invoice
	 */
	public Invoice(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	/**
	 * This method is used to retrieve the ID of the invoice
	 * 
	 * @return This returns the ID of the invoice
	 */
	public int getInvoiceID() {
		return invoiceID;
	}

	/**
	 * This method is used to retrieve the ID of the order
	 * 
	 * @return This returns the ID of the order
	 */
	public int getOrderID() {
		return orderId;
	}

	/**
	 * This method is used to get the year of the order
	 * 
	 * @return This returns the year the order was made
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * This method is used to get the month of the order
	 * 
	 * @return This returns the month the order was made
	 */
	public int getMonth() {
		return this.month;
	}

	/**
	 * This method is used to get the day of the order
	 * 
	 * @return This returns the day the order was made
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * This method sets the ID of the invoice
	 * 
	 * @param invoiceID This parameter is the ID of the invoice
	 */
	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	/**
	 * This method retrieves the date and time of the receipt in String format
	 * 
	 * @return This returns the date and time of the receipt in String format
	 */
	public String getReceiptDateTime() {
		return receiptDateTime;
	}

	/**
	 * This method sets the date and time of the receipt in LocalDateTime format
	 */
	public void setReceiptDateTime() {
		LocalDateTime timeStamp = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		receiptDateTime = dtf.format(timeStamp);
		this.year = timeStamp.getYear();
		this.month = timeStamp.getMonthValue();
		this.day = timeStamp.getDayOfMonth();
	}
}
