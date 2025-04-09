package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query( value = "SELECT * FROM transaction WHERE member_id = :member AND purchase_date BETWEEN DATE_SUB(:end, INTERVAL 1 year) AND :end", nativeQuery = true)
    public List<Transaction> processRewards(@Param("member") Long member, @Param("end")LocalDate end);

    @Query(value =  "SELECT transactionid FROM transaction", nativeQuery = true)
    public List<Long> getIDs();
}
