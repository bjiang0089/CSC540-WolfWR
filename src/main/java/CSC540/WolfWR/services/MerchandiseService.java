package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.models.Store;
import CSC540.WolfWR.models.Supplier;
import CSC540.WolfWR.repositories.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Component
public class MerchandiseService extends Services<Merchandise, Long> {

    @Autowired
    private MerchandiseRepository repo;

    @Override
    protected JpaRepository<Merchandise, Long> getRepo() {
        return this.repo;
    }

    public List<Merchandise> deliveriesByTimeAndSupplier(LocalDate start, LocalDate end, Supplier s) {
        return repo.deliveriesByTime(start, end, s.getSupplierID());
    }

    public List<Merchandise> storeInventory(Store s) {
        return this.repo.storeInventory(s.getStoreID());
    }
}
