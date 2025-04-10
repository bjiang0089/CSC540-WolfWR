package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Store;
import CSC540.WolfWR.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for handling operations related to {@link Store} entities.
 * <p>
 * This class extends the generic {@link Services} class and provides CRUD operations for
 * managing {@link Store} entities. It interacts with the {@link StoreRepository} to perform
 * operations such as saving, finding, and deleting {@link Store} records from the database.
 * </p>
 * 
 * @see Store
 * @see StoreRepository
 * @see Services
 */
@Transactional
@Component
public class StoreService  extends  Services<Store, Long> {

    /**
     * The repository used for performing database operations related to {@link Store} entities.
     */
    @Autowired
    private StoreRepository repo;

    /**
     * Returns the repository used for performing database operations on {@link Store} entities.
     * This method is implemented from the abstract {@link Services} class.
     * 
     * @return the {@link StoreRepository} instance used to interact with the database.
     */
    @Override
    protected JpaRepository<Store, Long> getRepo() {
        return this.repo;
    }
}
