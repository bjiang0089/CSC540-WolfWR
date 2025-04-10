package CSC540.WolfWR.models;

import jakarta.persistence.*;

/**
 * Represents a member in the system with personal information and membership details.
 * <p>
 * This class is a JPA entity representing a record in the "members" table. It contains information 
 * about the member, such as their name, membership level, contact details, and status. It is mapped 
 * to a relational database using JPA annotations.
 * </p>
 * 
 * @see MembershipLevel
 */
@Entity
@Table(name="members")
public class Member extends DomainObject{

    /** The unique identifier for the member. */
    @Id
    @Column(name = "memberID")
    private long id;

    /** The first name of the member. */
    private String firstName;

    /** The last name of the member. */
    private String lastName;

    /** The membership level of the member, represented as an enum. */
    @Enumerated(EnumType.STRING)
    private MembershipLevel membershipLevel;

    /** The email address of the member. */
    private String email;

    /** The phone number of the member. */
    private String phoneNo;

    /** The home address of the member. */
    private String address;

    /** Whether the member is currently active or not. */
    private boolean isActive;

    /**
     * Constructor to create a new member with all necessary fields.
     * 
     * @param id The unique ID of the member.
     * @param firstName The first name of the member.
     * @param lastName The last name of the member.
     * @param ml The membership level as a string, which is converted to the {@link MembershipLevel} enum.
     * @param email The email address of the member.
     * @param phoneNo The phone number of the member.
     * @param address The address of the member.
     * @param isActive The active status of the member.
     */
    public Member(long id, String firstName, String lastName, String ml, String email, String phoneNo, String address, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.membershipLevel = MembershipLevel.getLevel(ml);
        this.email = email;
        this.address = address;
        this.phoneNo = phoneNo;
        this.isActive = isActive;
    }

    /** Default constructor for JPA entity instantiation. */
    public Member(){}

    /** 
     * Gets the unique ID of the member.
     * 
     * @return The unique ID of the member.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique ID for the member.
     * 
     * @param id The unique ID of the member.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the member.
     * 
     * @return The first name of the member.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the member.
     * 
     * @param firstName The first name of the member.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the member.
     * 
     * @return The last name of the member.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the member.
     * 
     * @param lastName The last name of the member.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the membership level of the member as a string.
     * 
     * @return The name of the membership level.
     */
    public String getMembershipLevel() {
        return membershipLevel.getName();
    }

    /**
     * Sets the membership level for the member.
     * 
     * @param membershipLevel The membership level for the member.
     */
    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    /**
     * Gets the email address of the member.
     * 
     * @return The email address of the member.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the member.
     * 
     * @param email The email address of the member.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the member.
     * 
     * @return The phone number of the member.
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the phone number of the member.
     * 
     * @param phoneNo The phone number of the member.
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Gets the address of the member.
     * 
     * @return The address of the member.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the member.
     * 
     * @param address The address of the member.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Checks whether the member is active.
     * 
     * @return {@code true} if the member is active, otherwise {@code false}.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active status of the member.
     * 
     * @param active The active status of the member.
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Returns a string representation of the member, including their ID, full name, and membership level.
     * 
     * @return A formatted string representing the member.
     */
    public String toString() {
        return String.format("Id: %2d. Full Name: %s %s. Membership Level: %s\n",
                this.id, this.firstName, this.lastName, this.membershipLevel.getName());
    }
}
