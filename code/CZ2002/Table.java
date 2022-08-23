package CZ2002;
/**
 * This class is used to model a table
 * @author Li Han
 * @version 1.0
 * @since 2021-11-13
 */
public class Table {
	/**
	 * the maximum number of people that can fit the table, can be 2,4,6,8 or 10
	 */
	private int tableCapacity;
	/*
	 * check if the table is taken by someone , can be true or false
	 */
	private boolean isOccupied;
	/*
	 * unique identifier for each and every table
	 */
	private int tableNo;
	
	/**
	 * Create a new table having tableCapacity, isOccupied and tableNo 
	 * @param tableCapacity     capacity of the table
	 * @param isOccupied        table taken (true) or not taken (false)
	 * @param tableNo           unique identifier of the table
	 */
	public Table(int tableCapacity, boolean isOccupied, int tableNo) {
		this.tableCapacity = tableCapacity;
		this.isOccupied = isOccupied;
		this.tableNo = tableNo;
	}
	/**
	 * get the capacity of this table
	 * @return the capacity of this table
	 */
	public int getTableCapacity() {
		return tableCapacity;
	}
	/**
	 * change the capacity of the table
	 * @param tableCapacity New capacity for the table
	 */
	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}
	/**
	 * get the status of the this table
	 * @return  the status of the table
	 */
	public boolean isOccupied() {
		return isOccupied;
	}
	/**
	 * change the status of this table
	 * @param isOccupied New status for the table
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	/**
	 * get the unique identifier of this table
	 * @return the tableNo of the table
	 */
	public int getTableNo() {
		return tableNo;
	}
	/**
	 * change the unique identifier of this table
	 * @param tableNo New identifier for the table
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
}
