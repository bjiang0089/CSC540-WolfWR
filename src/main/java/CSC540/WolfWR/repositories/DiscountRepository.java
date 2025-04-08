package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
