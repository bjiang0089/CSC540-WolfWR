package CSC540.WolfWR.views;

import CSC540.WolfWR.WolfWRApp;
import CSC540.WolfWR.services.MerchandiseService;
import CSC540.WolfWR.services.StoreService;
import CSC540.WolfWR.services.TransactionService;
import CSC540.WolfWR.services.SupplierService;
import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.models.Store;
import CSC540.WolfWR.models.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

@Component
public class WarehouseView {

    @Autowired
    private MerchandiseService merchServ;

    @Autowired
    private StoreService storeServ;

    @Autowired
    private TransactionService transServ;

    @Autowired
    private SupplierService supplServ;

    public void view(Scanner scan) {
        String input = null;

        System.out.println("Select an action with the number provided:\n");

        System.out.println("[0] Return to Home Page");
        System.out.println("[1] View All Inventory");
        System.out.println("[2] Transfer Inventory Between Stores");
        System.out.println("[3] Process Merchandise Return");
        System.out.println("[4] Add Product to Inventory");
        System.out.print("> ");

        input = scan.nextLine().trim();

        if (input.equals("0")) {
            System.out.println();
            return;
        }

        System.out.println();
        List<Store> stores = storeServ.findAll(); 
        System.out.println(); 
        System.out.println("Select your store:");
        displayStores(stores);
        Store myStore = null;
        try {
            myStore = stores.get(Integer.parseInt(scan.nextLine().trim()));
        } catch (Exception e) {
            System.out.println("Invalid Store\n");
            return;
        }
        switch (input.trim()) {
            case "1":
                List<Merchandise> inventory = merchServ.storeInventory(myStore);
                viewAllInventory(inventory, scan);
                System.out.println();
                System.out.println("Success!");
                System.out.println();
                break;
            case "2":
                transferInventory(myStore, scan);
                break;
            case "3":
                processReturn(myStore, scan);
                break;
            case "4":
                stockMerchandise(myStore, scan);
                break;
            default:
                System.out.println("\nUnknown action\n");
        }
    }

    private void displayStores(List<Store> stores) {
        int idx = 0;
        for (Store s : stores) {
            System.out.printf("[%d] Store ID: %d\n", idx, s.getStoreID());
            idx++;
        }
        System.out.print("> ");
    }

