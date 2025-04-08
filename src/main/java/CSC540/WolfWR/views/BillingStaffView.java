package CSC540.WolfWR.views;

import java.util.Scanner;

public class BillingStaffView {

    public static void view(Scanner scan) {
        String input = null;
        BillingStaffView view = new BillingStaffView();
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
                    view.generateBill(scan);
                    break;
                case "case 2":
                    view.tabulateRewards(scan);
                        break;
                default:
                    System.out.println("\nUnknown action\n");
            }
        }

    }

    private void generateBill(Scanner scan) {
        System.out.println("\nChoose a supplier to pay:");
        // TODO: Generate list of suppliers

        //
    }

    private void tabulateRewards(Scanner scan) {
        System.out.println("\nChoose a member:");
        // TODO: Generate list of members

    }
}
