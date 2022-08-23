package CZ2002;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class is used to hold entire menu  from alaCarte items as well as promotional sets
 * @author Shi Yuan 
 * @version 1.0
 * @since 2021-11-13
 *
 */
public class Menu {
	/**
	 * ArrayList holding the alaCarte items
	 */
	private ArrayList<AlaCarte> alaCarteList;
	/**
	 * ArrayList holding the promotional set
	 */
	private ArrayList<PromotionalSet> promotionalSetList;
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Creating two ArrayList, alaCarteList and promotionalSetList containing alaCarte items and promotional set respectively
	 * generate a list of menu for customers to see
	 */
	public Menu() {
		alaCarteList = new ArrayList<AlaCarte>();
		promotionalSetList = new ArrayList<PromotionalSet>();
		generateMenu();

	}
	/**
	 * this method is to create an alaCarte menu item
	 */
	public void createMenuItem() {
		String name, description, type;
		double price;
		int alaCarteId;

		System.out.println("Enter Item Name:");
		name = sc.nextLine();
		System.out.println("Enter Item Price:");
		price = Double.parseDouble(sc.nextLine());;
		System.out.println("Enter Item Description:");
		description = sc.nextLine();
		System.out.println("Enter Item Type:");
		type = sc.nextLine();

		if (alaCarteList.size() == 0)
			alaCarteId = 1;
		else
			alaCarteId = alaCarteList.get(alaCarteList.size() - 1).getAlaCarteId() + 1;
		AlaCarte alaCarteTemp = new AlaCarte(alaCarteId, name, price, description, type);

		alaCarteList.add(alaCarteTemp);
		System.out.printf("Item Added.\n");
	}
	/**
	 * this method is to update an alaCarte menu item
	 */
	public void updateMenuItem() {

		String name, description, type;
		double price;
		int index, alaCarteId;

		String oldName, oldDes, oldType;
		double oldPrice;

		// Check if exists
		System.out.println("Enter Item Id:");
		alaCarteId = Integer.parseInt(sc.nextLine());;
		index = findMenuItem(alaCarteId);
		if (index == -1) {
			System.out.printf("Unable to find Dish.\n");
			return;
		}
		// Name
		oldName = alaCarteList.get(index).getName();
		System.out.println("Enter new Item Name:");
		System.out.println("Type '-' to skip");
		name = sc.nextLine();
		if (name.equals("-")) {
			alaCarteList.get(index).updateName(oldName);
		} else {
			alaCarteList.get(index).updateName(name);
			System.out.printf("Name updated.\n");
		}

		// Price
		oldPrice = alaCarteList.get(index).getPrice();
		System.out.println("Enter new Item Price:");
		System.out.println("Type -1 to skip");
		price = Float.parseFloat(sc.nextLine());
		if (price != -1) {
			alaCarteList.get(index).updatePrice(price);
			System.out.printf("Price updated.\n");
		} else {
			alaCarteList.get(index).updatePrice(oldPrice);
		}

		// Description
		oldDes = alaCarteList.get(index).getDescription();
		System.out.println("Enter new Item Description:");
		System.out.println("Type '-' to skip");
		description = sc.nextLine();
		if (description.equals("-")) {
			alaCarteList.get(index).updateDescription(oldDes);
		} else {
			alaCarteList.get(index).updateDescription(description);
			System.out.printf("Description updated.\n");
		}

		// Type
		oldType = alaCarteList.get(index).getType();
		System.out.println("Enter new Item Type:");
		System.out.println("Type '-' to skip");
		type = sc.nextLine();
		if (type.equals("-")) {
			alaCarteList.get(index).updateType(oldType);
		} else {
			alaCarteList.get(index).updateType(type);
			System.out.printf("Type updated.\n");
		}

	}
	/**
	 * this method is to remove an alaCarte menu item
	 */
	public void removeMenuItem() {
		int index, alaCarteId;
		System.out.println("Enter Item Id of item to remove:");
		alaCarteId = Integer.parseInt(sc.nextLine());;
		index = findMenuItem(alaCarteId);
		if (index == -1) {
			System.out.printf("Unable to find Dish.\n");
			return;
		} else {
			alaCarteList.remove(index);
			System.out.println("Item removed!");
		}

	}
	/**
	 * this method is to find if the alaCarte menu item exist in the ArrayList consisting alaCarte items
	 * @param alaCarteId  the unique identifier of each alaCarte menu item
	 * @return the unique identifier of the alaCarte menu item if it exists, else return -1
	 */
	public int findMenuItem(int alaCarteId) { // return -1 if unable to find

		int alaCarteListSize = alaCarteList.size();
		for (int index = 0; index < alaCarteListSize; index++) {
			if (alaCarteList.get(index).getAlaCarteId() == alaCarteId)
				return index;
		}
		return -1;
	}

