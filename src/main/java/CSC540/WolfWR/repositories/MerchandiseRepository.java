package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    @Query(value = "SELECT * FROM Merchandise WHERE supplierID = :supplierID AND production_date BETWEEN :start AND :end", nativeQuery = true)
    public List<Merchandise> deliveriesByTime(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("supplierID")Long supplierID);


    @Query(value = "SELECT * FROM merchandise WHERE storeID = :store AND quantity > 0", nativeQuery = true)
    public List<Merchandise> storeInventory(@Param("store") Long storeID);

    @Query(value = "SELECT * FROM merchandise WHERE storeID = :store", nativeQuery = true)
    public List<Merchandise> totalStoreInventory(@Param("store") Long storeID);

    @Query(value = "SELECT productid FROM merchandise", nativeQuery = true)
    public List<Long> getIDs();
}
