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
    @EmbeddedId
    private SignUpID id;

    @ManyToOne
    @MapsId("storeID")
    @NotNull
    @JoinColumn(name = "storeID")
    private Store store;

    @ManyToOne
    @MapsId("memberID")
    @NotNull
    @JoinColumn(name = "memberID")
    private Member member;

    @NotNull
    private LocalDate signUpDate;

    public SignUp() {}

    public SignUp(Store store, Member member, LocalDate signUpDate) {
        this.id = new SignUpID(store.getStoreID(), member.getId());
        this.store = store;
        this.member = member;
        this.signUpDate = signUpDate;
    }

    public SignUpID getId() {
        return id;
    }

    public void setId(SignUpID id) {
        this.id = id;
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

    @Embeddable
    public static class SignUpID extends DomainObject {

        private long storeID;
        private long memberID;

        public SignUpID() {}

        public SignUpID(long storeID, long memberID) {
            this.storeID = storeID;
            this.memberID = memberID;
        }

        public long getStoreID() {
            return storeID;
        }

        public void setStoreID(long storeID) {
            this.storeID = storeID;
        }

        public long getMemberID() {
            return memberID;
        }

        public void setMemberID(long memberID) {
            this.memberID = memberID;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            SignUpID signUpID = (SignUpID) o;
            return storeID == signUpID.storeID && memberID == signUpID.memberID;
        }

        @Override
        public int hashCode() {
            return Objects.hash(storeID, memberID);
        }
    }


}
