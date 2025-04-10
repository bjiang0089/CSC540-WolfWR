package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing `Member` entities from the database.
 * <p>
 * This interface extends `JpaRepository`, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on `Member` entities. The `Member` entity is identified by a `Long` type primary key.
 * Spring Data JPA automatically provides implementations for common database operations.
 * </p>
 * 
 * @see Member
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
