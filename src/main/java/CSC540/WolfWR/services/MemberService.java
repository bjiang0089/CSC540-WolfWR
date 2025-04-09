package CSC540.WolfWR.services;


import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class MemberService extends Services<Member, Long> {

    @Autowired
    private MemberRepository repo;

    @Override
    protected JpaRepository<Member, Long> getRepo() {
        return this.repo;
    }
}
