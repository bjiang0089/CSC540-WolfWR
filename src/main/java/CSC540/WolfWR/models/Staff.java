package CSC540.WolfWR.models;

import jakarta.persistence.*;

@Entity
// Inheritance tag will save all applicable attributes to this table and
// the full entry to the child table
@Inheritance(strategy = InheritanceType.JOINED)
public class Staff extends DomainObject{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long staffID;

    @ManyToOne
    @JoinColumn(name = "storeID", nullable = false)
    private Store store;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "address", nullable = false, length = 128)
    private String address;

    @Column(name = "phone", nullable = false, length = 16)
    private String phone;

    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Column(name = "employmentTime", nullable = false)
    private int employmentTime;

    public enum Title {
        MANAGER,
        REGISTRATION,
        BILLING,
        WAREHOUSE,
        UNKNOWN;

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

    @Column(name = "title", nullable = false)
    private Title title;

    public Staff() {}

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

    public long getStaffId() {
        return this.staffID;
    }

    public void setStaffId(long staffID) {
        this.staffID = staffID;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Title getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = Title.fromString(title);
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmploymentTime() {
        return this.employmentTime;
    }

    public void setEmploymentTime(int employmentTime) {
        this.employmentTime = employmentTime;
    }
}