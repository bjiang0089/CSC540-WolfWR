package CSC540.WolfWR.views;

import org.springframework.stereotype.Component;

import java.util.Scanner;

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
        BillingStaffView billling = new BillingStaffView();
        WarehouseView warehouse = new WarehouseView();
        RegistrationView registration = new RegistrationView();
        while (true) {

            System.out.println("Insert Message Body or " +
                    "'back' to return to the previous page");
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
                default:
                    System.out.println("\nUnknown action\n");
            }
        }

    }
}
