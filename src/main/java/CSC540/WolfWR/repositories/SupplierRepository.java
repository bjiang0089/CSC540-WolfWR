package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing {@link Supplier} entities from the database.
 * <p>
 * This interface extends {@link JpaRepository}, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on {@link Supplier} entities. It facilitates interactions with the data layer for managing supplier information.
 * </p>
 * 
 * <p>
 * This repository is used for managing the data associated with suppliers, including supplier ID, name, contact details,
 * and location.
 * </p>
 * 
 * @see Supplier
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
