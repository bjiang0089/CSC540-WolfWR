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
    @MapsId("id")
    @JoinColumn(name = "id")
    private Member m;

    @ManyToOne
    @MapsId("name")
    @JoinColumn(name = "name")
    private MembershipLevel ml;

    private String joinDate;

    public Have() {}

    @Embeddable
    public class HaveID extends DomainObject {

        private Long memberID;

        private String levelID;

        public Long getMemberID() {
            return memberID;
        }

        public void setMemberID(Long memberID) {
            this.memberID = memberID;
        }

        public String getLevelID() {
            return levelID;
        }

        public void setLevelID(String levelID) {
            this.levelID = levelID;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof HaveID) {
                return (Objects.equals(this.memberID, ((HaveID) o).getMemberID()))
                        && (this.levelID.equals(((HaveID) o).getLevelID()));
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash( this.memberID, this.levelID );
        }
    }
}
