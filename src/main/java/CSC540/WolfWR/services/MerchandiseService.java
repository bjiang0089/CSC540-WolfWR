package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public class MerchandiseService extends Services<Merchandise, Long> {

    private JpaRepository<Merchandise, Long> repo;

    @Override
    public JpaRepository<Merchandise, Long> getRepo() {
        return this.repo;
    }
}
