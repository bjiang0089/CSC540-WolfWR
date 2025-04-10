package CSC540.WolfWR.views;

import CSC540.WolfWR.WolfWRApp;
import CSC540.WolfWR.models.*;
import CSC540.WolfWR.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code CustomerView} class provides the customer interface for interacting with the store.
 * This includes viewing and selecting a member, making a purchase, selecting stores, and adding merchandise to the cart.
 * The customer can also proceed to checkout and complete their purchase.
 * <p>
 * This class uses services such as {@link StoreService}, {@link MerchandiseService}, {@link TransactionService},
 * and {@link MemberService} to interact with the business logic and persist data.
 * </p>
 */
@Transactional
@Component
public class CustomerView {

    /**
     * The service used for performing operations on {@link Store} entities.
     */
    @Autowired
    private DataSource source;

    @Autowired
    private StoreService storeServ;

    /**
     * The service used for performing operations on {@link Merchandise} entities.
     */
    @Autowired
    private MerchandiseService merchServ;

    /**
     * The service used for performing operations on {@link Transaction} entities.
     */
    @Autowired
    private TransactionService transServ;

    /**
     * The service used for performing operations on {@link Member} entities.
     */
    @Autowired
    private MemberService memberServ;


    @Autowired
    private DiscountService discountServ;



    /**
     * Displays the customer view, prompting the user to sign in by selecting their account and then presenting options 
     * to make a purchase or return to the home page.
     * 
     * @param scan The {@link Scanner} object used to take user input.
     */

    public void view(Scanner scan) {
        List<Member> members = memberServ.findAll();
        System.out.println("Please sign in by selecting the number associated with your account:");
        displayMembers(members);
        String input = null;

        input = scan.nextLine().trim();
        Member activeMember = null;
        try {
            activeMember = members.get( Integer.parseInt(input) - 1 );
        } catch (Exception e) {
            System.out.println("Error Signing In");
            return;
        }

        while (true) {
            System.out.println("\nSelect an action with the number provided:");
            System.out.println("[0] Return to Home Page");
            System.out.println("[1] Make a Purchase");
            System.out.println("[2] View History");
            System.out.print("> ");

            input = scan.nextLine().trim();

            switch (input) {
                case "0":
                    System.out.println("Returning to Home Page. . .\n");
                    return;
                case "1":
                    try {
                        makePurchase(scan, activeMember);
                    } catch (Exception e) {
                        System.out.println("Error while processing transaction.");
                    }

                    break;
                case "2":
                    viewHistory(activeMember);
                    break;
                default:
                    System.out.println("\nUnknown action\n");
            }
        }
    }

    public void viewHistory(Member m) {
        List<Transaction> trans = transServ.getHistoryByCustomer(m);
        int count = 0;
        double total = 0;
        System.out.println();
        for (int i = 0 ; i < trans.size(); i++) {
            Transaction t = trans.get(i);
            System.out.printf("Transaction %4d - Total: $%3.2f - Date: %s\n",
                    t.getTransactionID(), t.getTotalPrice(), t.getPurchaseDate().toString());
            count++;
            total += t.getTotalPrice();
        }
        System.out.printf("You've made %3d purchases with a total of $%4.2f\n\n",
                count, total);
    }

