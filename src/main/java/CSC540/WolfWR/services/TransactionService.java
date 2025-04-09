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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<Staff> findCashier(Store s) {
        return staffRepo.findCashier(s.getStoreID(), Staff.Title.BILLING.name());
    }

    public long generateID() {
        List<Long> ids = repo.getIDs();
        Random rand = new Random();
        long id = rand.nextLong() % 1009;
        id = Math.abs(id);
        while ( ids.contains( id % 1009 ) ) {
            id = rand.nextLong() % 1009;
            id = Math.abs(id);
        }
        return id;
    }
}
