package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query(value = "SELECT memberid FROM members", nativeQuery = true)
    public List<Long> getIDs();

    @Query(value = "SELECT * from members WHERE is_active = 1", nativeQuery = true)
    public List<Member> getMembers();

}
