package CSC540.WolfWR.models;

import jakarta.persistence.*;

/**
 * Represents a staff member working at a store.
 * <p>
 * This class contains the details of a staff member, including their personal information, 
 * their store association, job title, and employment details. The `Staff` class is designed 
 * to be part of a joined inheritance structure, where specific staff types are stored in 
 * child classes.
 * </p>
 */
@Entity
// Inheritance tag will save all applicable attributes to this table and
// the full entry to the child table
@Inheritance(strategy = InheritanceType.JOINED)
public class Staff extends DomainObject{
    /** The unique identifier for the staff member. */
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long staffID;

    /** The store where the staff member works. */
    @ManyToOne
    @JoinColumn(name = "storeID", nullable = false)
    private Store store;

    /** The name of the staff member. */
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    /** The age of the staff member. */
    @Column(name = "age", nullable = false)
    private int age;

    /** The address of the staff member. */
    @Column(name = "address", nullable = false, length = 128)
    private String address;

    /** The phone number of the staff member. */
    @Column(name = "phone", nullable = false, length = 16)
    private String phone;

    /** The email address of the staff member. */
    @Column(name = "email", nullable = false, length = 64)
    private String email;

    /** The number of years the staff member has been employed. */
    @Column(name = "employmentTime", nullable = false)
    private int employmentTime;


    /**
     * Enum representing the various job titles that a staff member can have.
     * <p>
     * This enum defines possible titles like MANAGER, REGISTRATION, BILLING, WAREHOUSE, 
     * and UNKNOWN, along with a method to convert a string input into an appropriate title.
     * </p>
     */
    public enum Title {
        MANAGER, 
        REGISTRATION, 
        BILLING, 
        WAREHOUSE, 

        UNKNOWN;

        public static Title fromString(String title) {
        /**
         * Converts a string to a corresponding {@link Title} enum.
         * 
         * @param title The title as a string, which is matched case-insensitively.
         * @return The corresponding {@link Title} enum value, or {@link Title#UNKNOWN} if no match.
         */
        private static Title fromString(String title) {
            switch (title.toLowerCase()) {
                case "manager":
                case "assistant manager":
                    return MANAGER;
                case "billing staff":
                case "cashier":
                    return BILLING;
                case "warehouse checker":
                    return WAREHOUSE;

                case "registration":
                    return REGISTRATION;
                default:
                    return UNKNOWN;
            }
        }
    }

   /** The job title of the staff member. */
    @Column(name = "title", nullable = false)
    private Title title;

    /**
     * Default constructor for the Staff class.
     * <p>
     * This constructor is used by frameworks (such as JPA) for object-relational mapping (ORM).
     * </p>
     */
    public Staff() {}

    /**
     * Constructs a Staff object with the specified details.
     * <p>
     * This constructor initializes the staff member with all necessary details such as 
     * name, age, address, contact details, title, and employment time.
     * </p>
     * 
     * @param name The name of the staff member.
     * @param address The address of the staff member.
     * @param age The age of the staff member.
     * @param email The email address of the staff member.
     * @param title The job title of the staff member (as a string, which will be converted to the `Title` enum).
     * @param phone The phone number of the staff member.
     * @param employmentTime The number of years the staff member has been employed.
     * @param staffID The unique identifier for the staff member.
     * @param store The store where the staff member works.
     */
    public Staff(String name,
                 String address,
                 int age,
                 String email,
                 String title,
                 String phone,
                 int employmentTime,
                 long staffID,
                 Store store) {
        this.staffID = staffID;
        this.store = store;
        this.name = name;
        this.age = age;
        this.address = address;
        this.title = Title.fromString(title);
        this.email = email;
        this.phone = phone;
        this.employmentTime = employmentTime;
    }

    /**
     * Returns the unique identifier for the staff member.
     * 
     * @return The staff ID.
     */
    public long getStaffId() {
        return this.staffID;
    }

    /**
     * Sets the unique identifier for the staff member.
     * 
     * @param staffID The staff ID to set.
     */
    public void setStaffId(long staffID) {
        this.staffID = staffID;
    }

    /**
     * Returns the store where the staff member works.
     * 
     * @return The store associated with the staff member.
     */
    public Store getStore() {
        return this.store;
    }

    /**
     * Sets the store where the staff member works.
     * 
     * @param store The store to set.
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Returns the name of the staff member.
     * 
     * @return The name of the staff member.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the staff member.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the staff member.
     * 
     * @return The address of the staff member.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address of the staff member.
     * 
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the age of the staff member.
     * 
     * @return The age of the staff member.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the age of the staff member.
     * 
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the job title of the staff member.
     * 
     * @return The job title of the staff member.
     */
    public Title getTitle() {
        return this.title;
    }

    /**
     * Sets the job title of the staff member.
     * 
     * @param title The job title to set (as a string).
     */
    public void setTitle(String title) {
        this.title = Title.fromString(title);
    }

    /**
     * Returns the phone number of the staff member.
     * 
     * @return The phone number of the staff member.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets the phone number of the staff member.
     * 
     * @param phone The phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the email address of the staff member.
     * 
     * @return The email address of the staff member.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email address of the staff member.
     * 
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the number of years the staff member has been employed.
     * 
     * @return The number of years of employment.
     */
    public int getEmploymentTime() {
        return this.employmentTime;
    }

    /**
     * Sets the number of years the staff member has been employed.
     * 
     * @param employmentTime The number of years to set.
     */
    public void setEmploymentTime(int employmentTime) {
        this.employmentTime = employmentTime;
    }
}
