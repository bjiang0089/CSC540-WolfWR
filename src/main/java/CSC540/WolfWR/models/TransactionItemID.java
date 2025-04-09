package CSC540.WolfWR.models;

import java.util.Objects;

public class TransactionItemID extends DomainObject {
    private long transactionID;
    private long productID;

    public TransactionItemID () {}

    public TransactionItemID(long transactionID, long productID) {
        this.transactionID = transactionID;
        this.productID = productID;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransactionItemID that = (TransactionItemID) o;
        return this.transactionID == that.getTransactionID() && this.productID == that.getProductID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.transactionID, this.productID);
    }
}