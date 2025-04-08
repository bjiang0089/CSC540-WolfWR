package CSC540.WolfWR.services;

import CSC540.WolfWR.models.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;

public class SignUpService extends  Services<SignUp, Long> {

    private JpaRepository<SignUp, Long> repo;

    @Override
    public JpaRepository<SignUp, Long> getRepo() {
        return this.repo;
    }
}
