package CZ2002;

import java.util.ArrayList;

/**
 * This class is used to contain the promotional sets
 * 
 * @author Shi Yuan
 * @version 1.0
 * @since 2021-11-13
 *
 */
public class PromotionalSet {
	/**
	 * the unique identifier for each set
	 */
	private int setId;
	/**
	 * ArrayList of alaCarte items in the promotional set
	 */
	private ArrayList<AlaCarte> alaCarteList;
	/**
	 * price of the set
	 */
	private double price;
	/**
	 * description of the set
	 */
	private String description;
	/**
	 * name of the set
	 */
	private String setName;
	
	/**
	 * Create a promotional set with its unique id, name, individual alaCarte items, price and description
	 * @param setId        unique identifier of the set 
	 * @param setName      the set name
	 * @param alaCarteList the alaCarteList of alaCarte items
	 * @param price        the price of the set
	 * @param description  the description of the set
	 */
	public PromotionalSet(int setId, String setName, ArrayList<AlaCarte> alaCarteList, double price,
			String description) {
		this.setId = setId;
		this.alaCarteList = alaCarteList;
		this.price = price;
		this.description = description;
		this.setName = setName;
	}
	/**
	 * change the alaCarteList to the most updated version
	 * @param alaCarteList New alaCarteList
	 */
	public void updateAlaCarteList(ArrayList<AlaCarte> alaCarteList) {
		this.alaCarteList = alaCarteList;
	}
	/**
	 * change the price of the set 
	 * @param price New price for the set
	 */
	public void updatePrice(double price) {
		this.price = price;
	}
	/**
	 * change the description of the set
	 * @param description New description for the set
	 */
	public void updateDescription(String description) {
		this.description = description;
	}
	/**
	 * get the alaCarteList
	 * @return the alaCarteList
	 */
	public ArrayList<AlaCarte> getAlaCarteList() {
		return alaCarteList;
	}
	
	/**
	 * get the price of the set 
	 * @return the price of the set
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * get the id of the set
	 * @return the id of the set
	 */
	public int getSetId() {
		return setId;
	}
	
	/**
	 * get the description of the set
	 * @return the description of the set
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * get the set name
	 * @return the set name
	 */
	public String getSetName() {
		return setName;
	}
	
	/**
	 * change the set name
	 * @param setName New set name
	 */
	public void setSetName(String setName) {
		this.setName = setName;
	}

}