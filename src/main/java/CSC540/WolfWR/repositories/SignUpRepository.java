package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing {@link SignUp} entities from the database.
 * <p>
 * This interface extends {@link JpaRepository}, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on {@link SignUp} entities.
 * </p>
 * 
 * <p>
 * This repository is used for managing user sign-up records in the system, allowing for the persistence of
 * data related to a user's registration and store affiliation.
 * </p>
 * 
 * @see SignUp
 */
@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {
}
