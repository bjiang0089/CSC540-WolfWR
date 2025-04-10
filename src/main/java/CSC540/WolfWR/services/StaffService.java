package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Staff;
import CSC540.WolfWR.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for handling operations related to {@link Staff} entities.
 * <p>
 * This class extends the generic {@link Services} class and provides CRUD operations for
 * managing {@link Staff} entities. It interacts with the {@link StaffRepository} to perform
 * operations such as saving, finding, and deleting {@link Staff} records from the database.
 * </p>
 * 
 * @see Staff
 * @see StaffRepository
 * @see Services
 */
@Transactional
@Component
public class StaffService  extends Services<Staff, Long> {

    /**
     * The repository used for performing database operations related to {@link Staff} entities.
     */
    @Autowired
    private StaffRepository repo;

    /**
     * Returns the repository used for performing database operations on {@link Staff} entities.
     * This method is implemented from the abstract {@link Services} class.
     * 
     * @return the {@link StaffRepository} instance used to interact with the database.
     */
    @Override
    protected JpaRepository<Staff, Long> getRepo() {
        return this.repo;
    }
}
