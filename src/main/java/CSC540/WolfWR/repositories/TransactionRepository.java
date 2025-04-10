package CSC540.WolfWR.repositories;

import CSC540.WolfWR.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for accessing {@link Transaction} entities from the database.
 * <p>
 * This interface extends {@link JpaRepository}, providing methods for performing CRUD (Create, Read, Update, Delete)
 * operations on {@link Transaction} entities. It includes custom queries for retrieving transaction data based on specific criteria.
 * </p>
 * 
 * <p>
 * This repository facilitates interactions with the data layer to manage and query transactions within the system.
 * It supports the retrieval of transactions by member, filtering by date range for rewards processing, as well as fetching
 * transaction IDs.
 * </p>
 * 
 * @see Transaction
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Retrieves a list of transactions made by a specific member within the past year up until the provided end date.
     * <p>
     * This query is used for processing rewards or loyalty benefits by checking the member's recent purchase history.
     * </p>
     *
     * @param member the ID of the member whose transactions are being queried
     * @param end the end date to limit the transactions, typically the current date or the most recent transaction date
     * @return a list of {@link Transaction} objects that match the criteria
     */
    @Query( value = "SELECT * FROM transaction WHERE member_id = :member AND purchase_date BETWEEN DATE_SUB(:end, INTERVAL 1 year) AND :end", nativeQuery = true)
    public List<Transaction> processRewards(@Param("member") Long member, @Param("end")LocalDate end);

    @Query(value = "SELECT * FROM transaction WHERE member_id = :memberID", nativeQuery = true)
    public List<Transaction> getHistoryByCustomer(@Param("memberID") Long memberID);

    /**
     * Retrieves all transaction IDs in the system.
     * <p>
     * This query fetches the unique transaction IDs for all transactions in the database.
     * </p>
     *
     * @return a list of transaction IDs
     */
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
