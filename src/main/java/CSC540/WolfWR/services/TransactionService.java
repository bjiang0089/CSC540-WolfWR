package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.models.Transaction;
import CSC540.WolfWR.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Component
public class TransactionService  extends  Services<Transaction, Long> {

    @Autowired
    private TransactionRepository repo;

    @Override
    protected JpaRepository<Transaction, Long> getRepo() {
        return this.repo;
    }

    public List<Transaction> processRewards(Member m, LocalDate end) {
        return this.repo.processRewards(m.getId(), end);
    }
}
