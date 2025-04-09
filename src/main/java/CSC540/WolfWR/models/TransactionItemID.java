package CSC540.WolfWR.models;

import java.util.Objects;

public class TransactionItemID extends DomainObject {
    private long transactionID;
    private long productID;
    private long storeID;

    public TransactionItemID () {}

    public TransactionItemID(long transactionID, long productID, long storeID) {
        this.transactionID = transactionID;
        this.productID = productID;
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

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransactionItemID that = (TransactionItemID) o;
        return this.transactionID == that.getTransactionID() &&
                this.productID == that.getProductID() &&
                this.storeID == that.getStoreID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.transactionID, this.productID, this.storeID);
    }
}