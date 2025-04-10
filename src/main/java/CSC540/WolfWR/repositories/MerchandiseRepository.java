package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for accessing {@link Merchandise} entities from the database.
 * <p>
 * This interface extends {@link JpaRepository}, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on {@link Merchandise} entities. Additionally, it defines custom queries to retrieve merchandise data
 * based on specific criteria.
 * </p>
 * 
 * <p>
 * This interface includes:
 * <ul>
 *     <li>{@link #deliveriesByTime(LocalDate, LocalDate, Long)}: Retrieves a list of merchandise delivered by a specific supplier
 *         within a given time range.</li>
 *     <li>{@link #storeInventory(Long)}: Retrieves a list of merchandise available in a specific store where the quantity is greater than zero.</li>
 * </ul>
 * </p>
 * 
 * @see Merchandise
 */
@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    /**
     * Retrieves a list of {@link Merchandise} delivered by a specific supplier within a given time range.
     * 
     * @param start The start date of the delivery period.
     * @param end The end date of the delivery period.
     * @param supplierID The ID of the supplier.
     * @return A list of {@link Merchandise} objects delivered by the supplier between the specified dates.
     */
    @Query(value = "SELECT * FROM Merchandise WHERE supplierID = :supplierID AND production_date BETWEEN :start AND :end", nativeQuery = true)
    public List<Merchandise> deliveriesByTime(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("supplierID")Long supplierID);


    /**
     * Retrieves a list of merchandise available in a specific store where the quantity is greater than zero.
     * 
     * @param storeID The ID of the store.
     * @return A list of {@link Merchandise} objects available in the specified store.
     */
    @Query(value = "SELECT * FROM merchandise WHERE storeID = :store AND quantity > 0", nativeQuery = true)
    public List<Merchandise> storeInventory(@Param("store") Long storeID);

    @Query(value = "SELECT productid FROM merchandise", nativeQuery = true)
    public List<Long> getIDs();
}
