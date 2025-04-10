package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing {@link Store} entities from the database.
 * <p>
 * This interface extends {@link JpaRepository}, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on {@link Store} entities. It facilitates interactions with the data layer for managing store information.
 * </p>
 * 
 * <p>
 * This repository is used for managing the data associated with stores, including details like store ID, address,
 * contact information, and the store manager.
 * </p>
 * 
 * @see Store
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value =  "SELECT storeID FROM store", nativeQuery = true)
    public List<Long> getIDs();
}
