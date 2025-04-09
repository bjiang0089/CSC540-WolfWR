package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Supplier;
import CSC540.WolfWR.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SupplierService  extends  Services<Supplier, Long> {

    @Autowired
    private SupplierRepository repo;

    @Override
    protected JpaRepository<Supplier, Long> getRepo() {
        return this.repo;
    }
}
