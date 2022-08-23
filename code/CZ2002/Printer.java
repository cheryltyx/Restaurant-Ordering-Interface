package CZ2002;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * the Printer class is responsible for printing out invoices,orders and sale revenue
 * @author Hannah V,Shi Yuan,cheryl
 * @version 1.0
 * @since 2021-11-13
 *
 */
public class Printer {
  /**
   * gst is the additional tax on the order, includes tax + service charge 
   * (which in this case, tax is 7% and service charge is 10%)
   */
  private double gst;
  /**
   * orderDatabase is the database of all the orders
   * it would be used when accessing a specific order for printing
   */
  private OrderDatabase orderDatabase;
  Scanner sc = new Scanner(System.in);
  /**
   * invoiceManager manages all the invoices
   * it would be used when accessing a specific order for printing
   */
  private InvoiceManager invoiceManager;
  /**
   * this is the constructor for the Printer using the order database and invoice manager
   * @param orderDatabase database of all the orders
   * @param invoiceManager manager of all the invoices
   */
  public Printer(OrderDatabase orderDatabase, InvoiceManager invoiceManager){
    this.gst = 0.17;
    this.orderDatabase = orderDatabase;
    this.invoiceManager = invoiceManager;
  }
  /**
   * prints the final receipt of the customer with all the ordered AlaCarte Items and Promotional Sets and its quantities
   * and the final price the customer has to pay, taking into account membership discount and gst
   * @param orderId the id of the order that you wish to print the invoice of
   */
  public void printInvoice(int orderId) {
   
    Order order = orderDatabase.getOrder(orderId);
    System.out.println("------------------------------");
    System.out.println(".    Sidecar Bar & Grills.    ");
    System.out.println("      577 College Street.     ");
    System.out.println("       Toranto, Ontario       ");
    System.out.println("------------------------------");
    printOrder(order);
    System.out.println("------------------------------");
    if(order.getCustomer().getCustStatus()){
    System.out.printf ("         Sub-total: %11.2f\n",order.getTotalPrice());
    System.out.printf ("               Gst: %11.2f\n",order.getTotalPrice()*gst);
    System.out.printf ("        Membership: %11.2f\n",order.getTotalPrice()*-0.1);
    System.out.println("------------------------------");
    System.out.printf ("     Total:     %15.2f\n",order.getTotalPrice()*.9*(gst+1));
    }
    else{	
    System.out.printf ("          Sub-total %11.2f\n",order.getTotalPrice());
    System.out.printf ("               Gst: %11.2f\n",order.getTotalPrice()*gst);
    System.out.println("------------------------------");
    System.out.printf ("     Total:     %15.2f\n",order.getTotalPrice()*(gst+1));
    }
    System.out.println("------------------------------");
    System.out.println("------------------------------");
    



    return;
  }
  /**
   * prints the current AlaCarte items and Promotional Sets and its respective quantities that the customer of that order has ordered
   * @param order the order object that you wish to print the items of
   */
  public void printOrder(Order order){

    ArrayList<AlaCarte> alaCarteList = order.getOrderedAlaCarteItems();
    ArrayList<PromotionalSet> promotionalSetList = order.getOrderedPromoSets();
    AlaCarte alaCarteTemp;
    PromotionalSet promotionalSetTemp;
    for (int i = 0; i < alaCarteList.size(); i++){
      for(int j = i; j > 0; j--){
        if(alaCarteList.get(j).getAlaCarteId() < alaCarteList.get(j-1).getAlaCarteId()){
          alaCarteTemp = alaCarteList.get(j);
          alaCarteList.set(j, alaCarteList.get(j-1));
          alaCarteList.set(j-1, alaCarteTemp);
        }
        else break;
      }
    }

    for (int i = 0; i < promotionalSetList.size(); i++){
      for(int j = i; j > 0; j--){
        if(promotionalSetList.get(j).getSetId() > promotionalSetList.get(j-1).getSetId()){
          promotionalSetTemp = promotionalSetList.get(j);
          promotionalSetList.set(j, promotionalSetList.get(j-1));
          promotionalSetList.set(j-1, promotionalSetTemp);
        }
        else break;
      }
    }

    int quantity = 1;
    int tempId = 0;
    
    if (alaCarteList.size() != 0) {
      alaCarteTemp = alaCarteList.get(0);
      if (alaCarteTemp != null) {      
        tempId = alaCarteTemp.getAlaCarteId();
      }
      for (int i = 1; i < alaCarteList.size(); i++){
        if (tempId == alaCarteList.get(i).getAlaCarteId()){
          quantity++;
          continue;
        }
        else {
          System.out.printf("%-3d %-20s %7.2f\n", quantity, alaCarteTemp.getName(), alaCarteTemp.getPrice()*quantity);
          quantity = 1;
          alaCarteTemp = alaCarteList.get(i);
          tempId = alaCarteTemp.getAlaCarteId();
        }
      }
      System.out.printf("%-3d %-20s %7.2f\n", quantity, alaCarteTemp.getName(), alaCarteTemp.getPrice()*quantity);
    }
    
    quantity = 1;
    tempId = 0;
    
    if(promotionalSetList.size() != 0) {
    
      promotionalSetTemp = promotionalSetList.get(0);
      if (promotionalSetTemp != null) {     
        tempId = promotionalSetTemp.getSetId();
      }
      for (int i = 1; i < promotionalSetList.size(); i++){
        if (tempId == promotionalSetList.get(i).getSetId()){
          quantity++;
          continue;
        }
        else {
          System.out.printf("%-3d %-20s %7.2f\n", quantity, promotionalSetTemp.getSetName(), promotionalSetTemp.getPrice()*quantity);
          quantity = 1;
          promotionalSetTemp = promotionalSetList.get(i);
          tempId = promotionalSetTemp.getSetId();
        }
      }
      System.out.printf("%-3d %-20s %7.2f\n", quantity, promotionalSetTemp.getSetName(), promotionalSetTemp.getPrice()*quantity);

    }
  }
  /**
   * prints the saleRevenue of the particular day that the staff requests for
   * prints out the individual alaCarte items and promotional items and its respective quantity for the day
   * and also prints out the total amount of revenue the restaurant has accumulated for the day
   */
  public void printSaleRevenue() {
    int day,month,year;
    double totalRevenue = 0;
    ArrayList<AlaCarte> alaCarteList = new ArrayList<AlaCarte>();
    ArrayList<PromotionalSet> promotionalSetList = new ArrayList<PromotionalSet>();

    System.out.println("Enter Year:");
    year = sc.nextInt();
    System.out.println("Enter Month:");
    month = sc.nextInt();
    System.out.println("Enter Day:");
    day = sc.nextInt();
    ArrayList<Invoice> invoiceList = invoiceManager.getInvoiceList();
    for(Invoice invoice: invoiceList){
      if(year == invoice.getYear()){
        if(month == invoice.getMonth()){
          if(day == invoice.getDay()){
            totalRevenue = totalRevenue + orderDatabase.getOrder(invoice.getInvoiceID()).calculateFinalPrice();
            alaCarteList.addAll(orderDatabase.getOrder(invoice.getInvoiceID()).getOrderedAlaCarteItems());
            promotionalSetList.addAll(orderDatabase.getOrder(invoice.getInvoiceID()).getOrderedPromoSets());
          }
        }
      }
    }
    System.out.println("------------------------------");
    System.out.printf("%d/%d/%d\n",month,day,year);
    System.out.println("------------------------------");

    AlaCarte alaCarteTemp;
    PromotionalSet promotionalSetTemp;
    for (int i = 0; i < alaCarteList.size(); i++){
      for(int j = i; j > 0; j--){
        if(alaCarteList.get(j).getAlaCarteId() < alaCarteList.get(j-1).getAlaCarteId()){
          alaCarteTemp = alaCarteList.get(j);
          alaCarteList.set(j, alaCarteList.get(j-1));
          alaCarteList.set(j-1, alaCarteTemp);
        }
        else break;
      }
    }

    for (int i = 0; i < promotionalSetList.size(); i++){
      for(int j = i; j > 0; j--){
        if(promotionalSetList.get(j).getSetId() > promotionalSetList.get(j-1).getSetId()){
          promotionalSetTemp = promotionalSetList.get(j);
          promotionalSetList.set(j, promotionalSetList.get(j-1));
          promotionalSetList.set(j-1, promotionalSetTemp);
        }
        else break;
      }
    }

    int quantity = 1;
    int tempId = 0;
    
    if (alaCarteList.size() != 0) {
      alaCarteTemp = alaCarteList.get(0);
      if (alaCarteTemp != null) {      
        tempId = alaCarteTemp.getAlaCarteId();
      }
      for (int i = 1; i < alaCarteList.size(); i++){
        if (tempId == alaCarteList.get(i).getAlaCarteId()){
          quantity++;
          continue;
        }
        else {
          System.out.printf("%-3d %-20s\n", quantity, alaCarteTemp.getName());
          quantity = 1;
          alaCarteTemp = alaCarteList.get(i);
          tempId = alaCarteTemp.getAlaCarteId();
        }
      }
      System.out.printf("%-3d %-20s\n", quantity, alaCarteTemp.getName());
    }

    quantity = 1;
    tempId = 0;
    
    if(promotionalSetList.size() != 0) {
    
      promotionalSetTemp = promotionalSetList.get(0);
      if (promotionalSetTemp != null) {     
        tempId = promotionalSetTemp.getSetId();
      }
      for (int i = 1; i < promotionalSetList.size(); i++){
        if (tempId == promotionalSetList.get(i).getSetId()){
          quantity++;
          continue;
        }
        else {
          System.out.printf("%-3d %-20s\n", quantity, promotionalSetTemp.getSetName());
          quantity = 1;
          promotionalSetTemp = promotionalSetList.get(i);
          tempId = promotionalSetTemp.getSetId();
        }
      }
      System.out.printf("%-3d %-20s\n", quantity, promotionalSetTemp.getSetName());

    }
    System.out.println("------------------------------");
    System.out.printf("Total Sales Revenue %.2f\n", totalRevenue);
    System.out.println("------------------------------");

    
    return;
  }
}
