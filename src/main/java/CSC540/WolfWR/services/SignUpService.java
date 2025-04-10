package CSC540.WolfWR.services;

import CSC540.WolfWR.models.SignUp;
import CSC540.WolfWR.repositories.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for handling operations related to {@link SignUp} entities.
 * <p>
 * This class extends the generic {@link Services} class and provides CRUD operations for
 * managing {@link SignUp} entities. It interacts with the {@link SignUpRepository} to perform
 * operations such as saving, finding, and deleting {@link SignUp} records from the database.
 * </p>
 * 
 * @see SignUp
 * @see SignUpRepository
 * @see Services
 */
@Transactional
@Component
public class SignUpService extends  Services<SignUp, Long> {

    /**
     * The repository used for performing database operations related to {@link SignUp} entities.
     */
    @Autowired
    private SignUpRepository repo;

    /**
     * Returns the repository used for performing database operations on {@link SignUp} entities.
     * This method is implemented from the abstract {@link Services} class.
     * 
     * @return the {@link SignUpRepository} instance used to interact with the database.
     */
    @Override
    protected JpaRepository<SignUp, Long> getRepo() {
        return this.repo;
    }
}
