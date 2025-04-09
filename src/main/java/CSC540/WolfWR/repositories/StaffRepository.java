package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = "SELECT * FROM staff WHERE storeID = :storeID AND title = :cashier", nativeQuery = true)
    public List<Staff> findCashier(@Param("storeID") Long storeID, @Param("cashier") int title);
}
