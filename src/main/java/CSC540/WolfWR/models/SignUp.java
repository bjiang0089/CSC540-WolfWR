package CSC540.WolfWR.models;

import CSC540.WolfWR.WolfWRApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
@Entity
public class SignUp extends DomainObject{

    @Id
    @Column(name = "memberID")
    private long memberID;  // Use memberID as the primary key for SignUp

    @ManyToOne
    @NotNull
    @JoinColumn(name = "storeID")
    private Store store;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId  // Maps memberID as the primary key of SignUp
//    @NotNull
//    @JoinColumn(name = "memberID", insertable = false, updatable = false)  // Don't insert or update the memberID here
//    private Member member;

    @NotNull
    private LocalDate signUpDate;

    // Constructor with parameters
    public SignUp(Store store,  String signUpDate, Member member) {
        this.memberID = member.getId(); // Set memberID from the existing Member's ID
        this.store = store;
//        this.member = member;  // Reference the existing Member
        this.signUpDate = LocalDate.parse(signUpDate, WolfWRApp.timeFormat);
    }

    // Default constructor
    public SignUp() {}


    // Getters and Setters
    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }

//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
