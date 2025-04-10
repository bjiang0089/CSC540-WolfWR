package CSC540.WolfWR.services;

import CSC540.WolfWR.models.*;
import CSC540.WolfWR.repositories.MerchandiseRepository;
import CSC540.WolfWR.repositories.StaffRepository;
import CSC540.WolfWR.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service class responsible for handling operations related to {@link Transaction} entities.
 * <p>
 * This class extends the generic {@link Services} class and provides business logic for managing
 * {@link Transaction} entities, including handling purchases, processing rewards, and generating transaction IDs.
 * It interacts with the {@link TransactionRepository}, {@link MerchandiseRepository}, and {@link StaffRepository}
 * to perform CRUD operations on {@link Transaction}, {@link Merchandise}, and {@link Staff} records in the database.
 * </p>
 * 
 * @see Transaction
 * @see TransactionRepository
 * @see MerchandiseRepository
 * @see StaffRepository
 * @see Services
 */
@Transactional
@Component
public class TransactionService  extends  Services<Transaction, Long> {

    /**
     * The repository used for performing database operations related to {@link Transaction} entities.
     */
    @Autowired
    private TransactionRepository repo;

    /**
     * The repository used for performing database operations related to {@link Merchandise} entities.
     */
    @Autowired
    private MerchandiseRepository merchRepo;

    /**
     * The repository used for performing database operations related to {@link Staff} entities.
     */
    @Autowired
    private StaffRepository staffRepo;

    /**
     * Returns the repository used for performing database operations on {@link Transaction} entities.
     * This method is implemented from the abstract {@link Services} class.
     * 
     * @return the {@link TransactionRepository} instance used to interact with the database.
     */
    @Override
    protected JpaRepository<Transaction, Long> getRepo() {
        return this.repo;
    }

    /**
     * Processes the rewards for a given {@link Member} based on transactions that occurred before the specified end date.
     * 
     * @param m The {@link Member} whose rewards are to be processed.
     * @param end The end date for considering transactions for reward processing.
     * @return A list of {@link Transaction} entities that are eligible for rewards for the given member.
     */
    public List<Transaction> processRewards(Member m, LocalDate end) {
        return this.repo.processRewards(m.getId(), end);
    }

    /**
     * Completes the purchase for a given {@link Transaction} by decrementing the quantity of each product in the transaction.
     * This method updates the {@link Merchandise} quantities in the inventory and saves the {@link Transaction} entity.
     * 
     * @param t The {@link Transaction} representing the completed purchase.
     */
    @Transactional
    public void completePurchase(Transaction t) {
        List<TransactionItem> cart = t.getProductList();
        for (TransactionItem ti: cart) {
            Merchandise m = ti.getMerch();
            // Decrement the quantity
            m.setQuantity( m.getQuantity() - 1 );
            merchRepo.save( m );
        }

        repo.save(t);
    }

    /**
     * Finds the list of {@link Staff} members working as cashiers at a given {@link Store}.
     * 
     * @param s The {@link Store} at which the cashiers are working.
     * @return A list of {@link Staff} members working as cashiers in the given store.
     */
    public List<Staff> findCashier(Store s) {
        return staffRepo.findCashier(s.getStoreID(), Staff.Title.BILLING.name());
    }

    /**
     * Generates a unique transaction ID by selecting a random number that does not already exist in the system.
     * It ensures that the generated ID is unique by checking against the list of existing transaction IDs.
     * 
     * @return A unique transaction ID.
     */
    public long generateID() {
        List<Long> ids = repo.getIDs();
        Random rand = new Random();
        long id = rand.nextLong() % 1009;
        id = Math.abs(id);
        while ( ids.contains( id % 1009 ) ) {
            id = rand.nextLong() % 1009;
            id = Math.abs(id);
        }
        return id;
    }
}
