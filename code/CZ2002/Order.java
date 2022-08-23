package CZ2002;
import java.util.ArrayList;
/**
 * This Order class holds the various attributes and methods that can be done to the order.
 * @author Hannah V
 * @version 1.0
 * @since 2021-11-13
 */
public class Order {
	/**
	 * customer this is their order, we would be able to associate all their info to this order, such as their membership status
	 */
	private Customer customer;
	/**
	 * OrderID the unique id associated to each order taken from the order database
	 */
	private int OrderID;
	/**
	 * staffName the staff name that conducts the order
	 */
	private String staffName;
	/**
	 * tableNumber this is the tableNumber where this order is taken and their ordered items will be sent to
	 */
	private int tableNumber;
	/**
	 * orderedAlaCarteItems this is the arrayList with all the AlaCarte items a customer ordered
	 */
	private ArrayList<AlaCarte> orderedAlaCarteItems;
	/**
	 * orderedPromoSets this is the arrayList with all the PromoSets a customer ordered
	 */
	private ArrayList<PromotionalSet> orderedPromoSets;
	/**
	 * menu this is the menu where the customer can order AlaCarte and PromoSets from
	 */
	private Menu menu;
	/**
	 * totalPrice this is the raw total price of all the AlaCarte and PromoSets before the addition of gst or service charge or a deduction of membership
	 */
	private double totalPrice;
	/**
	 * This is the constructor to Order with its Order ID, staff name, customer taking order, table they are sat at and the current menu
	 * @param OrderID the unique id associated to each order
	 * @param staffName the staff name that conducts the order
	 * @param customer this is their order
	 * @param tableNumber this is the tableNumber where this order is taken
	 * @param menu this is the menu where the customer can order menu items from
	 */
	public Order (int OrderID, String staffName, Customer customer,int tableNumber,Menu menu) {
		this.orderedAlaCarteItems = new ArrayList<AlaCarte>();
		this.orderedPromoSets = new ArrayList<PromotionalSet>();
		this.OrderID = OrderID;
		this.totalPrice = 0;
		this.staffName = staffName;
		this.customer = customer;
		this.tableNumber = tableNumber;
		this.menu = menu;
	}
	/**
	 * returns the customer who took this order
	 * @return customer who took this order
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * returns the unique identification number of this order
	 * @return its order id
	 */
	public int getOrderID() {
		return OrderID;
	}
	/**
	 * returns the table Number where the customer sat for this order
	 * @return the table number for this order
	 */
	public int getTableNumber() {
		return tableNumber;
	}
	/**
	 * returns the total price of this order before tax,membership discount,gst
	 * @return the total price of this order
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * returns the staff name who took this order
	 * @return the staff name who took this order
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * returns the array list of ala carte items the customer ordered
	 * @return the array list of ala carte items ordered
	 */
	public ArrayList<AlaCarte> getOrderedAlaCarteItems() {	
		return orderedAlaCarteItems;
	}
	/**
	 * returns the array list of promotional sets the customer ordered
	 * @return the array list of promotional sets the customer ordered
	 */
	public ArrayList<PromotionalSet> getOrderedPromoSets() {
		return orderedPromoSets;
	}
	/**
	 * adds the alaCarte item to the array list of orderedAlaCarte Items,
	 * prints out invalid alacarte Item if AlaCarte does not exist in meanu
	 * @param AlaCarteItemID the id of the ordered AlaCarte item
	 */
	public void addAlaCarteItem(int AlaCarteItemID) {
		if (menu.getAlaCarteItem(AlaCarteItemID) != null) {
			AlaCarte a = menu.getAlaCarteItem(AlaCarteItemID);
			orderedAlaCarteItems.add(a);
			totalPrice = totalPrice + a.getPrice();
			System.out.println("AlaCarte Item added");
			}
		else {
			System.out.println("Invalid AlaCarteItem ID!");
		}
	}
	/**
	 * adds the Promotional set to the array list of orderedPromoSets Items,
	 * prints out invalid Promotional set if Promotional set does not exist in mean
	 * @param PromotionalSetID the id of the ordered Promotional set
	 */
	public void addPromotionalSet(int PromotionalSetID) {
		if (menu.getPromotionalItem(PromotionalSetID) != null) {
			PromotionalSet p = menu.getPromotionalItem(PromotionalSetID);
			orderedPromoSets.add(p);
			totalPrice = totalPrice + p.getPrice();
			System.out.println("Promotional Set added");
		}
		else {
			System.out.println("Invalid PromotionalSet ID!");
		}
	}
	/**
	 * removes the alaCarte item from the array list of orderedAlaCarte Items,
	 * @param AlaCarteItemID the id of the AlaCarte item they wish to remove
	 * @return 1 if successfully removed, or -1 if not successfully removed
	 */
	public int removeAlaCarteItem(int AlaCarteItemID) {
		for (AlaCarte a: orderedAlaCarteItems) {
			if (a.getAlaCarteId() == AlaCarteItemID) {
				orderedAlaCarteItems.remove(a);
				totalPrice = totalPrice - a.getPrice();
				return 1;
			}
		}
		return -1;
	}
		
	/**
	 * removes the Promotional set from the array list of orderedPromoSets Items,
	 * @param PromotionalSetID the id of the Promotional set item they wish to remove
	 * @return 1 if successfully removed, or -1 if not successfully removed
	 */
	public int removePromotionalSet(int PromotionalSetID) {
		for (PromotionalSet p: orderedPromoSets) {
			if (p.getSetId() == PromotionalSetID) {
				orderedPromoSets.remove(p);
				totalPrice = totalPrice - p.getPrice();
				return 1;
			}
		}
		return -1;
	}
	/**
	 * calculates the final price which includes the membership discount(if necessary) , gst and service charge
	 * @return the final price
	 */
	public double calculateFinalPrice() {
		double membership = customer.getCustStatus()  ? .9 : 1.0; 
		return totalPrice * 1.07 * 1.10 * membership;
	}
	/**
	 * checking through the order details for testing purposes
	 */
	public void getOrderDetails() {
		System.out.println("Order taken by: "+staffName);
		System.out.println("Table number: "+ tableNumber);
		System.out.println("Items ordered: ");
		for (AlaCarte a: orderedAlaCarteItems) 
			System.out.println(a.getName()+ " , $"+ a.getPrice());
		for (PromotionalSet p: orderedPromoSets) 
			System.out.println(p.getSetName()+ " , $"+ p.getPrice());
		System.out.println("Total cost: $" + totalPrice);
	}
	
}
