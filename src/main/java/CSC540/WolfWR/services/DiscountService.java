package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class DiscountService extends Services<Discount, Discount.DiscountID>{

    @Autowired
    private JpaRepository<Discount, Discount.DiscountID> repo;


    @Override
    public JpaRepository<Discount, Discount.DiscountID> getRepo() {
        return this.repo;
    }
}
