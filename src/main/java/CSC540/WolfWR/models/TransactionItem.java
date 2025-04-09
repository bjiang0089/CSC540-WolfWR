package CSC540.WolfWR.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(TransactionItemID.class)
public class TransactionItem extends DomainObject{

    @Id
    @Column(name = "transactionID")
    private long transactionID;

    @Id
    @Column(name = "productID")
    private long productID;

    @Id
    @Column(name = "storeID")
    private long storeID;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "transactionID")
    private Transaction transaction;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "productID", referencedColumnName = "productID"),
            @JoinColumn(name = "storeID", referencedColumnName = "storeID")
    })
    @MapsId
    private Merchandise merch;

    public TransactionItem() {}

    public TransactionItem(Transaction transaction, Merchandise merch) {
        this.transaction = transaction;
        this.merch = merch;

        this.productID = merch.getProductID();
        this.transactionID = transaction.getTransactionID();
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Merchandise getMerch() {
        return merch;
    }

    public void setMerch(Merchandise merch) {
        this.merch = merch;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransactionItem that = (TransactionItem) o;
        return transactionID == that.transactionID && productID == that.productID && Objects.equals(transaction, that.transaction) && Objects.equals(merch, that.merch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, productID, transaction, merch);
    }
}
