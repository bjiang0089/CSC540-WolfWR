package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
