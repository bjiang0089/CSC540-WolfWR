package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {
}
