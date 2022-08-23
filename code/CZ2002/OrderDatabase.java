package CZ2002;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The database which handles all the orders, plus the creation and updating of each Order 
 * @author Hannah V
 * @version 1.0
 * @since 2021-11-13
 */
public class OrderDatabase {
	/**
	 * an array list which holds all the orders
	 */
	private ArrayList<Order> orderedItemsList;
	/**
	 * the current size of the order database, to use for order id
	 */
	private int sizeOfOrderDatabase;
	/**
	 * this is the menu where the customer can order AlaCarte and PromoSets from
	 */
	private Menu menu;
	/**
	 * the constructor for the order database with the menu of the restaurant
	 * @param menu this is the menu where the customer can order AlaCarte and PromoSets from
	 */
	public OrderDatabase(Menu menu) {
		this.sizeOfOrderDatabase = 0;
	    this.orderedItemsList = new ArrayList<Order>();
		this.menu = menu;
	}
	/**
	 * returns the array list of all orders
	 * @return the array list of all orders
	 */
	public ArrayList<Order> getOrderedItemsList() {
		return orderedItemsList;
	}
	/**
	 * Creates Order of the customer which is input into the array list of order 
	 * @param customer the customer that is currently taking the order
	 * @param sc the scanner used to scan in the info
	 * @return the order id that the created order has
	 */
	public int createOrder(Customer customer, Scanner sc){
		sizeOfOrderDatabase++;
		System.out.println("Name of Staff conducting Order: ");
		String staffName = sc.nextLine();
		System.out.println("Table number of Customer: ");
		int tableNo = Integer.parseInt(sc.nextLine());
		Order order = new Order(sizeOfOrderDatabase,staffName,customer,tableNo,menu);
		orderedItemsList.add(order);
		System.out.println("Enter AlaCarte ID: (Enter 0 to finish ordering Ala Carte Items)");
		int AlaCarteID = Integer.parseInt(sc.nextLine());
		while (AlaCarteID != 0) {
			System.out.println("Quantity of said item:");
			int quantity = Integer.parseInt(sc.nextLine());
			for (int i= 0; i< quantity;i++)
			order.addAlaCarteItem(AlaCarteID);
			System.out.println("Enter AlaCarte ID: (Enter 0 to finish ordering Ala Carte Items)");
			AlaCarteID = Integer.parseInt(sc.nextLine());
			if (AlaCarteID == 0) break;
			System.out.println("Quantity of said item:");
		    quantity = Integer.parseInt(sc.nextLine());
			for (int i= 0; i< quantity;i++)
			order.addAlaCarteItem(AlaCarteID);
			System.out.println("Enter AlaCarte ID: (Enter 0 to finish ordering Ala Carte Items)");
			AlaCarteID = Integer.parseInt(sc.nextLine());
		}
		System.out.println("Enter Promotional Set ID: (Enter 0 to finish ordering)");
		int PromoSetsID = Integer.parseInt(sc.nextLine());
		while (PromoSetsID != 0) {
			System.out.println("Quantity of said item:");
			int quantity = Integer.parseInt(sc.nextLine());
			for (int i= 0; i< quantity;i++)
			order.addPromotionalSet(PromoSetsID);
			System.out.println("Enter Promotional Set ID: (Enter 0 to finish ordering)");
			PromoSetsID = Integer.parseInt(sc.nextLine());
			if (PromoSetsID == 0) break;
			System.out.println("Quantity of said item:");
		    quantity = Integer.parseInt(sc.nextLine());
			for (int i= 0; i< quantity;i++)
			order.addPromotionalSet(PromoSetsID);
			System.out.println("Enter Promotional Set ID: (Enter 0 to finish ordering)");
			PromoSetsID = Integer.parseInt(sc.nextLine());
		}
		return sizeOfOrderDatabase;
	}
	/**
	 * add an additional AlaCarte item to an existing order, invalid orderID will be printed if orderID not found
	 * @param OrderID the id of the order that will be updated
	 * @param sc the scanner used to scan in the info
	 */
	public void addAlaCarteToOrder(int OrderID,Scanner sc) {
		for (Order o : orderedItemsList) {
			if (o.getOrderID() == OrderID) {
				System.out.println("Enter AlaCarte ID: ");
				int AlaCarteID = Integer.parseInt(sc.nextLine());
				o.addAlaCarteItem(AlaCarteID);
				return;
			}
		}
		System.out.println("Invalid OrderID!");
	}
	/**
	 * add an additional Promotional Set to an existing order, invalid orderID will be printed if orderID not found
	 * @param OrderID the id of the order that will be updated
	 * @param sc the scanner used to scan in the info
	 */
	public void addPromoSetsToOrder(int OrderID,Scanner sc) {
		for (Order o : orderedItemsList) {
			if (o.getOrderID() == OrderID) {
				System.out.println("Enter PromoSets ID: ");
				int PromoSetsID = Integer.parseInt(sc.nextLine());
				 o.addPromotionalSet(PromoSetsID);
				 return;
			}
		}
		System.out.println("Invalid OrderID!");
	}
	/**
	 * remove an AlaCarte item from an existing order
	 * invalid orderID will be printed if orderID not found
	 * AlaCarte item not found will be printed if AlaCarte id was not found
	 * @param OrderID the id of the order that will be updated
	 * @param sc the scanner used to scan in the info
	 */
	public void removeAlaCarteToOrder(int OrderID,Scanner sc) {
		for (Order o : orderedItemsList) {
			if (o.getOrderID() == OrderID) {
				System.out.println("Enter AlaCarte ID: ");
				int AlaCarteID = Integer.parseInt(sc.nextLine());
				int result = o.removeAlaCarteItem(AlaCarteID);
				if (result == 1) System.out.println("AlaCarte item removed.");
				else System.out.println("AlaCarte item not found!");
				return;
			}
		}
		System.out.println("Invalid OrderID!");
	}
	/**
	 * remove an Promotional Set from an existing order, invalid orderID will be printed if orderID not found
	 * invalid orderID will be printed if orderID not found
	 * Promotional Set not found will be printed if Promotional Set id was not found
	 * @param OrderID the id of the order that will be updated
	 * @param sc the scanner used to scan in the info
	 */
	public void removePromoSetsToOrder(int OrderID, Scanner sc) {
		for (Order o : orderedItemsList) {
			if (o.getOrderID() == OrderID) {
				System.out.println("Enter PromoSets ID: ");
				int PromoSetsID = Integer.parseInt(sc.nextLine());
				int result = o.removePromotionalSet(PromoSetsID);
				if (result == 1) System.out.println("Promotional Set removed.");
				else System.out.println("Promotional Set not found!");
				return;
			}
		}
		System.out.println("Invalid OrderID!");
	}
	/**
	 * remove an order from the order database
	 * @param OrderID the id of the order that will be removed from the array list of orders
	 */
	public void removeOrder(int OrderID) {
		for (Order o : orderedItemsList) {
			if (o.getOrderID() == OrderID) {
				orderedItemsList.remove(o);
				System.out.println("Order removed.");
				return;
			}
		}
		System.out.println("Invalid OrderID!");
		
	}
	/**
	 * returns the order from the order database
	 * @param OrderID the id of the order that is being retrieved from the array list of orders
	 * @return the order object with this order id
	 */
	public Order getOrder(int OrderID) {
		for (Order o :orderedItemsList) {
			if (o.getOrderID() == OrderID) {
				return o;
			}
		}
		return null;
	}
	/**
	 * view the order details of this order id
	 * used for testing purposes
	 * @param OrderID the id of the order that wants to be viewed
	 */
	public void viewOrder(int OrderID) {
		for (Order o : orderedItemsList) {
			if (o.getOrderID() == OrderID) {
				o.getOrderDetails();
			}
		}
	}
}