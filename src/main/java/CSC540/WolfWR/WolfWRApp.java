package CSC540.WolfWR;

import CSC540.WolfWR.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class WolfWRApp  implements CommandLineRunner{


    @Autowired
    private MemberRepository mRepo;

    public static void main(String[] args) {
        SpringApplication.run(WolfWRApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nWelcome to Wolf Wholesale\n\n");



        while(true) {
            System.out.println("Please select your role (input the number associated with your role): ");
            System.out.println("[1] Manager");
            System.out.println("[2] Billing Staff");
            System.out.println("[3] Registration Staff");
            System.out.println("[4] Warehouse Staff");
            System.out.println("[5] Customer");
            System.out.print("> ");

            String line = scan.nextLine().trim();
            if (line.trim().equals("q")){
                System.out.println("Goodbye");
                break;
            }

            switch(line) {
                case "0":
                    System.out.println("Loading Data to the database. . .\n");
                    break;
                case "1":
                    System.out.println("Manager View. . .\n");                    break;
                case "2":
                    System.out.println("Billing Staff View. . .\n");                    break;
                case "3":
                    System.out.println("Registration Staff View. . .\n");                    break;
                case "4":
                    System.out.println("Warehouse Staff View. . .\n");                    break;
                case "5":
                    System.out.println("Customer View. . .\n");
                    break;
                default:
                    System.out.println("Unknown role selected\n");
            }
        }
        scan.close();
    }
}
