package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Store;
import CSC540.WolfWR.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class StoreService  extends  Services<Store, Long> {

    @Autowired
    private StoreRepository repo;

    @Override
    protected JpaRepository<Store, Long> getRepo() {
        return this.repo;
    }
}
