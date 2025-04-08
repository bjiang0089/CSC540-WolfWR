package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
