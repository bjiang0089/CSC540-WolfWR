package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT memberid FROM members", nativeQuery = true)
    public List<Long> getIDs();

    @Query(value = "SELECT * from members WHERE is_active = 0", nativeQuery = true)
    public List<Member> getMembers();

}
