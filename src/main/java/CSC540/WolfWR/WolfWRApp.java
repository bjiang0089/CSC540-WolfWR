package CSC540.WolfWR;

import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.services.MemberService;
import CSC540.WolfWR.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for Wolf Wholesale Retail (WolfWR) system.
 * Acts as the command-line interface entry point for interacting with different roles in the system.
 * 
 * <p>This class is responsible for launching the Spring Boot application and presenting
 * a text-based menu to the user for selecting their role and accessing corresponding views.</p>
 * 
 * Implements {@link CommandLineRunner} to run initialization logic after the application context is loaded.
 */
@SpringBootApplication
public class WolfWRApp  implements CommandLineRunner{

    /** Loads initial data into the database. */
    @Autowired
    private DataLoader loader;

    /** View interface for billing staff operations. */
    @Autowired
    private BillingStaffView billing;

    /** View interface for registration staff operations. */
    @Autowired
    private RegistrationView registration;

    /** View interface for warehouse staff operations. */
    @Autowired
    private WarehouseView warehouse;

    /** View interface for manager operations. */
    @Autowired
    private ManagerView manager;

    /** View interface for customer operations. */
    @Autowired
    private CustomerView customer;

    /** View interface for global/corporate level operations. */
    @Autowired
    private GlobalView global;

    /** Service for managing member-related operations. */
    @Autowired
    private MemberService memberServ;

    /** Formatter for displaying dates in MM-dd-yyyy format. */
    public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WolfWRApp.class, args);
    }

    /**
     * Executes after the application has started. 
     * Displays a role selection menu and routes the user to the corresponding view.
     *
     * @param args application arguments
     * @throws Exception in case of input/output or other runtime errors
     */
    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nWelcome to Wolf Wholesale\n\n");



        while(true) {
            System.out.println("\nPlease select your role (input the number associated with your role) or q to quit: ");
            System.out.println("[0] Load Data");
            System.out.println("[1] Manager");
            System.out.println("[2] Billing Staff");
            System.out.println("[3] Registration Staff");
            System.out.println("[4] Warehouse Staff");
            System.out.println("[5] Customer");
            System.out.println("[6] Global / Corporate");
            System.out.print("> ");

            String line = scan.nextLine().trim();
            if (line.trim().equals("q")){
                System.out.println("Goodbye");
                break;
            }

            switch(line) {
                case "0":
                    System.out.println("Loading Data to the database. . .\n");
                    loader.loadData();
                    System.out.println("Data Loaded!!!\n");
                    break;
                case "1":
                    System.out.println("Manager View. . .\n");
                    manager.view(scan);
                    break;
                case "2":
                    System.out.println("Billing Staff View. . .\n");
                    billing.view(scan);
                    break;
                case "3":
                    System.out.println("Registration Staff View. . .\n");
                    registration.view(scan);
                    break;
                case "4":
                    System.out.println("Warehouse Staff View. . .\n");
                    warehouse.view(scan);
                    break;
                case "5":
                    System.out.println("Customer View. . .\n");
                    customer.view(scan);
                    break;

                case "6":
                    System.out.println("Global View. . .");
                    global.view(scan);
                    break;

                default:
                    System.out.println("Unknown role selected\n");
            }
        }
        scan.close();
    }

    /**
     * Helper method to display a selection of all customers (members).
     * Currently prints an index for each member found in the system.
     */
    private void customerSelection() {
        List<Member> members = memberServ.findAll();
        for (int i = 0; i < members.size(); i++) {
            System.out.printf("[%d] ");
        }
    }
}
