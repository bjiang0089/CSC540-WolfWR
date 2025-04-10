package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Staff;
import CSC540.WolfWR.models.Store;
import CSC540.WolfWR.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Transactional
@Component
public class StaffService  extends Services<Staff, Long> {

    @Autowired
    private StaffRepository repo;

    @Override
    protected JpaRepository<Staff, Long> getRepo() {
        return this.repo;
    }

    public long generateID() {
        return Collections.max( repo.getID() ) + 1;
    }

    public List<Staff> findAllByStore(Store s) {
        return repo.findByStore(s.getStoreID());
    }
}
