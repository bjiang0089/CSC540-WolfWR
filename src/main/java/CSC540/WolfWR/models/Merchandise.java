package CSC540.WolfWR.models;

import CSC540.WolfWR.WolfWRApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Represents a merchandise item in the store.
 * <p>
 * This class holds information about a product available in the store, including details such as
 * the product ID, name, quantity, prices, production and expiration dates, as well as its associated
 * supplier and store.
 * </p>
 */
@Entity
public class Merchandise extends DomainObject {

    /** The unique identifier for the product. */
    @Id
    private long productID;

    /** The name of the product. */
    @Column(nullable = false)
    private String productName;

    /** The quantity of the product available in stock. */
    @Min(0)
    @NotNull
    private int quantity;

    /** The price the store pays to the supplier for each unit. */
    @Min(0)
    @NotNull
    private double buyPrice;

    /** The price customers pay for each unit in the store. */
    @Min(0)
    @NotNull
    private double marketPrice;

    /** The production date of the product. */
    @NotNull
    private LocalDate productionDate;

    /** The expiration date of the product. */
    @NotNull
    private LocalDate expirationDate;

    /** The supplier of the product. */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    /** The store that sells the product. */
    @ManyToOne
    @JoinColumn(name = "storeID")
    @NotNull
    private Store store;

    /**
     * Default constructor for the Merchandise class.
     */
    public Merchandise() {}

    /**
     * Constructs a Merchandise object with the specified attributes.
     * 
     * @param productID The unique identifier for the product.
     * @param productName The name of the product.
     * @param quantity The quantity of the product in stock.
     * @param buyPrice The price the store pays to the supplier for each unit.
     * @param marketPrice The price customers pay for each unit in the store.
     * @param productionDate The production date of the product, as a string.
     * @param expirationDate The expiration date of the product, as a string.
     * @param supplier The supplier of the product.
     * @param store The store that sells the product.
     */
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

    /**
     * Returns the unique identifier for the product.
     * 
     * @return The product ID.
     */
    public long getProductID() {
        return productID;
    }

    /**
     * Sets the unique identifier for the product.
     * 
     * @param productID The product ID to set.
     */
    public void setProductID(long productID) {
        this.productID = productID;
    }

    /**
     * Returns the name of the product.
     * 
     * @return The product name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     * 
     * @param productName The product name to set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the quantity of the product in stock.
     * 
     * @return The product quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in stock.
     * 
     * @param quantity The product quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the price the store pays to the supplier for each unit.
     * 
     * @return The buy price of the product.
     */
    public double getBuyPrice() {
        return buyPrice;
    }

    /**
     * Sets the price the store pays to the supplier for each unit.
     * 
     * @param buyPrice The buy price to set.
     */
    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * Returns the price customers pay for each unit in the store.
     * 
     * @return The market price of the product.
     */
    public double getMarketPrice() {
        return marketPrice;
    }

    /**
     * Sets the price customers pay for each unit in the store.
     * 
     * @param marketPrice The market price to set.
     */
    public void setMarketPrice(float marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * Returns the production date of the product.
     * 
     * @return The production date.
     */
    public LocalDate getProductionDate() {
        return productionDate;
    }

    /**
     * Sets the production date of the product.
     * 
     * @param productionDate The production date to set.
     */
    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * Returns the expiration date of the product.
     * 
     * @return The expiration date.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the product.
     * 
     * @param expirationDate The expiration date to set.
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the supplier of the product.
     * 
     * @return The supplier of the product.
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets the supplier of the product.
     * 
     * @param supplier The supplier to set.
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Returns the store that sells the product.
     * 
     * @return The store selling the product.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets the store that sells the product.
     * 
     * @param store The store to set.
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Returns a string representation of the merchandise.
     * 
     * @return A formatted string with the product details.
     */
    public String toString() {
        return String.format("Product ID: %d, Name: %s, Quantity: %d, Buy Price: %f, MarketPrice: %f, Production Date: %s, Expiration Date: %s, Supplier Name: %s, Store ID: %d\n",
                this.getProductID(), this.getProductName(), this.getQuantity(), this.getBuyPrice(), this.getMarketPrice(), this.getProductionDate().toString(),
                this.getExpirationDate().toString(), this.getSupplier().getSupplierName(), this.getStore().getStoreID());
    }
}