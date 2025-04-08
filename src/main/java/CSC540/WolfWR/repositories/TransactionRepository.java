package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
