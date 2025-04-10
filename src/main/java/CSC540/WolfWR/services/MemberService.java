package CSC540.WolfWR.services;


import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@Component
public class MemberService extends Services<Member, Long> {

    @Autowired
    private MemberRepository repo;

    @Override
    protected JpaRepository<Member, Long> getRepo() {
        return this.repo;
    }

    public long generateID() {
        List<Long> ids = repo.getIDs();
        return Collections.max(ids) + 1;
    }

    public List<Member> viewMembers() {
        return repo.getMembers();
    }
}
