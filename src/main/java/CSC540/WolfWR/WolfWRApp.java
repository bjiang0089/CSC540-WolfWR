package CSC540.WolfWR;

import CSC540.WolfWR.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@SpringBootApplication
public class WolfWRApp  implements CommandLineRunner{


    @Autowired
    private DataLoader loader;

    @Autowired
    private BillingStaffView billing;

    @Autowired
    private RegistrationView registration;

    @Autowired
    private WarehouseView warehouse;

    @Autowired
    private ManagerView manager;

    @Autowired
    private CustomerView customer;

    @Autowired
    private GlobalView global;

    public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static void main(String[] args) {
        SpringApplication.run(WolfWRApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nWelcome to Wolf Wholesale\n\n");



        while(true) {
            System.out.println("Please select your role (input the number associated with your role): ");
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
}
