package CSC540.WolfWR.views;

import CSC540.WolfWR.WolfWRApp;
import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.models.Supplier;
import CSC540.WolfWR.models.Transaction;
import CSC540.WolfWR.services.MemberService;
import CSC540.WolfWR.services.MerchandiseService;
import CSC540.WolfWR.services.SupplierService;
import CSC540.WolfWR.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class BillingStaffView {

    @Autowired
    private TransactionService transServ;

    @Autowired
    private MerchandiseService merchServ;

    @Autowired
    private MemberService memberServ;

    @Autowired
    private SupplierService supplierServ;


    public void view(Scanner scan) {
        String input = null;
        //BillingStaffView view = new BillingStaffView();
        while (true) {

            System.out.println("Select an action with the number provided:\n>");

            System.out.println("[0] Return to Home Page");
            System.out.println("[1] Generate Bill for Supplier");
            System.out.println("[2] Calculate Membership Rewards");
            System.out.print("> ");

            input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("0")) {
                return;
            }

            switch (input.trim().toLowerCase()) {
                case "1":
                    // Make call to helper method
                    generateBill(scan);
                    break;
                case "2":
                    tabulateRewards(scan);
                        break;
                default:
                    System.out.println("\nUnknown action\n");
            }
        }

    }

    public void generateBill(Scanner scan) {

        // TODO: Generate list of suppliers
        List<Supplier> suppliers = supplierServ.findAll();
        System.out.println("\nChoose a supplier to pay:\n> ");
        displaySuppliers(suppliers);

        Supplier s = null;
        try {
            s = suppliers.get( Integer.parseInt(scan.nextLine().trim()) );
        } catch (Exception e) {
            System.out.println("Invalid Supplier\n");
            return;
        }

        System.out.println("Choose a time frame.");
        System.out.print("Input the start date as mm-dd-yyyy:\n> ");
        LocalDate start = null;
        LocalDate end = null;

        try {
            start = LocalDate.parse(scan.nextLine().trim(), WolfWRApp.timeFormat);
            System.out.print("\nInput the end date as mm-dd-yyyy:\n> ");
            end = LocalDate.parse(scan.nextLine().trim(), WolfWRApp.timeFormat);
        } catch (Exception e) {
            System.out.println("Invalid date\n");
            return;
        }

        List<Merchandise> deliveries = merchServ.deliveriesByTimeAndSupplier(start, end, s);
        System.out.println();
        double grandTotal = showDeliveries(deliveries);
        System.out.printf("Pay %s $%.2f\n\n", s.getSupplierName(), grandTotal);

    }

    public void tabulateRewards(Scanner scan) {

        // Generate list of members and select
        List<Member> members = memberServ.findAll();

        System.out.println("\nChoose a member:");
        displayMembers(members);
        Member m = null;
        try {
            int idx = Integer.parseInt( scan.nextLine().trim() );
            m = members.get(idx - 1);
        } catch (Exception e) {
            System.out.println("Invalid Member\n");
            return;
        }
        System.out.print("Input the *END* of the rewards period as mm-dd-yyyy\n> ");
        LocalDate end = null;

        try {
            end = LocalDate.parse(scan.nextLine().trim(), WolfWRApp.timeFormat);
        } catch (Exception e) {
            System.out.println("Invalid Date.\n");
            return;
        }

        List<Transaction> transactions = transServ.processRewards(m, end);
        double total = tabulateTotalPurchases(transactions);

        System.out.print("""
                
                Input rewards percentage as a whole number between 0 and 100.
                Example: Platinum members get 2%. Input '2'.
                >\s""");

        int percent = 0;
        try {
             percent = Integer.parseInt(scan.nextLine().trim());
             if (percent < 0 || percent > 100) {
                System.out.println("Invalid rewards rate.\n");
                return;
             }
        } catch (Exception e) {
            System.out.println("Invalid Percentage\n");
            return;
        }

        System.out.printf("\nMember %s %s purchased $%.2f this year.\nThey earned %.2f in rewards.\n\n",
                m.getFirstName(), m.getLastName(), total, total * 0.01 * percent);
    }

    public double showDeliveries(List<Merchandise> deliveries) {
        double grandTotal = 0;
        for (Merchandise m: deliveries) {
            double total = m.getBuyPrice() * m.getQuantity();
            System.out.printf("Product %20s, Buy Price%4.2f, Quantity: %4d, Total: %4.2f\n",
                    m.getProductName(), m.getBuyPrice(), m.getQuantity(), total);
            grandTotal += total;
        }
        return grandTotal;
    }

    private void displaySuppliers(List<Supplier> suppliers) {
        for (int i = 0; i < suppliers.size(); i++) {
            System.out.printf("[%d] %s\n", i, suppliers.get(i).getSupplierName());
        }
        System.out.print("> ");
    }

    private double tabulateTotalPurchases(List<Transaction> trans) {
        double total = 0;
        for (Transaction t: trans) {
            total += t.getTotalPrice();
        }
        return total;
    }

    private void displayMembers(List<Member> members) {
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            System.out.printf("[%d] %10s %10s. %s Member\n", i + 1, m.getFirstName(), m.getLastName(), m.getMembershipLevel());
        }
        System.out.print("> ");
    }

}
