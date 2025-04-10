package CSC540.WolfWR.models;

import CSC540.WolfWR.WolfWRApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.*;

/**
 * Represents a transaction in the system.
 * <p>
 * The `Transaction` class stores information about a transaction made by a member 
 * at a specific store, including transaction details such as purchase date, 
 * total price, the cashier handling the transaction, and the list of items involved.
 * </p>
 */
@Entity
public class Transaction extends DomainObject {
    /** The unique identifier for the transaction. */
    @Id
    private long transactionID;

    /** The store where the transaction took place. */
    @ManyToOne
    @JoinColumn(name = "storeID")
    @NotNull
    private Store store;

    /** The member associated with the transaction. */
    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    /** The cashier who processed the transaction. */
    @ManyToOne
    @JoinColumn(name = "cashierID")
    @NotNull
    private Staff cashierID;

    /** The date of the transaction. */
    @NotNull
    private LocalDate purchaseDate;

    /** The total price of the transaction. */
    private double totalPrice;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<TransactionItem> productList;

    /** The list of items purchased in the transaction. */
    @OneToMany(cascade = CascadeType.ALL)
    private List<TransactionItem> productList;

    /**
     * Default constructor for the `Transaction` class.
     * <p>
     * This constructor is used by frameworks (such as JPA) for object-relational mapping (ORM).
     * </p>
     */
    public Transaction() {}

    /**
     * Constructs a `Transaction` object with the specified details.
     * <p>
     * This constructor initializes the transaction with its ID, associated store, member, 
     * cashier, purchase date, total price, and a list of purchased items.
     * </p>
     * 
     * @param transactionID The unique identifier for the transaction.
     * @param store The store where the transaction occurred.
     * @param member The member involved in the transaction.
     * @param cashierID The cashier who processed the transaction.
     * @param purchaseDate The date of the transaction.
     * @param totalPrice The total price of the transaction.
     * @param productList The list of items purchased in the transaction.
     */
    public Transaction(long transactionID, Store store, Member member, Staff cashierID,
                       String purchaseDate, float totalPrice, List<TransactionItem> productList) {
        this.transactionID = transactionID;
        this.store = store;
        this.member = member;
        this.cashierID = cashierID;
        this.purchaseDate = LocalDate.parse(purchaseDate);
        this.totalPrice = totalPrice;
        this.productList = productList;
    }

    /**
     * Constructs a `Transaction` object with the specified details, excluding the product list.
     * <p>
     * This constructor initializes the transaction with its ID, associated store, member, 
     * cashier, purchase date, and total price. An empty list of items is initialized for the transaction.
     * </p>
     * 
     * @param transactionID The unique identifier for the transaction.
     * @param store The store where the transaction occurred.
     * @param member The member involved in the transaction.
     * @param cashierID The cashier who processed the transaction.
     * @param purchaseDate The date of the transaction.
     * @param totalPrice The total price of the transaction.
     */
    public Transaction(long transactionID, Store store, Member member, Staff cashierID,
                       String purchaseDate, double totalPrice) {
        this.transactionID = transactionID;
        this.store = store;
        this.member = member;
        this.cashierID = cashierID;
        this.purchaseDate = LocalDate.parse(purchaseDate, WolfWRApp.timeFormat);
        this.totalPrice = totalPrice;
        this.productList = new ArrayList<TransactionItem>();
    }

    /**
     * Returns the cashier who processed the transaction.
     * 
     * @return The cashier associated with the transaction.
     */
    public Staff getCashierID() {
        return cashierID;
    }

    /**
     * Sets the cashier for the transaction.
     * 
     * @param cashierID The cashier to associate with the transaction.
     */
    public void setCashierID(Staff cashierID) {
        this.cashierID = cashierID;
    }

    /**
     * Returns the member associated with the transaction.
     * 
     * @return The member associated with the transaction.
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the member for the transaction.
     * 
     * @param member The member to associate with the transaction.
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Returns the store where the transaction occurred.
     * 
     * @return The store where the transaction occurred.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets the store for the transaction.
     * 
     * @param store The store to associate with the transaction.
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Returns the unique identifier for the transaction.
     * 
     * @return The transaction ID.
     */
    public long getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the unique identifier for the transaction.
     * 
     * @param transactionID The transaction ID to set.
     */
    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Returns the date when the transaction occurred.
     * 
     * @return The transaction date.
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the date when the transaction occurred.
     * 
     * @param purchaseDate The date to set for the transaction.
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Returns the total price of the transaction.
     * 
     * @return The total price of the transaction.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price for the transaction.
     * 
     * @param totalPrice The total price to set for the transaction.
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Returns the list of items purchased in the transaction.
     * 
     * @return The list of items in the transaction.
     */
    public List<TransactionItem> getProductList() {
        return productList;
    }

    /**
     * Sets the list of items purchased in the transaction.
     * 
     * @param productList The list of items to set for the transaction.
     */
    public void setProductList(List<TransactionItem> productList) {
        this.productList = productList;
    }

    /**
     * Adds a merchandise item to the transaction.
     * <p>
     * This method creates a new `TransactionItem` object that associates the specified merchandise 
     * with the current transaction, and adds it to the list of products.
     * </p>
     * 
     * @param m The merchandise item to add to the transaction.
     */
    public void addMerchandise(Merchandise m){
        TransactionItem it = new TransactionItem(this, m);
        this.productList.add(it);
    }


//    public List<Merchandise> getProductList() {
//        return productList;
//    }
//
//    public void setProductList(List<Merchandise> productList) {
//        this.productList = productList;
//    }
//    public void addMerchandise(Merchandise m){
//        this.productList.add(m);
//    }
}
