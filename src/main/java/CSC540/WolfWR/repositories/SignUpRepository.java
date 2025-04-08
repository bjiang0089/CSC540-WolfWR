package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignUpRepository extends JpaRepository<SignUp, Long> {
}
