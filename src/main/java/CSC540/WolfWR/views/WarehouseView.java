package CSC540.WolfWR.views;

import CSC540.WolfWR.repositories.MerchandiseRepository;
import CSC540.WolfWR.services.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * The {@code WarehouseView} class represents the view for managing warehouse operations such as transferring inventory
 * and viewing the entire inventory.
 * This class interacts with the user through the {@link Scanner} to facilitate inventory management.
 */
@Component
public class WarehouseView {

    /**
     * The service used for performing operations on {@link MerchandiseService} entities.
     */
    @Autowired
    private MerchandiseService merchServ;

    /**
     * Displays the warehouse management interface to the user.
     * This method provides options for warehouse staff to manage the inventory and transfer items between stores.
     * 
     * @param scan The {@link Scanner} object used to take user input for various warehouse operations.
     */
    public void view(Scanner scan) {

    }

    /**
     * Facilitates the process of transferring inventory between stores.
     * This method is responsible for interacting with the user to handle the inventory transfer operation.
     * 
     * @param scan The {@link Scanner} object used to gather user input for transferring inventory.
     */
    public void transferInventory(Scanner scan) {

    }

    /**
     * Displays all merchandise available in the inventory.
     * This method allows the user to view the entire inventory in the warehouse, including details of each product.
     * 
     * @param scan The {@link Scanner} object used to take user input for viewing inventory.
     */
    public void viewAllInventory(Scanner scan) {

    }
}
