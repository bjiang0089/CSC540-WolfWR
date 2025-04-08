package CSC540.WolfWR.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class SignUp extends DomainObject {

    @Id
    private long memberID;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "storeID")
    private Store store;

    @MapsId
    @OneToOne
    @NotNull
    @JoinColumn(name = "memberID")
    private Member member;

    @NotNull
    private LocalDate signUpDate;

    public SignUp() {}

    public SignUp(Store store, Member member, LocalDate signUpDate) {
        this.memberID = member.getId();
        this.store = store;
        this.member = member;
        this.signUpDate = signUpDate;
    }

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
