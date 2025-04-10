package CSC540.WolfWR.models;

import CSC540.WolfWR.WolfWRApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Represents the signup information of a member at a specific store.
 * <p>
 * This class stores the information about a member signing up for a store, including the 
 * store they signed up at, the sign-up date, and the associated member ID.
 * </p>
 */
@Entity
public class SignUp extends DomainObject{

    /** The unique identifier for the member who signed up. */
    @Id
    @Column(name = "memberID")
    private long memberID;  // Use memberID as the primary key for SignUp

    /** The store where the member signed up. */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "storeID")
    private Store store;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId  // Maps memberID as the primary key of SignUp
//    @NotNull
//    @JoinColumn(name = "memberID", insertable = false, updatable = false)  // Don't insert or update the memberID here
//    private Member member;

    /** The date when the member signed up. */
    @NotNull
    private LocalDate signUpDate;

    /**
     * Constructs a SignUp object with the specified store, sign-up date, and associated member.
     * <p>
     * The member's ID is extracted from the Member object and used as the memberID in this class.
     * </p>
     * 
     * @param store The store where the member signed up.
     * @param signUpDate The date the member signed up, in string format (to be parsed into a LocalDate).
     * @param member The member who is signing up.
     */
    public SignUp(Store store,  String signUpDate, Member member) {
        this.memberID = member.getId(); // Set memberID from the existing Member's ID
        this.store = store;
//        this.member = member;  // Reference the existing Member
        this.signUpDate = LocalDate.parse(signUpDate, WolfWRApp.timeFormat);
    }

    /**
     * Default constructor for the SignUp class.
     * <p>
     * This constructor is used for creating empty SignUp objects, typically for use with frameworks 
     * that perform object-relational mapping.
     * </p>
     */
    public SignUp() {}


    /**
     * Returns the unique identifier for the member who signed up.
     * 
     * @return The member ID.
     */
    public long getMemberID() {
        return memberID;
    }

    /**
     * Sets the unique identifier for the member who signed up.
     * 
     * @param memberID The member ID to set.
     */
    public void setMemberID(long memberID) {
        this.memberID = memberID;
    }

    /**
     * Returns the sign-up date for the member.
     * 
     * @return The sign-up date.
     */
    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    /**
     * Sets the sign-up date for the member.
     * 
     * @param signUpDate The sign-up date to set.
     */
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

    /**
     * Returns the store where the member signed up.
     * 
     * @return The store associated with the sign-up.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets the store where the member signed up.
     * 
     * @param store The store to set.
     */
    public void setStore(Store store) {
        this.store = store;
    }
}
