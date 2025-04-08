package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public class StoreService  extends  Services<Store, Long> {

    private JpaRepository<Store, Long> repo;

    @Override
    public JpaRepository<Store, Long> getRepo() {
        return this.repo;
    }
}
