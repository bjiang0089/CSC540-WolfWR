package CSC540.WolfWR.services;

import CSC540.WolfWR.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public class TransactionService  extends  Services<Transaction, Long> {

    private JpaRepository<Transaction, Long> repo;

    @Override
    public JpaRepository<Transaction, Long> getRepo() {
        return this.repo;
    }
}
