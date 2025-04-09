package CSC540.WolfWR.models;

import CSC540.WolfWR.WolfWRApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Transaction extends DomainObject {
    @Id
    private long transactionID;

    @ManyToOne
    @JoinColumn(name = "storeID")
    @NotNull
    private Store store;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;


    @ManyToOne
    @JoinColumn(name = "cashierID")
    @NotNull
    private Staff cashierID;

    @NotNull
    private LocalDate purchaseDate;

    private double totalPrice;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<TransactionItem> productList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TransactionItem> productList;

    public Transaction() {}

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

    public Staff getCashierID() {
        return cashierID;
    }

    public void setCashierID(Staff cashierID) {
        this.cashierID = cashierID;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public List<TransactionItem> getProductList() {
        return productList;
    }

    public void setProductList(List<TransactionItem> productList) {
        this.productList = productList;
    }

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
