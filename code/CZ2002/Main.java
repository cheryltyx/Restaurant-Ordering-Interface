package CZ2002;

import java.awt.Image;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Main application class to start up the Restaurant Reservation Point of Sales
 * (RRPSS) application. Provides an interface for restaurant staff to perform
 * all the necessary functions to manage the restaurant.
 * 
 * @author lihan, cheryl, hannah, shiyuan
 * @version 1.0
 * @since 2021-11-13
 */
class Main {
	public static void main(String[] args) {

		int choice;
		Scanner sc = new Scanner(System.in);

		CustomerDatabase customerDatabase = new CustomerDatabase();
		System.out.println();

		Menu menu = new Menu();
		System.out.println();

		OrderDatabase orderDatabase = new OrderDatabase(menu);

		InvoiceManager im = new InvoiceManager();

		Printer printer = new Printer(orderDatabase, im);

		TableManager tableManager = new TableManager();
		System.out.println();

		ReservationManager reservationManager = new ReservationManager();

		ReservationMaker reservationMaker = new ReservationMaker(reservationManager, tableManager);

		System.out.println("Enter staff name: ");
		String staffName = sc.nextLine();
		System.out.println("Enter staff gender: M/F");
		char staffGender = sc.nextLine().charAt(0);
		System.out.println("Enter staff ID");
		int staffID = Integer.parseInt(sc.nextLine());
		// sc.nextLine();
		System.out.println("Enter staff position: ");
		String staffPos = sc.nextLine();

		Staff staff = new Staff(staffName, staffGender, staffID, staffPos);
		staff.getStaffInformation();

		do {
			System.out.println("1: Menu Item");
			System.out.println("2: Order");
			System.out.println("3: Reservation");
			System.out.println("4: Check table availability");
			System.out.println("5: Print Order Invoice");
			System.out.println("6: Print Sale Revenue Report");
			System.out.println("7: Customer");
			System.out.println("0: Exit");
			System.out.print("Enter the number of your choice:");
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1: // Create item
				System.out.println("1: Create Menu Item");
				System.out.println("2: Update Menu Item");
				System.out.println("3: Remove Menu Item");
				System.out.println("4: Print Menu Item");
				int type = Integer.parseInt(sc.nextLine());
				switch (type) {
				case 1:
					System.out.println("Please Choose");
					System.out.println("1. Ala Carte item");
					System.out.println("2. Promotional Set");
					type = Integer.parseInt(sc.nextLine());
					if (type == 1)
						menu.createMenuItem();
					else if (type == 2)
						menu.createPromotionalSetItem();
					break;
				case 2:
					System.out.println("Please Choose");
					System.out.println("1. Ala Carte item");
					System.out.println("2. Promotional Set");
					type = Integer.parseInt(sc.nextLine());
					if (type == 1)
						menu.updateMenuItem();
					else if (type == 2)
						menu.updatePromotionalSetItem();
					break;
				case 3:
					System.out.println("Please Choose");
					System.out.println("1. Ala Carte item");
					System.out.println("2. Promotional Set");
					type = Integer.parseInt(sc.nextLine());
					if (type == 1)
						menu.removeMenuItem();
					else if (type == 2)
						menu.removePromotionalSetItem();
					break;
				case 4:
					System.out.println("Please Choose");
					System.out.println("1. Ala Carte item");
					System.out.println("2. Promotional Set");
					type = Integer.parseInt(sc.nextLine());
					if (type == 1)
						menu.printAlaCarteMenu();
					else if (type == 2)
						menu.printPromotionalSetMenu();
					break;

				}
				break;

			case 2:
				System.out.println("1: Create Order");
				System.out.println("2: View Order");
				System.out.println("3: Add Order Item ");
				System.out.println("4: Remove Order Item");
				type = Integer.parseInt(sc.nextLine());
				switch (type) {
				case 1:
					System.out.println("Please enter Customer Phone Number");
					int custContact = Integer.parseInt(sc.nextLine());
					Customer customer = customerDatabase.getCustomer(custContact);
					if (customer != null) {
						int OrderID = orderDatabase.createOrder(customer, sc);
						im.createInvoice();
					} else {
						System.out.println("No such customer exist");
					}
					break;
				case 2: // view order
					System.out.println("Please enter Order ID:");
					int OrderID = Integer.parseInt(sc.nextLine());
					Order order = orderDatabase.getOrder(OrderID);
					if (order != null) {
						printer.printOrder(order);
					} else {
						System.out.println("Invalid Order ID!");
					}
					break;
				case 3:
					System.out.println("Please enter Order ID:");
					OrderID = Integer.parseInt(sc.nextLine());
					System.out.println("Please Choose");
					System.out.println("1. Ala Carte item");
					System.out.println("2. Promotional Set");
					int type5 = Integer.parseInt(sc.nextLine());
					if (type5 == 1)
						orderDatabase.addAlaCarteToOrder(OrderID, sc);
					else if (type5 == 2)
						orderDatabase.addPromoSetsToOrder(OrderID, sc);
					break;
				case 4:
					System.out.println("Please enter Order ID:");
					OrderID = Integer.parseInt(sc.nextLine());
					System.out.println("Please Choose");
					System.out.println("1. Ala Carte item");
					System.out.println("2. Promotional Set");
					type = Integer.parseInt(sc.nextLine());
					if (type == 1)
						orderDatabase.removeAlaCarteToOrder(OrderID, sc);
					else if (type == 2)
						orderDatabase.removePromoSetsToOrder(OrderID, sc);
					break;

				}
				break;
			case 3:
				System.out.println("1: Create Reservation Booking");
				System.out.println("2: Check Reservation");
				System.out.println("3: Remove Reservation");
				System.out.println("4: Update Reservation");
				System.out.println("5: Get Reservation List");

				type = Integer.parseInt(sc.nextLine());
				switch (type) {
				case 1: // Create reservation booking
					reservationMaker.createReservation();
					// add new customer
					String custName;
					int contact;
					boolean exist = false;
					// size of reservation list

					int size = reservationManager.getReservationList().size();
					custName = reservationManager.getReservationList().get(size - 1).getCustomerName();
					contact = reservationManager.getReservationList().get(size - 1).getContact();

					for (Customer c : customerDatabase.getCustomerList()) {
						if (c.getCustContact() == contact) {
							System.out.println("Customer already exist in database");
							exist = true;
						}
					}
					if (!exist) {
						Customer cust = new Customer(custName, contact, false,
								customerDatabase.getCustomerList().size() + 1);
						customerDatabase.getCustomerList().add(cust);
						System.out.println("Customer added to database");
					}

					break;
				case 2: // check Reservation
					reservationMaker.checkReservation();
					break;
				case 3: // Remove reservation
					reservationMaker.cancelReservation();
					break;
				case 4: // update Reservation
					reservationMaker.updateReservation();
					break;
				case 5:
					reservationManager.printReservationList();
				}
				break;
			case 4: // check table availability
				tableManager.checkTable();
				break;
			case 5:

				System.out.println("Enter Order ID to Print");
				int OrderId = Integer.parseInt(sc.nextLine());
				Order order = orderDatabase.getOrder(OrderId);
				if (order != null) {
					printer.printInvoice(OrderId);
					reservationMaker.vacateTable(order.getTableNumber());
					if (!order.getCustomer().getCustStatus()) {
						System.out.println("Would you like to be a member: true/false");
						boolean input = Boolean.parseBoolean(sc.nextLine());
						order.getCustomer().setCustStatus(input);
					}
				} else {
					System.out.println("Invalid Order ID!");

				}

				break;
			case 6:
				printer.printSaleRevenue();
				break;
			case 7:
				System.out.println("1: Add Customer");
				System.out.println("2: Remove Customer");
				System.out.println("3: Print Customer");
				type = Integer.parseInt(sc.nextLine());
				switch (type) {
				case 1: // Add Customer
					customerDatabase.addCustomer();
					break;
				case 2: // Remove Customer
					customerDatabase.removeCustomer();
					break;
				case 3: // Print Customer
					customerDatabase.printCustomer();
					break;
				}
				break;

			}

		} while (choice != 0);
		menu.updateCSV();
		customerDatabase.updateCSV();
	}
}