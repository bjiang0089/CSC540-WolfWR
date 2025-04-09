package CSC540.WolfWR.views;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ManagerView {

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