    /**
     * Allows a customer to make a purchase by selecting a store, adding merchandise to the cart, and proceeding to checkout.
     * The purchase is then completed with the total price calculated and the transaction saved.
     * 
     * @param scan The {@link Scanner} object used to take user input.
     * @param active The active {@link Member} making the purchase.
     */
    @Transactional
    public void makePurchase(Scanner scan, Member active) {
        // Store Selection
        Store current = null;
        List<Store> locations = storeServ.findAll();
        System.out.println("\nPlease select a store:");
        listLocations(locations);


    @Transactional
    public void makePurchase(Scanner scan, Member active) throws SQLException {
        Connection conn = null;
        try {
            conn = source.getConnection();
        } catch (Exception e) {
            System.out.println("Error connecting to the database.\n");
            return;
        }
        try  {
            conn.setAutoCommit(false);
            System.out.println("Please enter a date in the format mm-dd-yyyy:");
            System.out.print("> ");

            String input = scan.nextLine().trim();
            LocalDate date = null;
            try {
                date = LocalDate.parse(input, WolfWRApp.timeFormat);
            } catch (Exception e) {
                System.out.println("Invalid Date");
                conn.rollback();
                return;
            }
            // Store Selection
            Store current = null;
            List<Store> locations = storeServ.findAll();
            System.out.println("\nPlease select a store:");
            listLocations(locations);

            try {
                int idx = Integer.parseInt(scan.nextLine().trim());
                current = locations.get(idx - 1);
            } catch (Exception e) {
                System.out.println("Invalid Store\n");
                conn.rollback();
                return;
            }

            Transaction t = getNewTransaction(current, active, date);

            if (t == null) {
                System.out.println("There are no cashiers at this store\n");
                conn.rollback();
                return;
            }

            // Get list of transaction items
            List<Merchandise> product = merchServ.storeInventory(current);
            while (true) {
                System.out.println("Please make a selection:");
                listInventory(product);

                // Item Selection
                try {
                    input = scan.nextLine().trim();

                    if (input.equals("-1")) {
                        System.out.println("Cancelling Transaction. . .\n");
                        conn.rollback();
                        return;
                    } else if (input.equals("0")) {
                        System.out.println("Proceeding to Checkout. . .");
                        break;
                    } else {
                        int idx = Integer.parseInt(input) - 1;
                        Merchandise m = product.get(idx);
                        // Add Merch to cart
                        t.addMerchandise(m);
                        // Buy each Item once
                        product.remove(idx);
                        System.out.printf("%s added to cart\n\n", m.getProductName());
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Number Format Exception\n");
                } catch (Exception e) {
                    System.out.println("\nError reading selection\n");
                    e.getStackTrace();
                    continue;
                }
            }


            // Checkout
            double total = calculateTotal(date, t.getProductList());


            t.setTotalPrice(total);
            transServ.completePurchase(t);
            System.out.printf("\nThe total for you transaction is $%3.2f.\n", total);
            System.out.println("Purchase Complete!\n");
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
        conn.setAutoCommit(true);
    }


    private double calculateTotal(LocalDate date, List<TransactionItem> cart) {

    /**
     * Calculates the total price of the items in the shopping cart.
     * 
     * @param cart The list of {@link TransactionItem} in the cart.
     * @return The total price of the items in the cart.
     */
    private double calculateTotal(List<TransactionItem> cart) {
        double total = 0;
        double price = 0;

        List<Discount> discounts = discountServ.findByDate(date);
        for (TransactionItem m: cart) {
            discounts = discountServ.findByProductIDAndDate(m.getMerch(), date);
            price = m.getMerch().getMarketPrice();
            if (!discounts.isEmpty()) {
                Discount d = discounts.get(0);
                price *= (100 - d.getDiscountPercentage());
                price /= 100;
            }
            total += price;
        }
        return total;
    }

    /**
     * Displays the list of stores available for selection with an index.
     * 
     * @param locs The list of {@link Store} locations to display.
     */
    private void listLocations(List<Store> locs) {
        for(int i = 0; i < locs.size(); i++) {
            Store s = locs.get(i);
            System.out.printf("[%d] %s\n", i + 1, s.getAddress());
        }
        System.out.print("> ");
    }

    /**
     * Displays the available merchandise in a store's inventory with an index.
     * 
     * @param merch The list of {@link Merchandise} available for purchase.
     */
    private void listInventory(List<Merchandise> merch) {

        System.out.println("[-1] Cancel Transaction");
        System.out.println("[0] Checkout\n");
        for (int i = 0; i < merch.size(); i++) {
            Merchandise m = merch.get(i);
            System.out.printf("[%2d] $%3.2f %s\n", i + 1, m.getMarketPrice(),m.getProductName());
        }
        System.out.print("> ");
    }

    /**
     * Displays the list of members with an index for selection.
     * 
     * @param members The list of {@link Member} entities to display.
     */
    private void displayMembers(List<Member> members) {
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            System.out.printf("[%d] %10s %10s. %s Member\n", i + 1, m.getFirstName(), m.getLastName(), m.getMembershipLevel());
        }
        System.out.print("> ");
    }


    @Transactional
    public Transaction getNewTransaction(Store s, Member m, LocalDate date) {

    /**
     * Creates a new transaction for the active member at the selected store.
     * 
     * @param s The {@link Store} where the transaction is taking place.
     * @param m The {@link Member} making the transaction.
     * @return The created {@link Transaction} object, or null if no cashiers are available.
     */
    public Transaction getNewTransaction(Store s, Member m) {
        Transaction t = new Transaction();
        t.setTransactionID( transServ.generateID() );
        t.setStore(s);
        t.setPurchaseDate(date);
        t.setProductList( new ArrayList<TransactionItem>() );
        t.setMember(m);
        List<Staff> cashiers = transServ.findCashier(s);
        if (cashiers.isEmpty()) {
            t.setCashierID(null);
            return t;
        }
        Staff cash = cashiers.get( 0 );

        return t;
    }
}
