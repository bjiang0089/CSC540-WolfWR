package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public class StaffService  extends Services<Staff, Long> {

    private JpaRepository<Staff, Long> repo;

    @Override
    public JpaRepository<Staff, Long> getRepo() {
        return this.repo;
    }
}
