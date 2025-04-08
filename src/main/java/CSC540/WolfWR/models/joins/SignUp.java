package CSC540.WolfWR.models.joins;

import CSC540.WolfWR.models.DomainObject;
import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.models.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class SignUp extends DomainObject {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "storeID")
    private Store store;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "memberID")
    @Id
    private Member member;

    @NotNull
    private LocalDate signUpDate;

    public SignUp() {}

    public SignUp(Store store, Member member, LocalDate signUpDate) {
        this.store = store;
        this.member = member;
        this.signUpDate = signUpDate;
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
