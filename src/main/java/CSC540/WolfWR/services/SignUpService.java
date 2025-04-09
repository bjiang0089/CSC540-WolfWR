package CSC540.WolfWR.services;

import CSC540.WolfWR.models.SignUp;
import CSC540.WolfWR.repositories.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SignUpService extends  Services<SignUp, Long> {

    @Autowired
    private SignUpRepository repo;

    @Override
    protected JpaRepository<SignUp, Long> getRepo() {
        return this.repo;
    }
}
