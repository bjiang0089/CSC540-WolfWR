package CSC540.WolfWR.models;

import java.util.Objects;

public class MerchandiseID {
    private long productID;
    private long storeID;

    public MerchandiseID(){}

    public MerchandiseID(long productID, long storeID) {
        this.productID = productID;
        this.storeID = storeID;
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
        MerchandiseID that = (MerchandiseID) o;
        return productID == that.getProductID() && storeID == that.getStoreID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.productID, this.storeID);
    }
}
