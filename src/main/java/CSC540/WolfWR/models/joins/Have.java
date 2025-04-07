package CSC540.WolfWR.models.joins;

import CSC540.WolfWR.models.DomainObject;
import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.models.MembershipLevel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Have extends DomainObject {
    @EmbeddedId
    private HaveID id;

    @ManyToOne
    @MapsId("memberID")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @MapsId("mlID")
    @JoinColumn(name = "ml_name")
    private MembershipLevel ml;

    private String joinDate;

    public Have() {}

    @Embeddable
    public class HaveID extends DomainObject {

        private Long memberID;

        private String mlID;

        public Long getMemberID() {
            return memberID;
        }

        public HaveID() {}

        public HaveID(Long memberID, String mlId) {
            this.memberID = memberID;
            this.mlID = mlId;
        }

        public void setMemberID(Long memberID) {
            this.memberID = memberID;
        }

        public String getMlID() {
            return mlID;
        }

        public void setMlID(String levelID) {
            this.mlID = levelID;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof HaveID) {
                return (Objects.equals(this.memberID, ((HaveID) o).getMemberID()))
                        && (this.mlID.equals(((HaveID) o).getMlID()));
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash( this.memberID, this.mlID);
        }
    }
}
