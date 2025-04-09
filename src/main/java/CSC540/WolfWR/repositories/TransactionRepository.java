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

    @Query(value = "SELECT * FROM transaction WHERE member_id = :memberID", nativeQuery = true)
    public List<Transaction> getHistoryByCustomer(@Param("memberID") Long memberID);

    @Query(value =  "SELECT transactionid FROM transaction", nativeQuery = true)
    public List<Long> getIDs();

    @Query(value = "SELECT * FROM transaction WHERE purchase_date BETWEEN :start AND DATE_ADD(:start, INTERVAL 1 day) AND storeID = :storeid", nativeQuery = true)
    public List<Transaction> generateDayStoreReport(@Param("start") LocalDate start, @Param("storeid") Long storeID);

    @Query(value = "SELECT * FROM transaction WHERE purchase_date BETWEEN :start AND DATE_ADD(:start, INTERVAL 1 month) AND storeID = :storeid", nativeQuery = true)
    public List<Transaction> generateMonthStoreReport(@Param("start") LocalDate start, @Param("storeid") Long storeID);

    @Query(value = "SELECT * FROM transaction WHERE purchase_date BETWEEN :start AND DATE_ADD(:start, INTERVAL 1 year) AND storeID = :storeid", nativeQuery = true)
    public List<Transaction> generateYearStoreReport(@Param("start") LocalDate start, @Param("storeid") Long storeID);



    @Query(value = "SELECT * FROM transaction WHERE purchase_date BETWEEN :start AND DATE_ADD(:start, INTERVAL 1 day)", nativeQuery = true)
    public List<Transaction> generateDayReport(@Param("start") LocalDate start);

    @Query(value = "SELECT * FROM transaction WHERE purchase_date BETWEEN :start AND DATE_ADD(:start, INTERVAL 1 month)", nativeQuery = true)
    public List<Transaction> generateMonthReport(@Param("start") LocalDate start);

    @Query(value = "SELECT * FROM transaction WHERE purchase_date BETWEEN :start AND DATE_ADD(:start, INTERVAL 1 year)", nativeQuery = true)
    public List<Transaction> generateYearReport(@Param("start") LocalDate start);

    @Query(value = "SELECT * FROM transaction WHERE purchase_date BETWEEN :start AND :end AND storeid = :storeID", nativeQuery = true)
    public List<Transaction> generateBoundStoreSalesReport(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("storeID") Long storeID);

    @Query(value = "SELECT * FROM transaction WHERE purchase_date BETWEEN :start AND :end", nativeQuery = true)
    public List<Transaction> generateBoundSalesReport(@Param("start") LocalDate start, @Param("end") LocalDate end);

}
