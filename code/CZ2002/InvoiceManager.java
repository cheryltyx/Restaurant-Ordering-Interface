package CZ2002;
import java.util.ArrayList;
/**
 * This program is to keep track and store invoices
 * @author lihan,shiyuan
 * @version 1.0
 * @since 2021-11-13
 *
 */

public class InvoiceManager {
	/**
	 * This is the unique identifier for invoice ID
	 */
	private int idTracker;
	/**
	 * This is an arraylist that contains invoices
	 */
	private static ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
	/**
	 * This method helps to autogenerate the id of the invoice in ascending order
	 */
	public InvoiceManager() {
		idTrackerSetUp();
	}
	/**
	 * This method creates invoice
	 */
	public void createInvoice() {
		Invoice i = new Invoice(idTracker);
		idTracker++;
		i.setReceiptDateTime();
		addInvoice(i);
	}
	/**
	 * This method adds invoice to the list
	 * @param i This parameter is the invoice object
	 */
	public void addInvoice(Invoice i) {
		invoiceList.add(i);
	}
	/**
	 * This method removes invoice from the list
	 * @param i This parameter is the invoice object
	 */
	public void removeInvoice(Invoice i) {
		invoiceList.remove(i);
	}
	/**
	 * This method helps to autogenerate the id of the invoice in ascending order
	 */
	public void idTrackerSetUp() {
		if (invoiceList.isEmpty()) {
			idTracker = 1;
		}
		else {
			int size = invoiceList.size();
			idTracker = invoiceList.get(size-1).getInvoiceID() + 1;
		}
	}
/**
 * This method is used to retrieve the invoice list
 * @return list of invoices
 */
  public ArrayList<Invoice> getInvoiceList(){
    return invoiceList;
  }
}

