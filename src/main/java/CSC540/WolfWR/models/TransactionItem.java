package CSC540.WolfWR.models;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents an item in a transaction.
 * <p>
 * The `TransactionItem` class is used to associate a product (merchandise) with a specific transaction.
 * Each `TransactionItem` contains the `transactionID` and `productID`, linking a transaction to a product.
 * </p>
 */
@Entity
@IdClass(TransactionItemID.class)
public class TransactionItem extends DomainObject{
    /** The unique identifier of the transaction this item belongs to. */
    @Id
    @Column(name = "transactionID")
    private long transactionID;

    /** The unique identifier of the product (merchandise) in the transaction. */
    @Id
    @Column(name = "productID")
    private long productID;

    /** The transaction associated with this item. */
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "transactionID")
    private Transaction transaction;

    /** The merchandise associated with this item in the transaction. */
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "productID")
    private Merchandise merch;

    /**
     * Default constructor for the `TransactionItem` class.
     * <p>
     * This constructor is used by frameworks (such as JPA) for object-relational mapping (ORM).
     * </p>
     */
    public TransactionItem() {}

    /**
     * Constructs a `TransactionItem` object with the specified transaction and merchandise.
     * <p>
     * This constructor initializes the `TransactionItem` with the given transaction and merchandise.
     * It automatically sets the `productID` and `transactionID` based on the provided objects.
     * </p>
     * 
     * @param transaction The transaction this item belongs to.
     * @param merch The merchandise item associated with the transaction.
     */
    public TransactionItem(Transaction transaction, Merchandise merch) {
        this.transaction = transaction;
        this.merch = merch;

        this.productID = merch.getProductID();
        this.transactionID = transaction.getTransactionID();
    }

    /**
     * Returns the unique identifier of the transaction associated with this item.
     * 
     * @return The `transactionID` of the associated transaction.
     */
    public long getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the unique identifier of the transaction associated with this item.
     * 
     * @param transactionID The transaction ID to associate with this item.
     */
    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Returns the unique identifier of the product (merchandise) associated with this item.
     * 
     * @return The `productID` of the associated merchandise.
     */
    public long getProductID() {
        return productID;
    }

    /**
     * Sets the unique identifier of the product (merchandise) associated with this item.
     * 
     * @param productID The product ID to associate with this item.
     */
    public void setProductID(long productID) {
        this.productID = productID;
    }

    /**
     * Returns the transaction associated with this item.
     * 
     * @return The transaction associated with this item.
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Sets the transaction associated with this item.
     * 
     * @param transaction The transaction to associate with this item.
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Returns the merchandise associated with this item.
     * 
     * @return The merchandise associated with this item.
     */
    public Merchandise getMerch() {
        return merch;
    }

    /**
     * Sets the merchandise associated with this item.
     * 
     * @param merch The merchandise to associate with this item.
     */
    public void setMerch(Merchandise merch) {
        this.merch = merch;
    }

    /**
     * Compares this `TransactionItem` to another object for equality.
     * <p>
     * This method returns `true` if the object is a `TransactionItem` and has the same `transactionID` 
     * and `productID`, and if both the `transaction` and `merch` objects are also equal.
     * </p>
     * 
     * @param o The object to compare this `TransactionItem` to.
     * @return `true` if the objects are equal, otherwise `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransactionItem that = (TransactionItem) o;
        return transactionID == that.getTransactionID() && productID == that.getProductID() &&
                Objects.equals(transaction, that.transaction) && Objects.equals(merch, that.merch);
    }

    /**
     * Returns a hash code value for this `TransactionItem`.
     * <p>
     * This method returns a hash code that is computed based on the `transactionID`, `productID`, 
     * and the hash codes of the `transaction` and `merch` objects.
     * </p>
     * 
     * @return A hash code value for this `TransactionItem`.
     */
    @Override
    public int hashCode() {
        return Objects.hash(transactionID, productID, transaction, merch);
    }
}
