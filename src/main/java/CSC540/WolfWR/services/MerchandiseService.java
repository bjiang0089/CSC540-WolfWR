package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Merchandise;
import CSC540.WolfWR.repositories.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class MerchandiseService extends Services<Merchandise, Long> {

    @Autowired
    private MerchandiseRepository repo;

    @Override
    protected JpaRepository<Merchandise, Long> getRepo() {
        return this.repo;
    }
}
