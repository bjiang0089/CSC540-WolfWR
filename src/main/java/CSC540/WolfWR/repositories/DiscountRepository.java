package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Discount.DiscountID> {
}
