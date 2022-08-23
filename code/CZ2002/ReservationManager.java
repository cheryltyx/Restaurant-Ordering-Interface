package CZ2002;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class is used to keep track of the ArrayList containing all the
 * reservations and also contain functions to manage reservations
 * 
 * @author Li Han
 * @version 1.0
 * @since 2021-11-13
 */
public class ReservationManager {
	/**
	 * ArrayList of all reservations for the restaurant
	*/
	private ArrayList<Reservation> currentReservations = new ArrayList<Reservation>();
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Method to add reservations to the ArrayList, currentReservations
	 * @param r   reservation to be added to the ArrayList
	 */
	public void addReservation(Reservation r) {
		currentReservations.add(r);
	}

	/**
	 * Method to check if the reservation is still valid. If invalid, remove reservation.
	 * @param remove   boolean to indicate if reservation should be removed after checking
	 * @return         the reservation that has been removed or removed the last reservation as a dummy. If no reservations in ArrayList, return null.
	 */
	public Reservation checkReservationValid(boolean remove) {
		System.out.println("Please enter contact number used in reservation");
		int contactNo = Integer.parseInt(sc.nextLine());

		if (currentReservations.size() == 0) {
			System.out.println("There are no reservations made by anyone at the moment!");
			return null;
		}

		for (Reservation reservation : currentReservations) {
			if (reservation.getContact() == contactNo) {
				// Valid
				if (remove) {
					removeReservation(reservation);
					return reservation;
				}

				else {
					// if date equal
					LocalDateTime reservationDate = reservation.getDate();
					if (LocalDateTime.now().toLocalDate().equals(reservationDate.toLocalDate())
							&& LocalDateTime.now().getHour() >= reservationDate.getHour() + 1) {

						System.out.println("reservation period over after 1 hour!");
						removeReservation(reservation);
						return reservation;

					}
					// if year over or month over
					else if (LocalDateTime.now().toLocalDate().getYear() > reservationDate.toLocalDate().getYear()
							|| LocalDateTime.now().toLocalDate().getMonthValue() > reservationDate.toLocalDate()
									.getMonthValue()) {
						System.out.println("reservation period already over!");
						removeReservation(reservation);
						return reservation;
					}

					// if date over
					else if ((LocalDateTime.now().toLocalDate().getYear() == reservationDate.toLocalDate().getYear())
							&& LocalDateTime.now().toLocalDate().getMonthValue() == reservationDate.toLocalDate()
									.getMonthValue()
							&& LocalDateTime.now().toLocalDate().getDayOfMonth() > reservationDate.toLocalDate()
									.getDayOfMonth()) {
						System.out.println("reservation period already over!");
						removeReservation(reservation);
						return reservation;
					}

					else { // different date from current time , example reservation date is one day after
							// current date or time not up yet
						System.out.println("reservation still ongoing on " + reservation.getDateTime()
								+ " for customer " + reservation.getCustomerName());
						return reservation;
					}
				}

			}
		}
		// if theres reservations in reservations array list but doesn't belong to that
		// contact number
		System.out.println("No such reservation!");
		return currentReservations.get(currentReservations.size() - 1); // dummy references to reference that end of
		// arrayList ( no such reservations exist)

	}

	/**
	 * Method to remove reservations from the ArrayList, currentReservations
	 * @param r  the reservation to be removed
	 */
	public void removeReservation(Reservation r) {
		currentReservations.remove(r);
		System.out.println(
				"Reservation made by customer " + r.getCustomerName() + " at time " + r.getDateTime() + " cancelled!");
		System.out.println("Reservation for table " + r.getTableNo() + " removed!");
	}

	/**
	 * Method to update the reservation time of the reservation found in the ArrayList
	 * @param contactNo the contact number used to identify the respective reservation
	 */
	public void updateReservation(int contactNo) {

		for (Reservation r : currentReservations) {
			if (r.getContact() == contactNo) {
			
				System.out.println("Please enter new date of reservation in this format: YYYY-MM-DD (e.g 2021-10-23)");
				String dateTime = sc.next();
				System.out.println("Please enter new time of reservation in this format: HH:mm (e.g 22:52)");
				String time = sc.next();
				String date = dateTime + "T" + time;
				LocalDateTime dt = LocalDateTime.parse(date); // raw format of type LocalDateTime
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd YYYY, HH:mm"); // convert to new format
				String bookingTime = dtf.format(dt); // format into the new format of type String (so that can print)
				// Check to see if got other clashing reservations on this timing
				for (Reservation r2 : currentReservations) {
					if (r != r2) {
						if (r2.getDate().toLocalDate().equals(dt.toLocalDate())) {// if date same
							if (r2.getDate().getHour() == dt.getHour()) { // if hour same
								System.out.println(
										"Please choose other time slots! current reservation time slot taken by existing customer!");
								return;
							}
						}
					}
				}

				r.setDate(dt);
				r.setDateTime(bookingTime);
				System.out.println("Reservation updated! Updated reservation for customer " + r.getCustomerName()
						+ " at " + r.getDateTime());
			}
		}

	}

	/**
	 * Method to return an ArrayList of reservations
	 * @return the ArrayList of reservations
	 */
	public ArrayList<Reservation> getReservationList() {
		return this.currentReservations;
	}
	
	/**
	 * Method to print all the current reservations being made 
	 */
	public void printReservationList() {
		if (currentReservations.size() == 0) {
			System.out.println("No reservations at the moment!");
			return;
		}
		for (int i = 0; i < currentReservations.size(); i++) {
			Reservation r = currentReservations.get(i);
			System.out.println("Reservation made by customer " + r.getCustomerName() + " on " + r.getDateTime()
					+ " at table " + r.getTableNo() + "\n");
		}
	}

}
