package CZ2002;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to create reservations and to modify reservations
 * @author Li Han
 * @version 1.0
 * @since 2021-11-13
 */
public class ReservationMaker {
	/**
	 * Access reservation manager and to get necessary functions to create reservations
	 */
	private ReservationManager rm;
	/**
	 * Access table manager and to get necessary functions to create reservations
	 */
	private TableManager tm;
	
	/**
	 * Create reservation maker that requires reservation manager and table manager 
	 * @param rm   used by reservation maker to interact with reservation manager
	 * @param tm   used by reservation maker to interact with table manager
	 */
	ReservationMaker(ReservationManager rm, TableManager tm) {
		this.rm = rm;
		this.tm = tm;
	}

	Scanner sc = new Scanner(System.in);
	
	/**
	 * Method to create reservations by interacting with reservation manager and table manager
	 */
	public void createReservation() {
		ArrayList<Table> TableList = tm.getTableList();
		
		System.out.println("Please enter the date of reservation in this format: YYYY-MM-DD (e.g 2021-10-23)");
		String dateTime = sc.nextLine();
		System.out.println("Please enter the time of reservation in this format: HH:mm (e.g 22:52)");
		String time = sc.nextLine();
		String date = dateTime + "T" + time;
		LocalDateTime dt = LocalDateTime.parse(date); // raw format of type LocalDateTime
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd YYYY, HH:mm"); // convert to new format
		String bookingTime = dtf.format(dt); // format into the new format of type String (so that can print)

		System.out.println("please enter no of pax: (int) ");
		int pax = Integer.parseInt(sc.nextLine());

		System.out.println("Please enter customer name (string)");
		String custName = sc.nextLine();
		System.out.println("Please enter customer contact number (int)");
		int contact = Integer.parseInt(sc.nextLine());

		// check if there exist reservation made by this person
		for (int x = 0; x < rm.getReservationList().size(); x++) {
			if (rm.getReservationList().get(x).getContact() == contact) {
				System.out.println("Customer has an existing reservation!");
				return;
			}
		}

		int tableNo = getTableNo(pax);

		if (tableNo != -1) {
			// check to see if someone else made a reservation on the same table but
			// different time (must be after 1 hr of the previous reservation)
			ArrayList<Reservation> reservationList = rm.getReservationList();
			for (int i = 0; i < reservationList.size(); i++) {
				Reservation r = reservationList.get(i);
				if (r.getTableNo() == tableNo) { // if same table check if same timing
					if (r.getDate().toLocalDate().equals(dt.toLocalDate()) && (dt.getHour() == r.getDate().getHour())) {
						// if within 1 hour of that reservation:
						System.out.println("Reservation made by someone else on " + r.getDateTime() + ".");
						System.out.println("Please choose other tables!");
						return;
					}
				}
			}
			///////////////////////////////////////////////////////////////////
			Reservation r = new Reservation(dt, bookingTime, pax, custName, contact, tableNo);
			rm.addReservation(r);
			System.out.println("Reservation made at " + bookingTime);
			for (Table t : TableList) {
				if (t.getTableNo() == tableNo) {
					t.setOccupied(true);
				}
			}
		} else {
			System.out.println("Reservation cannot be done.");
			return;
		}
	}

	/**
	 * Method to remove reservations
	 */
	public void cancelReservation() {
		Reservation reservation = rm.checkReservationValid(true); // remove reservation
		ArrayList<Reservation> reservationList = rm.getReservationList();
		// remove table of reservation
		int tableNo = reservation.getTableNo();
		ArrayList<Table> TableList = tm.getTableList();
		for (Table t : TableList) {
			if (t.getTableNo() == tableNo) {
				// check if there exist another customer on reservation list
				for (Reservation r : reservationList) {
					if (r.getTableNo() == tableNo) {
						t.setOccupied(true);
						return;
					}
				}
				t.setOccupied(false);
			}
		}

	}

