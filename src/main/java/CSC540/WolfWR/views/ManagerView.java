package CSC540.WolfWR.views;

import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.models.Staff;
import CSC540.WolfWR.models.Store;
import CSC540.WolfWR.services.MemberService;
import CSC540.WolfWR.services.StaffService;
import CSC540.WolfWR.services.StoreService;
import ch.qos.logback.core.encoder.EchoEncoder;
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
    @Autowired
    private StaffService staffService;
    @Autowired
    private StoreService storeService;


    public void view(Scanner scan) {
        String input = null;
        while (true) {

            System.out.println("Select an action with the number provided:");

            System.out.println("[0] Return to Home Page");
            System.out.println("[1] Generate Bill for Supplier");
            System.out.println("[2] Calculate Membership Rewards");
            System.out.println("[3] Generate Sales Report (day, month, year)");
            System.out.println("[4] Generate Sales Report (start - end)");
            System.out.println("[5] View History");
            System.out.println("[6] Hire New Staff");

            System.out.print("> ");
            input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("back")) {
                return;
            }

            switch (input.trim().toLowerCase()) {
                case "0":
                    return;
                case "case 1":
                    // Make call to helper method
                    billing.generateBill(scan);
                    break;
                case "case 2":
                    billing.tabulateRewards(scan);
                    break;
                case "3":
                    billing.generateStoreSalesReport(scan);
                    break;
                case "4":
                    billing.generateBoundStoreSalesReport(scan);
                    break;
                case "5":
                    Member m = selectMember(scan);
                    if (m == null) {
                        break;
                    }
                    customer.viewHistory(m);
                    break;
                case "6":
                    hireNewStaff(scan);
                    break;
                default:
                    System.out.println("\nUnknown action\n");
            }
        }

    }

    public void hireNewStaff(Scanner scan) {
        String input = "";

        System.out.println("\nSelect a store:");
        Store store = selectStore(scan);
        if (store == null) {
            return;
        }
        System.out.println("Please provide the following staff information as a comma seperated list:");
        System.out.println("(full name, age, email address, phone number)");
        System.out.print("> ");
        input = scan.nextLine().trim();

        String[] attr = input.split("\\s*,\\s*");
        int age = 0;
        try {
            age = Integer.parseInt(attr[1]);
        } catch (Exception e) {
            System.out.println("Unable to read Age\n");
            return;
        }

        System.out.println("Please provide their address on one line (no line breaks):");
        System.out.print("> ");
        String addr = scan.nextLine().trim();

        System.out.println("Please select which department they will be in as a number:");
        System.out.println("[1] Management");
        System.out.println("[2] Billing");
        System.out.println("[3] Registration");
        System.out.println("[4] Warehouse");
        System.out.print("> ");

        input = scan.nextLine().trim();
        String title = "";
        switch (input) {
            case "1":
                title = "manager";
                break;
            case "2":
                title = "cashier";
                break;
            case "3":
                title = "warehouse checker";
                break;
            case "4":
                title = "registration";
                break;
            default:
                System.out.println("Invalid Role\n");
                return;
        }
        Staff s = getNewStaff();
        s.setAddress(addr);
        s.setName(attr[0]);
        s.setAge(age);
        s.setEmail(attr[2]);
        s.setPhone(attr[3]);
        s.setTitle(title);
        s.setStore(store);
        try {
            staffService.save(s);
            System.out.println("New staff member added ");
        } catch (Exception e) {
            System.out.println("Error occurred when saving staff");
        }

    }

    public void fireStaff(Scanner scan) {

    }

    public Store selectStore(Scanner scan) {
        List<Store> locs = storeService.findAll();

        for(int i = 0; i < locs.size(); i++) {
            Store s = locs.get(i);
            System.out.printf("[%d] %s\n", i + 1, s.getAddress());
        }
        System.out.print("> ");
        try {
            return locs.get( Integer.parseInt(scan.nextLine().trim()) - 1 );
        } catch (Exception e) {
            System.out.println("Invalid Store");
            return null;
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

    public Staff getNewStaff() {
        Staff s = new Staff();
        s.setStaffId( staffService.generateID() );
        s.setEmploymentTime( 0 );
        return s;
    }
}
