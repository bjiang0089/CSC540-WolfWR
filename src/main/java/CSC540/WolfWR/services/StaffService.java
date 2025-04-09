package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Staff;
import CSC540.WolfWR.repositories.StaffRepository;
import CSC540.WolfWR.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class StaffService  extends Services<Staff, Long> {

    @Autowired
    private StaffRepository repo;

    @Override
    protected JpaRepository<Staff, Long> getRepo() {
        return this.repo;
    }
}
