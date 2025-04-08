package CSC540.WolfWR.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    private float totalPrice;

    @OneToMany
    private Set<Merchandise> productList;

    public Transaction() {}

    public Transaction(long transactionID, Store store, Member member, Staff cashierID,
                       LocalDate purchaseDate, float totalPrice, Set<Merchandise> productList) {
        this.transactionID = transactionID;
        this.store = store;
        this.member = member;
        this.cashierID = cashierID;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
        this.productList = productList;
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
}
