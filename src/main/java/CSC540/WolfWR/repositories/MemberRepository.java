package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
