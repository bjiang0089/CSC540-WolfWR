package CSC540.WolfWR.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a supplier in the system.
 * <p>
 * The `Supplier` class defines the properties and behavior for a supplier, 
 * which provides products to the store. Each supplier has a unique ID, 
 * a name, contact information, and a location.
 * </p>
 */
@Entity
public class Supplier extends DomainObject {

    /** The unique identifier for the supplier. */
    @Id
    private long supplierID;

    /** The name of the supplier. */
    @NotNull
    private String supplierName;

    /** The phone number of the supplier. */
    @NotNull
    private String phone;

    /** The email address of the supplier. */
    @NotNull
    private String email;

    /** The location of the supplier. */
    @NotNull
    private String location;

    /**
     * Default constructor for the Supplier class.
     * <p>
     * This constructor is used by frameworks (such as JPA) for object-relational mapping (ORM).
     * </p>
     */
    public Supplier() {}

    /**
     * Constructs a Supplier object with the specified details.
     * <p>
     * This constructor initializes a supplier with its unique ID, name, contact details, 
     * and location.
     * </p>
     * 
     * @param supplierID The unique identifier for the supplier.
     * @param supplierName The name of the supplier.
     * @param phone The phone number of the supplier.
     * @param email The email address of the supplier.
     * @param location The location of the supplier.
     */
    public Supplier(long supplierID, String supplierName,
                    String phone, String email, String location) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.phone = phone;
        this.email = email;
        this.location = location;
    }

    /**
     * Returns the unique identifier for the supplier.
     * 
     * @return The supplier ID.
     */
    public long getSupplierID() {
        return supplierID;
    }

    /**
     * Sets the unique identifier for the supplier.
     * 
     * @param supplierID The supplier ID to set.
     */
    public void setSupplierID(long supplierID) {
        this.supplierID = supplierID;
    }

    /**
     * Returns the name of the supplier.
     * 
     * @return The name of the supplier.
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Sets the name of the supplier.
     * 
     * @param supplierName The name to set for the supplier.
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * Returns the phone number of the supplier.
     * 
     * @return The phone number of the supplier.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the supplier.
     * 
     * @param phone The phone number to set for the supplier.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the email address of the supplier.
     * 
     * @return The email address of the supplier.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the supplier.
     * 
     * @param email The email address to set for the supplier.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the location of the supplier.
     * 
     * @return The location of the supplier.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the supplier.
     * 
     * @param location The location to set for the supplier.
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
