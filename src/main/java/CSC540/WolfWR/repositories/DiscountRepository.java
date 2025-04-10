package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Discount;
import CSC540.WolfWR.models.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


/**
 * Repository interface for accessing `Discount` entities from the database.
 * <p>
 * This interface extends `JpaRepository`, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on `Discount` entities. The `Discount` entity is identified by a composite key defined by the `DiscountID` class.
 * The repository is managed by Spring Data JPA, which automatically provides implementations for common database operations.
 * </p>
 * 
 * @see Discount
 * @see Discount.DiscountID
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Discount.DiscountID> {

    @Query(value = "SELECT * FROM discount WHERE :date BETWEEN start AND end ", nativeQuery = true)
    public List<Discount> findAllByDate(@Param("date")LocalDate date);

    @Query(value = "SELECT * FROM discount WHERE productID = :productID AND :date BETWEEN start AND end", nativeQuery = true)
    public List<Discount> findByProductIDAndDate(@Param("productID") Long productID, @Param("date") LocalDate date);

}
