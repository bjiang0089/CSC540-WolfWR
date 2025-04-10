package CSC540.WolfWR.models;

import jakarta.persistence.*;

/**
 * Represents a store within the application.
 * <p>
 * The `Store` class defines a store entity with details such as its unique identifier,
 * its address, phone number, and the staff member who manages the store. Each store has 
 * a single manager who is a staff member assigned to oversee the store's operations.
 * </p>
 */
@Entity
public class Store extends DomainObject {
    /** The unique identifier for the store. */
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeID;

    /** The manager of the store, represented by a Staff object. */
    @OneToOne
    @JoinColumn(name = "managerID")
    private Staff manager;


    /** The address of the store. */
    @Column(name = "address", nullable = false, length = 128)
    private String address;

    /** The phone number of the store. */
    @Column(name = "phone", nullable = false, length = 16)
    private String phone;

    /**
     * Default constructor for the Store class.
     * <p>
     * This constructor is used by frameworks (such as JPA) for object-relational mapping (ORM).
     * </p>
     */
    public Store() {};

    /**
     * Constructs a Store object with the specified details.
     * <p>
     * This constructor initializes the store with its unique ID, manager, address, and phone number.
     * </p>
     * 
     * @param storeID The unique identifier for the store.
     * @param phone The phone number of the store.
     * @param address The address of the store.
     * @param manager The manager of the store, represented by a {@link Staff} object.
     */
    public Store(long storeID, String phone, String address, Staff manager) {
        this.storeID = storeID;
        this.manager = manager;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Returns the unique identifier for the store.
     * 
     * @return The store ID.
     */
    public long getStoreID() {
        return storeID;
    }

    /**
     * Sets the unique identifier for the store.
     * 
     * @param storeID The store ID to set.
     */
    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    /**
     * Returns the manager of the store.
     * 
     * @return The {@link Staff} object representing the store's manager.
     */
    public Staff getManager() {
        return manager;
    }

    /**
     * Sets the manager of the store.
     * 
     * @param manager The {@link Staff} object to set as the store manager.
     */
    public void setManager(Staff manager) {
        this.manager = manager;
    }

    /**
     * Returns the address of the store.
     * 
     * @return The address of the store.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the store.
     * 
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the phone number of the store.
     * 
     * @return The phone number of the store.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the store.
     * 
     * @param phone The phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the store, including its ID and address.
     * 
     * @return A string representation of the store.
     */
    public String toString() {
        return String.format("\n\nStore ID: %d. Address\n\n", this.storeID, this.address);
    }
}
