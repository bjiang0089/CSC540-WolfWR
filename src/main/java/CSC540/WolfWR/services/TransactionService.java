package CSC540.WolfWR.services;

import CSC540.WolfWR.models.*;
import CSC540.WolfWR.repositories.MerchandiseRepository;
import CSC540.WolfWR.repositories.StaffRepository;
import CSC540.WolfWR.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Transactional
@Component
public class TransactionService  extends  Services<Transaction, Long> {

    @Autowired
    private TransactionRepository repo;

    @Autowired
    private MerchandiseRepository merchRepo;

    @Autowired
    private StaffRepository staffRepo;

    @Override
    protected JpaRepository<Transaction, Long> getRepo() {
        return this.repo;
    }

    public List<Transaction> processRewards(Member m, LocalDate end) {
        return this.repo.processRewards(m.getId(), end);
    }

    @Transactional
    public void completePurchase(Transaction t) {
        List<TransactionItem> cart = t.getProductList();
        for (TransactionItem ti: cart) {
            Merchandise m = ti.getMerch();
            // Decrement the quantity
            m.setQuantity( m.getQuantity() - 1 );
            merchRepo.save( m );
        }

        repo.save(t);
    }

    public void generateBoundStoreSalesReport(LocalDate start, LocalDate end, Store store) {
        List<Transaction> trans = repo.generateBoundStoreSalesReport(start, end, store.getStoreID());
        double total = 0;
        int count = 0;
        System.out.println();
        for (Transaction t: trans) {
            System.out.printf("Trans ID: %4d. Total: $%4.2f\n", t.getTransactionID(), t.getTotalPrice());
            total += t.getTotalPrice();
            count++;
        }
        System.out.println();
        System.out.printf("Between %s and %s, store %4d has completed %d transactions for a total of $%4.2f in sales.\n\n",
                start.toString(), end.toString(), store.getStoreID(), count, total);
    }

    public void generateBoundSalesReport(LocalDate start, LocalDate end) {
        List<Transaction> trans = repo.generateBoundSalesReport(start, end);
        double total = 0;
        int count = 0;
        System.out.println();
        for (Transaction t: trans) {
            System.out.printf("Trans ID: %4d. Total: $%4.2f\n", t.getTransactionID(), t.getTotalPrice());
            total += t.getTotalPrice();
            count++;
        }
        System.out.println();
        System.out.printf("Between %s and %s, %d transactions were completed company-wide for a total of $%4.2f in sales.\n\n",
                start.toString(), end.toString(), count, total);
    }

    public void generateStoreSalesReport(String timeframe, LocalDate start, Store store) {
        List<Transaction> trans = null;
        switch (timeframe) {
            case "day":
                trans = repo.generateDayStoreReport(start, store.getStoreID());
                break;
            case "month":
                trans = repo.generateMonthStoreReport(start, store.getStoreID());
                break;
            case "year":
                trans = repo.generateYearStoreReport(start, store.getStoreID());
                break;
            default:
                System.out.println("Invalid Input\n");
                return;
        }

        double total = 0;
        int count = 0;
        System.out.println();
        for (Transaction t: trans) {
            System.out.printf("Trans ID: %4d. Total: $%4.2f\n", t.getTransactionID(), t.getTotalPrice());
            total += t.getTotalPrice();
            count++;
        }
        System.out.println();
        System.out.printf("In a 1 %s timespan starting from %s, store %4d has completed %d transactions for a total of $%4.2f in sales.\n\n",
                timeframe, start.toString(), store.getStoreID(), count, total);
    }

    public void generateGlobalSalesReport(String timeframe, LocalDate start) {
        List<Transaction> trans = null;
        switch (timeframe) {
            case "day":
                trans = repo.generateDayReport(start);
                break;
            case "month":
                trans = repo.generateMonthReport(start);
                break;
            case "year":
                trans = repo.generateYearReport(start);
                break;
            default:
                System.out.println("Invalid Input\n");
                return;
        }

        double total = 0;
        int count = 0;
        System.out.println();
        for (Transaction t: trans) {
            System.out.printf("Trans ID: %4d. Store: %4d Total: $%4.2f\n",
                    t.getTransactionID(), t.getStore().getStoreID(), t.getTotalPrice());

            total += t.getTotalPrice();
            count++;
        }
        System.out.println();
        System.out.printf("In a 1 %s timespan starting from %s, %4d transactions have been completed company-wide for a total of $%4.2f in sales.\n\n",
                timeframe, start.toString(), count, total);
    }

    public List<Staff> findCashier(Store s) {
        return staffRepo.findCashier(s.getStoreID(), Staff.Title.BILLING.ordinal());
    }
    public List<Transaction> getHistoryByCustomer(Member m) {
        return repo.getHistoryByCustomer(m.getId());
    }

    public long generateID() {
        List<Long> ids = repo.getIDs();
        return Collections.max(ids) + 1;
    }
}
