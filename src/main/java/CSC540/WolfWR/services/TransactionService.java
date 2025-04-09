package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Transaction;
import CSC540.WolfWR.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TransactionService  extends  Services<Transaction, Long> {

    @Autowired
    private TransactionRepository repo;

    @Override
    protected JpaRepository<Transaction, Long> getRepo() {
        return this.repo;
    }
}