    public void transferInventory(Store myStore, Scanner scan) {
        List<Store> stores = storeServ.findAll();
        Store theirStore = null;
        System.out.println();
        System.out.println("Select the recipient store:");
        displayStores(stores);
        try {
            theirStore = stores.get(Integer.parseInt(scan.nextLine().trim()));
        } catch (Exception e) {
            System.out.println("Invalid Store\n");
            return;
        }
        if (myStore.getStoreID() == theirStore.getStoreID()) {
            System.out.println("Sender and recipient cannot be the same.");
            return;
        }
        List<Merchandise> myInventory = merchServ.storeInventory(myStore);
        List<Merchandise> otherInventory = merchServ.storeInventory(theirStore);

        System.out.println();
        System.out.println("Select the merchandise to transfer:");
        viewAllInventory(myInventory, scan);
        System.out.print("> ");
        Merchandise myMerch = null;
        Merchandise theirMerch = null;
        int transferAmt = 0;
        try {
            myMerch = myInventory.get(Integer.parseInt(scan.nextLine().trim()));
        } catch (Exception e) {
            System.out.println("Invalid Merchandise\n");
            return;
        }
        for (Merchandise merch : otherInventory) {
            if (myMerch.getProductName().equalsIgnoreCase(merch.getProductName())) {
                theirMerch = merch;
                break;
            }
        }
        System.out.println();
        System.out.print("Enter the quantity of merchandise to transfer:\n");
        System.out.print("> ");
        try {
            transferAmt = Integer.parseInt(scan.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid Quantity\n");
            return;
        }
        if (null != theirMerch) {
            if (transferAmt == 0) {
                System.out.println("Cannot Transfer 0 Units of Merchandise\n");
                return;
            } else if (myMerch.getQuantity() - transferAmt < 0) {
                System.out.println("Insufficient Amount of Merchandise to Transfer\n");
                return;
            } else {
                myMerch.setQuantity(myMerch.getQuantity() - transferAmt);
                theirMerch.setQuantity(theirMerch.getQuantity() + transferAmt);
                System.out.println();
                System.out.println("Success!\n");
            }
        } else {
            if (transferAmt == 0) {
                System.out.println("Cannot Transfer 0 Units of Merchandise\n");
                return;
            } else if (myMerch.getQuantity() - transferAmt < 0) {
                System.out.println("Insufficient Amount of Merchandise to Transfer\n");
                return;
            }
            long newId = merchServ.generateID();
            String name = myMerch.getProductName();
            int quantity = transferAmt;
            double buy = myMerch.getBuyPrice();
            double market = myMerch.getMarketPrice();
            LocalDate prod = myMerch.getProductionDate();
            LocalDate exp = myMerch.getExpirationDate();
            Supplier supplier = myMerch.getSupplier();

            theirMerch = new Merchandise();
            theirMerch.setProductID(newId);
            theirMerch.setProductName(name);
            theirMerch.setQuantity(quantity);
            theirMerch.setBuyPrice(buy);
            theirMerch.setMarketPrice(market);
            theirMerch.setProductionDate(prod);
            theirMerch.setExpirationDate(exp);
            theirMerch.setSupplier(supplier);
            theirMerch.setStore(theirStore);

            merchServ.save(theirMerch);
            System.out.println();
            System.out.println("Success!\n");
        }
    } 

    public void viewAllInventory(List<Merchandise> inventory, Scanner scan) {
        System.out.println();
        if (inventory.size() == 0) {
            System.out.println("You have no inventory to display...");
        } else {
            int idx = 0;
            for (Merchandise merch : inventory) {
                System.out.printf("[%d] %s", idx, merch.toString());
                idx++;
            }
        }
    }

    public void processReturn(Store store, Scanner scan) {
        List<Merchandise> inventory = merchServ.storeInventory(store);
        System.out.println("Select the merchandise being returned:");
        viewAllInventory(inventory, scan);
        System.out.print("> ");
        Merchandise merch = null;
        try {
            merch = inventory.get(Integer.parseInt(scan.nextLine().trim()));
        } catch (Exception e) {
            System.out.println("Invalid Merchandise\n");
            return;
        }
        System.out.println();
        System.out.println("Enter the quantity of merchandise being returned:");
        int amt = 0;
        System.out.print("> ");
        try {
            amt = Integer.parseInt(scan.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid Quantity\n");
            return;
        }
        merch.setQuantity(merch.getQuantity() + amt);
        System.out.println();
        System.out.println("Success!\n");
    }

    public void stockMerchandise(Store store, Scanner scan) {
        System.out.println();
        System.out.println("[0] Add new item to inventory.");
        System.out.println("[1] Increase quantity of existing item in inventory.");
        System.out.print("> ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scan.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid Selection\n");
            return;
        }
        if (choice < 0 || choice > 1) {
            System.out.println("Invalid Selection\n");
            return;
        } else {
            if (choice == 0) {
                addMerchandise(store, scan);
            } else {
                List<Merchandise> inventory = merchServ.totalStoreInventory(store);
                viewAllInventory(inventory, scan);
                System.out.print("> ");
                Merchandise merch = null;
                try {
                    merch = inventory.get(Integer.parseInt(scan.nextLine().trim()));
                } catch (Exception e) {
                    System.out.println("Invalid Merchandise\n");
                    return;
                }
                System.out.println("Enter the quantity of the item to be added:");
                System.out.print("> ");
                int add = 0;
                try {
                    add = Integer.parseInt(scan.nextLine().trim());
                } catch (Exception e) {
                    System.out.println("Invalid Quantity\n");
                    return;
                }
                if (add == 0) {
                    System.out.println("Invalid Quantity\n");
                    return;
                }
                merch.setQuantity(merch.getQuantity() + add);
                System.out.println("Success!\n");
            }   
        }
    }

    private void addMerchandise(Store store, Scanner scan) {
        long productID = merchServ.generateID();
        String name = "";
        int quantity = 0;
        double buy = 0.0;
        double market = 0.0;
        String pdString = "";
        LocalDate prod = null;
        String edString = "";
        LocalDate exp = null;
        Supplier supplier = null;
        try {
            System.out.print("Please enter the name of the product being added: ");
            name = scan.nextLine().trim();
            System.out.println();
            System.out.print("Please enter the product quantity: ");
            quantity = Integer.parseInt(scan.nextLine().trim());
            System.out.println();
            System.out.print("Please enter the buy price of the product: ");
            buy = Double.parseDouble(scan.nextLine().trim());
            System.out.println();
            System.out.print("Please enter the market price of the product: ");
            market = Double.parseDouble(scan.nextLine().trim());
            System.out.println();
            System.out.print("Please enter the production date of the product: ");
            pdString = scan.nextLine().trim();
            prod = LocalDate.parse(pdString, WolfWRApp.timeFormat);
            System.out.println();
            System.out.print("Please enter the expiration date of the product: ");
            edString = scan.nextLine().trim();
            exp = LocalDate.parse(edString, WolfWRApp.timeFormat);

            System.out.println("Select the supplier from the following list:\n");
            List<Supplier> suppliers = supplServ.findAll();
            int idx = 0;
            for (Supplier suppl : suppliers) {
                System.out.printf("[%d] Supplier Name: %s, Supplier ID: %d\n", idx, suppl.getSupplierName(), suppl.getSupplierID());
                idx++;
            }
            System.out.print("> ");
            try {
                supplier = suppliers.get(Integer.parseInt(scan.nextLine().trim()));
            } catch (Exception e) {
                System.out.println("Invalid Supplier\n");
                return;
            }

            Merchandise merch = new Merchandise();
            merch.setProductID(productID);
            merch.setProductName(name);
            merch.setQuantity(quantity);
            merch.setBuyPrice(buy);
            merch.setMarketPrice(market);
            merch.setProductionDate(prod);
            merch.setExpirationDate(exp);
            merch.setSupplier(supplier);
            merch.setStore(store);
            merchServ.save(merch);
        } catch (Exception e) {
            System.out.println("Invalid Merchandise\n");
            return;
        }
        System.out.println("Success!\n");
    }
}
