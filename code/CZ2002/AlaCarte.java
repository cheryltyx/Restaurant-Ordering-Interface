package CZ2002;
/**
 * The AlaCarte class that holds all the details of the alaCarte item 
 * @author Shi Yuan
 * @version 1.0
 * @since 2021-11-13
 */
public class AlaCarte {
	/**
	 * the unique identification number to identify each alaCarte from the menu
	 */
  private int alaCarteId;
  /**
   * the name of the alaCarte item
   */
	private String name;
	/**
	 * the price of the alaCarte item
	 */
	private double price;
	/**
	 * description of the alaCarte item, any other details relevant for alaCarte Item
	 */
	private String description;
	/**
	 * what type of food the alaCarte item is: main/ drink/ dessert
	 */
	private String type;
	/**
	 * the constructor for the alaCarte having alaCarte ID, name, price, description and type
	 * @param alaCarteId unique identification number to identify alaCarte
	 * @param name name of alaCarte
	 * @param price price of alaCarte
	 * @param description description of alaCarte 
	 * @param type what type of food alaCarte is
	 */
  public AlaCarte(int alaCarteId, String name, double price, String description, String type){
    this.alaCarteId = alaCarteId;
    this.name = name;
    this.price = price;
    this.description = description;
    this.type = type;
  }
  /**
   * returns the unique identifier of the alaCarte item to identify it in the menu
   * @return the id of the alaCarte item
   */
  public int getAlaCarteId(){
    return alaCarteId;
  }
  /**
   * returns the name of the alaCarte item
   * @return the name of alaCarte item
   */
  public String getName(){
    return name;
  }
  /**
   * returns the price of alaCarte item
   * @return the price of alaCarte item
   */
  public double getPrice(){
    return price;
  }
  /**
   * returns the description of alaCarte item
   * @return the description of alaCarte item
   */
  public String getDescription(){
    return description;
  }
  /**
   * returns the type of alaCarte item
   * @return the type of alaCarte item
   */
  public String getType(){
    return type;
  }
  /**
   * change the name of the alaCarte item
   * @param name the new name of the alaCarte item
   */
  public void updateName(String name){
    this.name = name;
  }
  /**
   * change the price of the alaCarte item
   * @param price the new price of the alaCarte item
   */
  public void updatePrice(double price){
    this.price = price;
  }
  /**
   * change the description of the alaCarte item
   * @param description the new description of the alaCarte item
   */
  public void updateDescription(String description){
    this.description = description;
  }
  /**
   * the new type of the alaCarte item
   * @param type the new type of the alaCarte item
   */
  public void updateType(String type){
    this.type = type;
  }


  

}