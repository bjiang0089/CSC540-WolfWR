package CSC540.WolfWR.models;

import jakarta.persistence.*;

@Entity
public class Store extends DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeID;

    @OneToOne
    @JoinColumn(name = "managerID")
    private Staff manager;


    @Column(name = "address", nullable = false, length = 128)
    private String address;

    @Column(name = "phone", nullable = false, length = 16)
    private String phone;


    public Store() {};

    public Store(long storeID, Staff manager, String address, String phone) {
        this.storeID = storeID;
        this.manager = manager;
        this.address = address;
        this.phone = phone;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public Staff getManager() {
        return manager;
    }

    public void setManager(Staff manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
