package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value =  "SELECT storeID FROM store", nativeQuery = true)
    public List<Long> getIDs();

    @Query(value = "SELECT * from store", nativeQuery = true)
    public List<Store> getAllStores();
}
