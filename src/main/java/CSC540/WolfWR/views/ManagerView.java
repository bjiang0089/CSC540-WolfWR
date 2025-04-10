package CSC540.WolfWR.views;

import CSC540.WolfWR.WolfWRApp;
import CSC540.WolfWR.models.*;
import CSC540.WolfWR.services.*;
import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


@Transactional
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
    @Autowired
    private MerchandiseService merchandiseService;
    @Autowired
    private DiscountService discountService;


    public void view(Scanner scan) {

/**
 * The {@code ManagerView} class represents the manager's interface for interacting with various views in the application.
 * This class allows the manager to perform different actions, such as generating bills and tabulating rewards for customers.
 * It uses the {@link BillingStaffView}, {@link WarehouseView}, and {@link RegistrationView} classes for specific actions.
 */
@Component
public class ManagerView {

    /**
     * Displays the manager's options and allows the manager to interact with different system functionalities.
     * The manager can select different actions, such as generating a bill or tabulating rewards, or return to the previous page.
     * 
     * @param scan The {@link Scanner} object used to take user input.
     */
    public static void view(Scanner scan) {

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
            System.out.println("[7] Fire Staff");
            System.out.println("[8] Create Discount");

            System.out.print("> ");
            input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("back")) {
                return;
            }

            switch (input.trim().toLowerCase()) {
                case "0":
                    return;
                case "1":
                    // Make call to helper method
                    billing.generateBill(scan);
                    break;
                case "2":
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
                case "7":
                    fireStaff(scan);
                    break;
                case "8":
                    createDiscount(scan);
                    break;
                default:
                    System.out.println("\nUnknown action\n");
            }
        }

    }

    public void createDiscount(Scanner scan) {
        Store store = selectStore(scan);
        List<Merchandise> merch = merchandiseService.storeInventory(store);

        System.out.println("\nSelect a product:");
        Merchandise m = null;
        for (int i = 0; i < merch.size(); i++) {
            m = merch.get(i);
            System.out.printf("[%2d] Store: %4d. Buy Price: %3.2f. Market Price: %3.2f. Product: %s\n",
                    i + 1, m.getStore().getStoreID(), m.getBuyPrice(), m.getMarketPrice(), m.getProductName());
        }
        System.out.print("> ");
        String input = scan.nextLine().trim();

        try {
            m = merch.get( Integer.parseInt(input) - 1);
        } catch (Exception e) {
            System.out.println("Invalid Product");
            return;
        }

        System.out.println("Please enter discount percentage as a whole number (0 - 100)");
        System.out.print("> ");
        input = scan.nextLine().trim();
        int percent = 0;
        try {
            percent = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Error reading percentage\n");
            return;
        }

        System.out.println("Please provide the *START* date for the report as mm-dd-yyyy:");
        System.out.print("> ");
        input = scan.nextLine().trim();
        LocalDate start = null;
        try {
            start = LocalDate.parse(input, WolfWRApp.timeFormat);
        } catch (Exception e) {
            System.out.println("Unable to parse start date\n");
            return;
        }

        System.out.println("Please provide the *END* date for the report as mm-dd-yyyy:");
        System.out.print("> ");
        input = scan.nextLine().trim();
        LocalDate end = null;
        try {
            end = LocalDate.parse(input, WolfWRApp.timeFormat);
        } catch (Exception e) {
            System.out.println("Unable to parse start date\n");
            return;
        }

        Discount d = new Discount(m, percent, start, end);
        try {
            discountService.save(d);
            System.out.println("\nDiscount Saved\n");
        } catch (Exception e) {
            System.out.println("Error occured while saving\n");
        }

    }

    public void hireNewStaff(Scanner scan) {
        String input = "";
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
        Store store = selectStore(scan);

        if (store == null) {
            return;
        }
        List<Staff> staff = staffService.findAllByStore(store);

        System.out.println("Select a staff member:");
        System.out.print("> ");
        Staff s = null;
        for (int i = 0; i < staff.size(); i++) {
            s = staff.get(i);
            System.out.printf("[%d] %s \n", i + 1, s.getName());
        }

        String input = scan.nextLine().trim();
        try {
            int idx = Integer.parseInt(input) - 1;
            s = staff.get(idx);
            staffService.delete(s);
            System.out.println("Staff member removed\n");
        } catch ( Exception e ) {
            System.out.println("Invalid staff member\n");
        }
    }

    public Store selectStore(Scanner scan) {
        List<Store> locs = storeService.findAll();
        System.out.println("\nSelect store:");
        System.out.print("> ");

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
