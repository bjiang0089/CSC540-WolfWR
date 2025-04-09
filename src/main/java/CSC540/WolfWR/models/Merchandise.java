package CSC540.WolfWR.models;

import CSC540.WolfWR.WolfWRApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@IdClass(MerchandiseID.class)
public class Merchandise extends DomainObject {

    @Id
    private long productID;

    @Id
    private long storeID;

    @ManyToOne
    @NotNull
    @MapsId
    @JoinColumn(name = "storeID")
    private Store store;

    @Column(nullable = false)
    private String productName;

    @Min(0)
    @NotNull
    private int quantity;

    // The price the store pays for each unit from supplier
    @Min(0)
    @NotNull
    private double buyPrice;

    // The price customers pay for each unit from the store
    @Min(0)
    @NotNull
    private double marketPrice;

    @NotNull
    private LocalDate productionDate;

    @NotNull
    private LocalDate expirationDate;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    public Merchandise() {}

    public Merchandise(long productID, String productName, int quantity, double buyPrice, double marketPrice,
                       String productionDate, String expirationDate, Supplier supplier, Store store) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.marketPrice = marketPrice;
        this.productionDate = LocalDate.parse(productionDate, WolfWRApp.timeFormat);
        this.expirationDate = LocalDate.parse(expirationDate, WolfWRApp.timeFormat);
        this.supplier = supplier;
        this.store = store;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Merchandise that = (Merchandise) o;
        return productID == that.getProductID() && storeID == that.getStoreID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, storeID);
    }
}
