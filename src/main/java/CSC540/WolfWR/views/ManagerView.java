package CSC540.WolfWR.views;

import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ManagerView {

    @Autowired
    private BillingStaffView billing;

    @Autowired
    private RegistrationView registration;

    @Autowired
    private WarehouseView warehouse;

    @Autowired
    private CustomerView customer;

    @Autowired
    private GlobalView global;

    @Autowired
    private MemberService memberServ;


    public void view(Scanner scan) {
        String input = null;
        BillingStaffView billling = new BillingStaffView();
        WarehouseView warehouse = new WarehouseView();
        RegistrationView registration = new RegistrationView();
        while (true) {

            System.out.println("Select an action with the number provided:");

            System.out.println("[0] Return to Home Page");
            System.out.println("[1] Generate Bill for Supplier");
            System.out.println("[2] Calculate Membership Rewards");
            System.out.println("[3] Generate Sales Report (day, month, year)");
            System.out.println("[4] Generate Sales Report (start - end)");
            System.out.println("[5] View History");

            System.out.print("> ");
            input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("back")) {
                return;
            }

            switch (input.trim().toLowerCase()) {
                case "case 1":
                    // Make call to helper method
                    billling.generateBill(scan);
                    break;
                case "case 2":
                    billling.tabulateRewards(scan);
                    break;
                case "3":
                    billling.generateStoreSalesReport(scan);
                    break;
                case "4":
                    billling.generateBoundStoreSalesReport(scan);
                    break;
                case "5":
                    Member m = selectMember(scan);
                    if (m == null) {
                        break;
                    }
                    customer.viewHistory(m);
                    break;
                case "6":
                    break;
                default:
                    System.out.println("\nUnknown action\n");
            }
        }

    }

    public Member selectMember(Scanner scan) {
        List<Member> members = memberServ.findAll();
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            System.out.printf("[%d] %10s %10s. %s Member\n", i + 1, m.getFirstName(), m.getLastName(), m.getMembershipLevel());
        }
        System.out.print("> ");

        String input = scan.nextLine().trim();
        Member m = null;
        try {
            m = members.get( Integer.parseInt(input) );
            return m;
        } catch (Exception e) {
            System.out.println("Invalid Selection");
            return null;
        }
    }
}
