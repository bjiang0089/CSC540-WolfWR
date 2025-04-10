package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.models.Store;
import CSC540.WolfWR.models.Supplier;
import CSC540.WolfWR.repositories.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing {@link Merchandise} entities.
 * <p>
 * This service class provides methods for interacting with the data layer to perform CRUD (Create, Read, Update, Delete)
 * operations on {@link Merchandise} entities, using the {@link MerchandiseRepository}.
 * </p>
 * <p>
 * It extends the {@link Services} class to inherit common CRUD functionality and is annotated with {@link Component}
 * to make it a Spring-managed bean. The {@link Transactional} annotation ensures that database operations are performed
 * within a transaction context.
 * </p>
 * 
 * @see Merchandise
 * @see MerchandiseRepository
 * @see Services
 */
@Transactional
@Component
public class MerchandiseService extends Services<Merchandise, Long> {

    /**
     * The repository used for accessing and managing {@link Merchandise} entities in the database.
     */
    @Autowired
    private MerchandiseRepository repo;

    /**
     * Returns the repository associated with {@link Merchandise} entities.
     * <p>
     * This method overrides the {@link Services#getRepo()} method to provide the specific repository for {@link Merchandise}.
     * </p>
     * 
     * @return the {@link MerchandiseRepository} instance used to interact with {@link Merchandise} entities.
     */
    @Override
    protected JpaRepository<Merchandise, Long> getRepo() {
        return this.repo;
    }

    /**
     * Retrieves a list of {@link Merchandise} delivered by a specific supplier within a given date range.
     * <p>
     * This method calls the repository method {@link MerchandiseRepository#deliveriesByTime} to fetch the list of 
     * merchandise delivered by the specified supplier during the given date range.
     * </p>
     * 
     * @param start the start date of the delivery range.
     * @param end the end date of the delivery range.
     * @param s the {@link Supplier} whose deliveries are to be fetched.
     * @return a list of {@link Merchandise} delivered by the specified supplier within the date range.
     */
    public List<Merchandise> deliveriesByTimeAndSupplier(LocalDate start, LocalDate end, Supplier s) {
        return repo.deliveriesByTime(start, end, s.getSupplierID());
    }

    /**
     * Retrieves a list of {@link Merchandise} available in a specific store.
     * <p>
     * This method calls the repository method {@link MerchandiseRepository#storeInventory} to fetch the list of 
     * merchandise available in the specified store that has a quantity greater than zero.
     * </p>
     * 
     * @param s the {@link Store} whose inventory is to be fetched.
     * @return a list of {@link Merchandise} available in the specified store.
     */
    public List<Merchandise> storeInventory(Store s) {
        return this.repo.storeInventory(s.getStoreID());
    }
}
