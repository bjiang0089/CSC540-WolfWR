package CSC540.WolfWR.services;


import CSC540.WolfWR.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public class MemberService extends Services<Member, Long> {

    private JpaRepository<Member, Long> repo;

    @Override
    public JpaRepository<Member, Long> getRepo() {
        return this.repo;
    }
}
