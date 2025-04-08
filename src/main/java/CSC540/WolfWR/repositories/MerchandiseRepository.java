package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
}
