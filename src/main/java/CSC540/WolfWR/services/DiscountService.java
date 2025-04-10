package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Discount;
import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing {@link Discount} entities.
 * <p>
 * This service provides methods for interacting with the data layer to perform CRUD (Create, Read, Update, Delete)
 * operations on {@link Discount} entities, using the {@link DiscountRepository}.
 * </p>
 * <p>
 * It extends the {@link Services} class to inherit common CRUD functionality. It is annotated with {@link Component}
 * to make it a Spring-managed bean and {@link Transactional} to ensure that the database operations are transactional.
 * </p>
 * 
 * @see Discount
 * @see DiscountRepository
 * @see Services
 */
@Component
@Transactional
public class DiscountService extends Services<Discount, Discount.DiscountID>{

    /**
     * The repository used for accessing and managing {@link Discount} entities in the database.
     */
    @Autowired
    private DiscountRepository repo;


    /**
     * Returns the repository associated with {@link Discount} entities.
     * <p>
     * This method overrides the {@link Services#getRepo()} method to provide the specific repository for {@link Discount}.
     * </p>
     * 
     * @return the {@link DiscountRepository} instance used to interact with the {@link Discount} entities.
     */
    @Override
    protected JpaRepository<Discount, Discount.DiscountID> getRepo() {
        return this.repo;
    }

    public List<Discount> findByDate(LocalDate date) {
        return repo.findAllByDate(date);
    }

    public List<Discount> findByProductIDAndDate(Merchandise m, LocalDate date) {
        return repo.findByProductIDAndDate(m.getProductID(), date);
    }


}
