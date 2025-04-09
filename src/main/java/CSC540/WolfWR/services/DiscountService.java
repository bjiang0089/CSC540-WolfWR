package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Discount;
import CSC540.WolfWR.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DiscountService extends Services<Discount, Discount.DiscountID>{

    @Autowired
    private DiscountRepository repo;


    @Override
    protected JpaRepository<Discount, Discount.DiscountID> getRepo() {
        return this.repo;
    }
}
