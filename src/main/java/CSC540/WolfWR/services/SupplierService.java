package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Supplier;
import CSC540.WolfWR.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for handling operations related to {@link Supplier} entities.
 * <p>
 * This class extends the generic {@link Services} class and provides CRUD operations for
 * managing {@link Supplier} entities. It interacts with the {@link SupplierRepository} to perform
 * operations such as saving, finding, and deleting {@link Supplier} records from the database.
 * </p>
 * 
 * @see Supplier
 * @see SupplierRepository
 * @see Services
 */
@Transactional
@Component
public class SupplierService  extends  Services<Supplier, Long> {

    /**
     * The repository used for performing database operations related to {@link Supplier} entities.
     */
    @Autowired
    private SupplierRepository repo;

    /**
     * Returns the repository used for performing database operations on {@link Supplier} entities.
     * This method is implemented from the abstract {@link Services} class.
     * 
     * @return the {@link SupplierRepository} instance used to interact with the database.
     */
    @Override
    protected JpaRepository<Supplier, Long> getRepo() {
        return this.repo;
    }
}
