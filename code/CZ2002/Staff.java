package CZ2002;
/**
 * This class is used to model a staff
 * @author Cheryl
 * @version 1.0
 * @since 2021-11-13
 */
public class Staff {
	/**
	 * the name of the staff
	 */
	private String staffName;
	/**
	 * the gender of the staff
	 */
	private char gender;
	/**
	 * the unique employeeID of the staff
	 */
	private int employeeID;
	/**
	 * the job position of the staff
	 */
	private String jobTitle;
	
	
	/**
	 * Create a new staff with their name, gender, unique employeeID and their position
	 * @param staffName    the name of the staff
	 * @param gender       the gender of the staff
	 * @param employeeID   the employeeID of the staff
	 * @param jobTitle     the position of the staff
	 */
	public Staff(String staffName, char gender, int employeeID, String jobTitle) {
		
		this.staffName = staffName;
		this.gender = gender;
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
	}
	/**
	 * Method to retrieve personal details of the staff
	 */
	public void getStaffInformation() {
		System.out.println("Staff name: "+ staffName);
		System.out.println("Staff gender: " + gender);
		System.out.println("Staff ID: " + employeeID);
		System.out.println("Staff Job Title: "+ jobTitle);
	}
	
}
