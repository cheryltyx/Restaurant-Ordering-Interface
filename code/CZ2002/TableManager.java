package CZ2002;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * This class is used to keep track of the table array list and also contain functions to manage the tables
 * @author Li Han
 * @version 1.0 
 * @since 2021-11-13
 */
public class TableManager {
	/**
	 * generate a list of existing tables 
	 */
  public TableManager(){
    generateTable();
  }
  Scanner sc = new Scanner(System.in);
  /**
   * ArrayList of all tables in the restaurant
   */
  private ArrayList<Table> availableTableList = new ArrayList<Table>();
  
 /**
  * Method to print the list of tables regardless of its status (occupied or not occupied)
  */
  public void printTableList(){
    System.out.println("The list of tables are: ");
    for (Table table: availableTableList) {
      System.out.println(table.getTableNo());
    }
  }
 
  /**
   * Method to get the ArrayList of tables
   * @return the ArrayList consisting of tables
   */
  public ArrayList<Table> getTableList(){
    return this.availableTableList;
  }
  
  /**
   * Method to check the availability of the table. User will be asked to enter the table of interest
   * @return the status of the table if it is occupied or not
   */
  public boolean checkTable() {
    printTableList();
    System.out.println("Please enter table number of interest");
    Scanner sc = new Scanner(System.in);
    int tableNo = sc.nextInt();
    for (Table t : availableTableList) {
      if (t.getTableNo() == tableNo) {
        if (t.isOccupied()) {
          System.out.println("Table " + tableNo + " has been reserved by some customers!");
          return true; // table taken
        }
      }
    }
    System.out.println("Table " + tableNo + " is not reserved by any customer yet!");
    return false; // table available
  }
  
  /**
   * Method to check the ArrayList, availableTableList and to find the first available table
   * @return the unique identifier of the first table that is available. If none available, the method will return -1
   */
  public int availableTable() {
    for (Table table: availableTableList) {
      if (!table.isOccupied()) { // if table is not occupied -> false (not false = true)
        return table.getTableNo();
      }
    }
    return -1; // all table occupied
  }
  
  /**
   * Method to check the ArrayList, availableTableList to find tables with enough seats to fit the pax size
   * @param paxSize  size used to check for tables that have enough space
   * @return an ArrayList consisting of tables with enough pax size that the user requested
   */
  public ArrayList<Table> getTableOfPaxSize(int paxSize) {
    ArrayList<Table> tableOfPaxSize = new ArrayList<Table>();
    for (Table table: availableTableList) {
      if (table.getTableCapacity() >= paxSize) { 
        tableOfPaxSize.add(table);
      }
      
    }
    return tableOfPaxSize;
  }
  
  /**
   * Method to generate the list of tables in the console
   */
  private void generateTable() {
    String line = "";
    String path = "src\\table.csv";
    
    try {
      BufferedReader br = new BufferedReader(new FileReader(path));
      int i = 1;
      while ((line = br.readLine()) != null) {
        if (i == 1) { // skip the first line
          i++;
          continue;
        }
        String[] values = line.split(",");
        System.out.println("tableNo: " + values[0] + ", tableCapacity: " + values[1] + ", isOccupied: " + values[2]);
        Table t = new Table(Integer.parseInt(values[1]),Boolean.parseBoolean(values[2]),Integer.parseInt(values[0]));
        availableTableList.add(t);
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
}