	/**
	 * Method to update Reservations such as updating time,table and pax size
	 */
	public void updateReservation() {
		System.out.println("Please enter your choice (1-3):");
		System.out.println("1: change reservation time");
		System.out.println("2: change table");
		System.out.println("3: change reservation time & table");
		System.out.println("4: change reservation pax");
		int choice = Integer.parseInt(sc.nextLine());;
		System.out.println("Please enter contact number used in reservation");
		int contactNo = Integer.parseInt(sc.nextLine());;
		if (choice < 1 || choice > 4) {
			System.out.println("Invalid choice! Please try again later!");
		} else {
			ArrayList<Table> TableList = tm.getTableList();
			ArrayList<Reservation> ReservationList = rm.getReservationList();
			int tableNo = 0;

			if (choice == 1) {
				rm.updateReservation(contactNo); // change reservation time (dunnid remove table)
			} else if (choice == 2) { // set old table to false and new table to true for isOccupied
				System.out.println("Please choose table of choice");
				tm.printTableList();
				int tableChoice = Integer.parseInt(sc.nextLine());;

				for (Reservation r : ReservationList) {
					if (r.getContact() == contactNo) {
						tableNo = r.getTableNo();
						Reservation target = r;
						for (Table t : TableList) {
							if (t.getTableNo() == tableChoice) {
								if (!t.isOccupied()) { // table not occupied at all
									target.setTableNo(tableChoice);
									System.out.println("Table successfully changed to table " + tableChoice
											+ " for customer " + target.getCustomerName());
									t.setOccupied(true);
									TableList.get(tableNo - 1).setOccupied(false);
									// check if got other reservation on this table that got removed
									for (Reservation r2 : ReservationList) {
										if (r2.getTableNo() == tableNo) {
											TableList.get(tableNo - 1).setOccupied(true);
											return;
										}
									}
									return;
								} else if (t.isOccupied()) { // table occupied, check if timing clash
									for (Reservation r3 : ReservationList) {
										if (r3.getDate().toLocalDate().equals(r.getDate().toLocalDate())
												&& r3.getDate().getHour() == r.getDate().getHour()) { // same date

											System.out.println(
													"Table is reserved by another customer on the same reservation time!");
											System.out.println("Please choose another table!");
											return;

										} else { // table is available to be assigned
											target.setTableNo(tableChoice);
											System.out.println("Table successfully changed to table " + tableChoice
													+ " for customer " + target.getCustomerName());
											t.setOccupied(true);
											TableList.get(tableNo - 1).setOccupied(false);
											// check if got other reservation on this table that got removed
											for (Reservation r2 : ReservationList) {
												if (r2.getTableNo() == tableNo) {
													t.setOccupied(true);
													return;
												}
											}
											return;
										}

									}
								}

							}
						}
					}
				}

			} else if (choice == 3) {
				boolean available = true;
				System.out.println("Please choose table of choice");
				tm.printTableList();
				int tableChoice = Integer.parseInt(sc.nextLine());;

				for (Reservation r : ReservationList) {
					if (r.getContact() == contactNo) {
						tableNo = r.getTableNo();
						Reservation target = r;
						for (Table t : TableList) {
							if (t.getTableNo() == tableChoice) {
								if (!t.isOccupied()) { // table not occupied at all
									target.setTableNo(tableChoice);
									System.out.println("Table successfully changed to table " + tableChoice
											+ " for customer " + target.getCustomerName());
									t.setOccupied(true);
									TableList.get(tableNo - 1).setOccupied(false);
									// check if got other reservation on this table that got removed
									for (Reservation r2 : ReservationList) {
										if (r2.getTableNo() == tableNo) {
											TableList.get(tableNo - 1).setOccupied(true);
										}
									}
								} else if (t.isOccupied()) { // table occupied, check if timing clash
									for (Reservation r3 : ReservationList) {
										if (r3.getDate().toLocalDate().equals(r.getDate().toLocalDate())
												&& r3.getDate().getHour() == r.getDate().getHour()) { // same date

											System.out.println(
													"Table is reserved by another customer during this reservation time!");
											System.out.println("Please choose another table!");
											available = false;
											break;

										} else { // table is available to be assigned
											target.setTableNo(tableChoice);
											System.out.println("Table successfully changed to table " + tableChoice
													+ " for customer " + target.getCustomerName());
											t.setOccupied(true);
											TableList.get(tableNo - 1).setOccupied(false);
											// check if got other reservation on this table that got removed
											for (Reservation r2 : ReservationList) {
												if (r2.getTableNo() == tableNo) {
													t.setOccupied(true);

												}
											}

										}

									}
								}

							}
						}
					}
				}
				//////////////////////////////////////////////

				////////////////////////////////////////////

				if (available) {
					rm.updateReservation(contactNo); // change timing
				} else { // if available = false means table of choice not available
					System.out.println("Do you still want to change timing of reservation? Please enter your choice!");
					System.out.println("0 : no");
					System.out.println("1 : yes");
					int option = Integer.parseInt(sc.nextLine());;
					if (option == 0) {
						return;
					} else { // option is 1 (yes)
						rm.updateReservation(contactNo); // update timing
					}
				}

			} else if (choice == 4) {
				System.out.println("Please enter the new pax");
				int newPax = Integer.parseInt(sc.nextLine());;
				for (Reservation r : ReservationList) {
					if (r.getContact() == contactNo) {
						r.setPax(newPax);
						tableNo = r.getTableNo();

						// ensure table can fit the new pax
						// if old table capacity smaller than new pax, need to change table
						if (TableList.get(tableNo - 1).getTableCapacity() < newPax) {
							System.out.println("New pax size cannot fit current table!");
							int check = getTableNo(newPax);

							if (check != -1) {
								for (Table t : TableList) {
									if (t.getTableNo() == check) {
										// check if table is reserved by other customers at the same timing
										for (Reservation r2 : ReservationList) {
											if (r != r2) {
												if (r.getDate().toLocalDate().equals(r2.getDate().toLocalDate())) {// if
																													// date
																													// same
													if (r.getDate().getHour() == r2.getDate().getHour()) { // if hour
																											// same
														System.out.println("Please choose other tables! current choice of table is already taken by existing customer!");
														return;
													}
												}
											}
										}
										t.setOccupied(true);
										TableList.get(tableNo - 1).setOccupied(false); // set old table false
										r.setTableNo(check);
										System.out.println("Table changed for customer " + r.getCustomerName()
												+ " to table " + check);
										return;
									}
								}
							}

						}
					}
				}

			}

		}

	}

	/**
	 * Method to check validity of the reservation
	 */
	public void checkReservation() {
		rm.checkReservationValid(false);
	}
	
	/**
	 * Method to get the tables with sufficient capacity
	 * @param pax   the pax used to find the tables with sufficient capacity
	 * @return      the table numbers that have sufficient capacity
	 */
	private int getTableNo(int pax) {

		ArrayList<Table> availableTables = tm.getTableOfPaxSize(pax);
		if (availableTables.size() == 0) {
			System.out.println("Sorry, no tables available!");
			return -1;
		} else {
			System.out.println("Please choose from the following tables with sufficient capacity");
			int index = 1;
			for (Table table : availableTables) {
				System.out.println(index + " : Table " + table.getTableNo());
				index++;
			}
			System.out.println("Please enter table choice ");
			int tableNo = availableTables.get(Integer.parseInt(sc.nextLine()) - 1).getTableNo();
			return tableNo;
		}
	}
	
	/**
	 * Method to vacate the table once invoice has been paid
	 * @param tableNo  the table number the customer dined at
	 */
	public void vacateTable(int tableNo) {
	    for (Table t: tm.getTableList()) {
	      if (t.getTableNo() == tableNo) {
	        t.setOccupied(false);
	      }
	    }
	  }
}
