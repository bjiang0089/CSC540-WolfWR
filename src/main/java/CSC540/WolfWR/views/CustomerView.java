package CSC540.WolfWR.views;

import CSC540.WolfWR.models.*;
import CSC540.WolfWR.services.MemberService;
import CSC540.WolfWR.services.MerchandiseService;
import CSC540.WolfWR.services.StoreService;
import CSC540.WolfWR.services.TransactionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
            System.out.print("> ");

            input = scan.nextLine().trim();

            switch (input) {
                case "0":
                    System.out.println("Returning to Home Page. . .\n");
                    return;
                case "1":
                    System.out.println("Making Purchase");
                    makePurchase(scan, activeMember);
                    break;
                default:
                    System.out.println("\nUnknown action\n");
            }
        }
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

        try {
            int idx = Integer.parseInt( scan.nextLine().trim() );
            current = locations.get(idx - 1);
        } catch (Exception e) {
            System.out.println("Invalid Store\n");
            return;
        }

        Transaction t = getNewTransaction(current, active);
        if (t == null) {
            System.out.println("There are no cashiers at this store\n");
            return;
        }

        // Get list of transaction items
        List<Merchandise> product = merchServ.storeInventory(current);
        while(true) {
            System.out.println("Please make a selection:");
            listInventory(product);

            // Item Selection
            try {
                String input = scan.nextLine().trim();

                if (input.equals("-1")) {
                    System.out.println("Cancelling Transaction. . .\n");
                    return;
                } else if (input.equals("0")) {
                    System.out.println("Proceeding to Checkout. . .");
                    break;
                } else {
                    int idx = Integer.parseInt(input) - 1;
                    Merchandise m = product.get(idx);
                    // Add Merch to cart
                    t.addMerchandise( m );
                    // Buy each Item once
                    product.remove( idx );
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
        double total = calculateTotal(t.getProductList());
        System.out.printf("The total for you transaction is $%3.2f.\n", total);
        t.setTotalPrice(total);
        transServ.completePurchase(t);
        System.out.println("Purchase Complete!\n");
    }

    /**
     * Calculates the total price of the items in the shopping cart.
     * 
     * @param cart The list of {@link TransactionItem} in the cart.
     * @return The total price of the items in the cart.
     */
    private double calculateTotal(List<TransactionItem> cart) {
        double total = 0;
        for (TransactionItem m: cart) {
            total += m.getMerch().getMarketPrice();
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
            System.out.printf("[%2d] %s\n", i + 1, m.getProductName());
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
        t.setPurchaseDate(LocalDate.now());
        t.setProductList( new ArrayList<TransactionItem>() );
        t.setMember(m);
        List<Staff> cashiers = transServ.findCashier(s);
        if (cashiers.isEmpty()) {
            System.out.println("There are no cashiers at this store\n");
            return null;
        }
        Staff cash = cashiers.get( 1009 % cashiers.size() );

        return t;
    }
}