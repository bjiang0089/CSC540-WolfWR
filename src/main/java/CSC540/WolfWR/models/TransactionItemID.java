package CSC540.WolfWR.models;

import java.util.Objects;

/**
 * Represents the composite primary key for the `TransactionItem` entity.
 * <p>
 * The `TransactionItemID` class is used to uniquely identify a `TransactionItem` by combining the `transactionID`
 * and `productID` fields. This class is used as the ID class in JPA when defining the composite key for the `TransactionItem` entity.
 * </p>
 */
public class TransactionItemID extends DomainObject {
    /** The unique identifier of the transaction associated with this item. */
    private long transactionID;

    /** The unique identifier of the product associated with this transaction item. */
    private long productID;

    /**
     * Default constructor for `TransactionItemID`.
     * <p>
     * This constructor is used by frameworks (such as JPA) for object-relational mapping (ORM).
     * </p>
     */
    public TransactionItemID () {}

    /**
     * Constructs a `TransactionItemID` with the specified `transactionID` and `productID`.
     * <p>
     * This constructor initializes the composite key with the given `transactionID` and `productID`.
     * </p>
     * 
     * @param transactionID The unique identifier of the transaction.
     * @param productID The unique identifier of the product.
     */
    public TransactionItemID(long transactionID, long productID) {
        this.transactionID = transactionID;
        this.productID = productID;
    }

    /**
     * Returns the unique identifier of the transaction associated with this `TransactionItemID`.
     * 
     * @return The `transactionID` of the associated transaction.
     */
    public long getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the unique identifier of the transaction associated with this `TransactionItemID`.
     * 
     * @param transactionID The transaction ID to set.
     */
    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Returns the unique identifier of the product associated with this `TransactionItemID`.
     * 
     * @return The `productID` of the associated product.
     */
    public long getProductID() {
        return productID;
    }

    /**
     * Sets the unique identifier of the product associated with this `TransactionItemID`.
     * 
     * @param productID The product ID to set.
     */
    public void setProductID(long productID) {
        this.productID = productID;
    }

    /**
     * Compares this `TransactionItemID` to another object for equality.
     * <p>
     * This method returns `true` if the object is a `TransactionItemID` and both the `transactionID` and `productID` fields
     * are equal. This is used to check if two `TransactionItemID` instances refer to the same composite key.
     * </p>
     * 
     * @param o The object to compare this `TransactionItemID` to.
     * @return `true` if the objects are equal, otherwise `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransactionItemID that = (TransactionItemID) o;
        return this.transactionID == that.getTransactionID() && this.productID == that.getProductID();
    }

    /**
     * Returns a hash code value for this `TransactionItemID`.
     * <p>
     * This method computes a hash code using the `transactionID` and `productID` fields. It is used in hash-based collections,
     * such as `HashMap` or `HashSet`, to maintain the uniqueness of the `TransactionItemID`.
     * </p>
     * 
     * @return A hash code value for this `TransactionItemID`.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.transactionID, this.productID);
    }
}