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

@Transactional
@Component
public class CustomerView {

    @Autowired
    private StoreService storeServ;

    @Autowired
    private MerchandiseService merchServ;

    @Autowired
    private TransactionService transServ;

    @Autowired
    private MemberService memberServ;

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

    private double calculateTotal(List<TransactionItem> cart) {
        double total = 0;
        for (TransactionItem m: cart) {
            total += m.getMerch().getMarketPrice();
        }
        return total;
    }

    private void listLocations(List<Store> locs) {
        for(int i = 0; i < locs.size(); i++) {
            Store s = locs.get(i);
            System.out.printf("[%d] %s\n", i + 1, s.getAddress());
        }
        System.out.print("> ");
    }

    private void listInventory(List<Merchandise> merch) {

        System.out.println("[-1] Cancel Transaction");
        System.out.println("[0] Checkout\n");
        for (int i = 0; i < merch.size(); i++) {
            Merchandise m = merch.get(i);
            System.out.printf("[%2d] $%3.2f %s\n", i + 1, m.getMarketPrice(),m.getProductName());
        }
        System.out.print("> ");
    }

    private void displayMembers(List<Member> members) {
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            System.out.printf("[%d] %10s %10s. %s Member\n", i + 1, m.getFirstName(), m.getLastName(), m.getMembershipLevel());
        }
        System.out.print("> ");
    }

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