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
        System.out.println("\n\nMain Method Running. . .\n\n");
//        while(true) {
//            System.out.println("\n\nEnter membership details as a comma separated list\n" +
//                    "(Id, First Name, Last Name, Membership Level, email, home address, phone number, active status) or q to quit: ");
//            String line = scan.nextLine();
//            if (line.trim().equals("q")){
//                System.out.println("Goodbye");
//                break;
//            }
//
//            String[] attr = line.split("\\s*,\\s*");
//
//            long id = Long.parseLong(attr[0]);
//            String first = attr[1] ;
//            String last = attr[2];
//            String mlID = attr[3];
//            String email = attr[4];
//            String addr = attr[5];
//            String phone = attr[6];
//            boolean active = Boolean.parseBoolean( attr[7] );
//
//
//
//            Member m = new Member(id, first, last, ml, email, addr, phone, active);
//            mRepo.save(m);
//            m = null;
//
//            Optional<Member> retrieve = mRepo.findById(id);
//            if (retrieve.isPresent()) {
//                m = retrieve.get();
//                System.out.println( ml.toString() );
//            } else {
//                System.out.println("Failed to Find Member\n");
//                continue;
//            }
//
//        }
        scan.close();
    }
}
