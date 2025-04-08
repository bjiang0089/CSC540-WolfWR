package CSC540.WolfWR.models;

import jakarta.persistence.*;

@Entity
@Table(name="members")
public class Member {

    @Id
    private long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private MembershipLevel membershipLevel;

    private String email;
    private String phoneNo;
    private String address;

    private boolean isActive;

    public Member(long id, String firstName, String lastName, MembershipLevel membershipLevel, String email, String address, String phoneNo, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.membershipLevel = membershipLevel;
        this.email = email;
        this.address = address;
        this.phoneNo = phoneNo;
        this.isActive = isActive;
    }

    public Member(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMembershipLevel() {
        return membershipLevel.getName();
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String toString() {
        return String.format("Id: %2d. Full Name: %s %s. Membership Level: %s\n",
                this.id, this.firstName, this.lastName, this.membershipLevel.getName());
    }
}
