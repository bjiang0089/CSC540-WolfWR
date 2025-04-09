package CSC540.WolfWR.views;

import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.models.Store;
import CSC540.WolfWR.services.MerchandiseService;
import CSC540.WolfWR.services.StoreService;
import CSC540.WolfWR.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CustomerView {

    @Autowired
    private StoreService storeServ;

    @Autowired
    private MerchandiseService merchServ;

    @Autowired
    private TransactionService transServ;

    public void view(Scanner scan) {
        String input = null;

        System.out.println("Select an action with the number provided:\n>");
        System.out.println("[0] Return to Home Page");
        System.out.println("[1] Make a Purchase");
        System.out.print("> ");

        input = scan.nextLine().trim();

        if (input.equalsIgnoreCase("0")) {
            return;
        }

        switch(input) {
            case "0":
                System.out.println("Returning to Home Page. . .\n");
                return;
            case "1":
                System.out.println();
                break;
            default:
                System.out.println("\nUnknown action\n");
        }
    }

    public void makeTransaction(Scanner scan) {
        // TODO: Store Selection
        Store current = null;
        List<Store> locations = storeServ.findAll();
        listLocations(locations);

        try {
            int idx = Integer.parseInt( scan.nextLine().trim() );
            current = locations.get(idx - 1);
        } catch (Exception e) {
            System.out.println("Invalid Store\n");
            return;
        }

        while(true) {
            List<Merchandise> product = null;
        }
        // TODO: Get list of transaction items
        // TODO: Item Selection
        //
    }

    private void listLocations(List<Store> locs) {
        for(int i = 0; i < locs.size(); i++) {
            Store s = locs.get(i);
            System.out.printf("[%d] %s\n", i + 1, s.getAddress());
        }
        System.out.print("> ");
    }
}
