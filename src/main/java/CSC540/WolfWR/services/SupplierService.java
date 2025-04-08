package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public class SupplierService  extends  Services<Supplier, Long> {

    private JpaRepository<Supplier, Long> repo;

    @Override
    public JpaRepository<Supplier, Long> getRepo() {
        return this.repo;
    }
}
