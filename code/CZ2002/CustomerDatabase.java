package CZ2002;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/**
 * This program contains an arraylist of customers
 * @author cheryl
 * @version 1.0
 * @since 2021-11-13
 */
public class CustomerDatabase {
	/**
	 * This method is to generate the customer database
	 */
	public CustomerDatabase() {
		generateCustomer();
	}
	Scanner sc = new Scanner(System.in); 
	/**
	 * this contains the ArrayList containing Customer objects
	 */
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	/**
	 * This method is to add customer into the arraylist
	 */
	public void addCustomer() {
		System.out.println("Name of customer");
		String custName = sc.nextLine();
		System.out.println("Contact number");
		int custContact = sc.nextInt();
		System.out.println("Membership status(true/false)");
		boolean custStatus = sc.nextBoolean();
		System.out.println("Customer ID");
		int custID = sc.nextInt();
		sc.nextLine();

		for(Customer c: customerList) {
			if (c.getCustContact() == custContact) {
				System.out.println("Customer already exist in database");
				return;
			}
		}
		Customer cust = new Customer(custName, custContact, custStatus, custID);
		customerList.add(cust);

	}
	/**
	 * This method is to remove customer from arraylist
	 */
	public void removeCustomer() {
		System.out.println("Customer contact number to be removed");
		int custContact = sc.nextInt();
		int check = 0;
		sc.nextLine();
		for(Customer c: customerList) {
			if (c.getCustContact() == custContact) {
				customerList.remove(c);
				check = 1;
				break;
			}
		}
		if(check == 1) {
			System.out.println("Customer successfully removed");
		}
		else {
			System.out.println("Customer does not exist in database");
		}
	}
	/**
	 * This method is to retrieve customer object
	 * @param custContact This is the customer's contact number
	 * @return This returns a customer object or null
	 */
  public Customer getCustomer(int custContact) {
		for(Customer c: customerList) {
			if (c.getCustContact() == custContact) {
				return c;
			}
		}
    return null;
	}
  /**
   * This method prints the details of a customer
   */
  public void printCustomer() {
	  for(Customer c: customerList) {
		  c.getCustomerDetails();
	  }
  }
  /**
   * This method retrieves the arraylist of customer database
   * @return This returns the arraylist of customer database
   */
  public ArrayList<Customer> getCustomerList(){
		return this.customerList;
	}
  /**
   * This method generates a list of existing customers
   */
  private void generateCustomer() {
		//String path = "C:\\Users\\Li Han\\Desktop\\NTU\\Y2S1\\CZ2002 Obj Oriented\\Assignment\\table.csv";
		String line = "";
		String path = "src\\customer.csv"; //Pls import to source (A FOLDER CALLED SRC) AND NOT ELSE WHERE IF NOT EXCEPTION WILL BE THROWN
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			int i = 1;
			while ((line = br.readLine()) != null) {
				if (i == 1) { // skip the first line
					i++;
					continue;
				}
				String[] values = line.split(",");
				System.out.printf("Customer name:%-13s ", values[0]);
				System.out.printf("Contact number:%-8s ", values[1]);
				System.out.printf("Membership status:%-5s ", values[2]);
				System.out.printf("Customer ID:%2s\n", values[3]);
				Customer c = new Customer(values[0],Integer.parseInt(values[1]),Boolean.parseBoolean(values[2]),Integer.parseInt(values[3]));
				customerList.add(c);
				i++;
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
  /**
   * This method updates the list of customers
   */
  public void updateCSV() {
	  String filepath = "src\\customer.csv";
	  Customer data;

		try {
			FileWriter fw = new FileWriter(filepath, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("custName" + "," + "contact" + "," + "membership" + "," + "custID");
			
			for(int row = 0; row < customerList.size(); row++)
			{
				data = customerList.get(row);
				pw.println(data.getCustName() + "," + data.getCustContact() + "," + data.getCustStatus() + "," + data.getCustID());
			}
			
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.printf("customer list up to date!\n");
			return;
		}

		System.out.printf("customer list up to date!\n");

	}
	
}