	/**
	 * this method is to create the promotional set
	 */
	public void createPromotionalSetItem() {
		ArrayList<AlaCarte> alaCarteListTemp = new ArrayList<AlaCarte>();
		AlaCarte alaCarteTemp;
		int index, setIdTemp, alaCarteId, count;
		double price;
		String description, setName;

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number of item to add to Promotional Set");
		count = Integer.parseInt(sc.nextLine());;
		for (int x = 0; x < count; x++) {
			System.out.println("Enter Item Id:");
			alaCarteId = Integer.parseInt(sc.nextLine());;
			index = findMenuItem(alaCarteId);
			if (index == -1) {
				System.out.printf("Item not in Menu.\n");
				continue;
			}
			alaCarteTemp = alaCarteList.get(index);
			alaCarteListTemp.add(alaCarteTemp);
		}
		System.out.println("Enter Promotional Set Name");
		setName = sc.nextLine();
		System.out.println("Enter Promotional Set Price:");
		price = Double.parseDouble(sc.nextLine());
		System.out.println("Enter Promotional Set Description:");
		description = sc.nextLine();

		if (promotionalSetList.size() == 0)
			setIdTemp = 1;
		else
			setIdTemp = promotionalSetList.get(promotionalSetList.size() - 1).getSetId() + 1;
		PromotionalSet promotionalSetTemp = new PromotionalSet(setIdTemp, setName, alaCarteListTemp, price,
				description);

		promotionalSetList.add(promotionalSetTemp);

		System.out.printf("Item Added.\n");
		return;
	}
	/**
	 * this method is to update the promotional set
	 */
	public void updatePromotionalSetItem() {
		ArrayList<AlaCarte> alaCarteListTemp = new ArrayList<AlaCarte>();
		String name, description;
		int index, index2, setId, alaCarteId;
		double price;

		Scanner sc = new Scanner(System.in);

		// Description
		System.out.println("Enter Promotional Set Id:");
		setId = Integer.parseInt(sc.nextLine());;
		index = findPromotionalSetItem(setId);
		if (index == -1) {
			System.out.printf("Unable to find Set.\n");
			return;
		}
		// setName
		System.out.println("Enter set name?");
		System.out.println("Enter -1 to skip");
		String change = sc.nextLine();
		if (!change.equals("-1")) {
			promotionalSetList.get(index).setSetName(change);
		}

		// alaCarteList
		System.out.println("Enter Number of item to update to Promotional Set");
		System.out.println("Enter -1 to skip");
		int count = Integer.parseInt(sc.nextLine());;
		if (count != -1) {
			for (int x = 0; x < count; x++) {
				System.out.printf("Enter Item Id:\n");
				alaCarteId = Integer.parseInt(sc.nextLine());;
				index2 = findMenuItem(alaCarteId);
				if (index2 == -1) {
					System.out.printf("Item not in Menu.\n");
					continue;
				}
				AlaCarte alaCarte = alaCarteList.get(index2);
				alaCarteListTemp.add(alaCarte);
			}
			promotionalSetList.get(index).updateAlaCarteList(alaCarteListTemp);
			System.out.printf("Set updated.\n");
		}

		// Price
		System.out.println("Enter Item Price:");
		System.out.println("Type -1 to skip");
		price = Double.parseDouble(sc.nextLine());;
		if (price != -1) {
			promotionalSetList.get(index).updatePrice(price);
			System.out.printf("Price updated.\n");
		}

		return;
	}
	/**
	 * this method is to remove the promotional set
	 */
	public void removePromotionalSetItem() {

		int setId, index;
		System.out.println("Enter Promotional Set Id:");
		setId = Integer.parseInt(sc.nextLine());;
		index = findPromotionalSetItem(setId);
		if (index == -1) {
			System.out.printf("Unable to find Set.\n");
			return;
		} else {
			promotionalSetList.remove(index);
			System.out.printf("Set removed.\n");
		}

		return;
	}
	/**
	 * this method is to find if the promotional set exist in the ArrayList consisting of promotional sets
	 * @param setId  unique identifier for the promotional set
	 * @return the unique identifier of the promotional set if it exists, else return -1
	 */
	public int findPromotionalSetItem(int setId) {
		int promotionalSetListSize = promotionalSetList.size();
		for (int index = 0; index < promotionalSetListSize; index++) {
			if (promotionalSetList.get(index).getSetId() == setId)
				return index;
		}
		return -1;
	}
	/**
	 * this method prints the alaCarte menu
	 */
	public void printAlaCarteMenu() {
		for (int x = 0; x < alaCarteList.size(); x++) {
			System.out.printf("AlaCarteId: %-3d Name: %-30s Price: %5.2f    Description: %-15s Type: %-9s\n",
					alaCarteList.get(x).getAlaCarteId(), alaCarteList.get(x).getName(), alaCarteList.get(x).getPrice(),
					alaCarteList.get(x).getDescription(), alaCarteList.get(x).getType());
		}
		return;
	}
	/**
	 * this method prints the promotional set menu
	 */
	public void printPromotionalSetMenu() {
		ArrayList<AlaCarte> alaCarteListTemp;
		for (int x = 0; x < promotionalSetList.size(); x++) {
			alaCarteListTemp = promotionalSetList.get(x).getAlaCarteList();
			System.out.printf("SetId: %-3d ", promotionalSetList.get(x).getSetId());
			System.out.printf("setName: %-6s ", promotionalSetList.get(x).getSetName());
			System.out.printf("Price: %5.2f ", promotionalSetList.get(x).getPrice());
			System.out.printf("Description: %-15s \n", promotionalSetList.get(x).getDescription());
			System.out.printf("Includes: ");
			for (int y = 0; y < alaCarteListTemp.size(); y++) {
				
				if( y == alaCarteListTemp.size()-1) {
					System.out.printf("%s\n ", alaCarteListTemp.get(y).getName());
				}
				else {
					System.out.printf("%s, ", alaCarteListTemp.get(y).getName());
				}
			}
			System.out.println();

		}
		return;
	}
	/**
	 * this method gets the alaCarteItem
	 * @param MenuID unique identifier of the alaCarte item
	 * @return alaCarte object
	 */
	public AlaCarte getAlaCarteItem(int MenuID) {
		for (AlaCarte a : alaCarteList) {
			if (a.getAlaCarteId() == MenuID) {
				return a;
			}
		}
		return null;
	}
	/**
	 * this method gets the promotional set
	 * @param MenuID unique identifier of the promotional set
	 * @return promotional set object
	 */
	public PromotionalSet getPromotionalItem(int MenuID) {
		for (PromotionalSet p : promotionalSetList) {
			if (p.getSetId() == MenuID) {
				return p;
			}
		}
		return null;
	}

	/**
	 * generate a list of alaCarte menu
	 */
	private void generateMenu() {

		String line = "";
		String path = "src\\menu.csv";

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			int i = 1;
			while ((line = br.readLine()) != null) {
				if (i == 1) { // skip the first line
					i++;
					continue;
				}
				String[] values = line.split(",");

				// int alaCarteId, String name, double price, String description, String type
				AlaCarte alaC = new AlaCarte(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]),
						values[3], values[4]);
				alaCarteList.add(alaC);
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		printAlaCarteMenu();
	}

	/**
	 * updating the alaCarte menu
	 */
	public void updateCSV() {
		// write back to csv file//
		String filepath = "src\\menu.csv";
		AlaCarte data;

		try {
			FileWriter fw = new FileWriter(filepath, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("alaCarteId" + "," + "name" + "," + "price" + "," + "description" + "," + "type");

			for (int row = 0; row < alaCarteList.size(); row++) {
				data = alaCarteList.get(row);
				pw.println(data.getAlaCarteId() + "," + data.getName() + "," + data.getPrice() + ","
						+ data.getDescription() + "," + data.getType());
			}

			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.printf("menu up to date!\n");
			return;
		}

		System.out.printf("menu up to date!\n");

	}

}
