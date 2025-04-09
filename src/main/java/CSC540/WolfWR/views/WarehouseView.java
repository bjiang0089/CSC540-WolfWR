package CSC540.WolfWR.views;

import CSC540.WolfWR.repositories.MerchandiseRepository;
import CSC540.WolfWR.services.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class WarehouseView {

    @Autowired
    private MerchandiseService merchServ;

    public void view(Scanner scan) {

    }

    public void transferInventory(Scanner scan) {

    }

    public void viewAllInventory(Scanner scan) {

    }
}
