package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing {@link Staff} entities from the database.
 * <p>
 * This interface extends {@link JpaRepository}, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on {@link Staff} entities. Additionally, it includes custom queries to retrieve specific staff records
 * based on store and role.
 * </p>
 * 
 * <p>
 * This repository is used for managing staff members within the store system, including details like name, age, 
 * address, contact information, role, and employment duration.
 * </p>
 * 
 * @see Staff
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    /**
     * Retrieves a list of {@link Staff} members working as cashiers in a specific store.
     * 
     * @param storeID The ID of the store where the cashiers work.
     * @param title   The title or role of the staff, e.g., "cashier".
     * @return A list of {@link Staff} members who match the provided store ID and title.
     */
    @Query(value = "SELECT * FROM staff WHERE storeID = :storeID AND title = :cashier", nativeQuery = true)
    public List<Staff> findCashier(@Param("storeID") Long storeID, @Param("cashier") String title);
}
