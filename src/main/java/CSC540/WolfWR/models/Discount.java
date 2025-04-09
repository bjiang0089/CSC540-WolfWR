package CSC540.WolfWR.models;

import CSC540.WolfWR.WolfWRApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@IdClass(Discount.DiscountID.class)
public class Discount extends DomainObject {

    @Id
    @Column(name = "productID")
    private long productID;

    @Id
    private long storeID;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "productID", referencedColumnName = "productID"),
        @JoinColumn(name = "storeID", referencedColumnName = "storeID")
    })
    @MapsId
    private Merchandise product;

    @Min(0)
    @Max(100)
    @NotNull
    private int discountPercentage;

    @MapsId("start")
    @Id
    private LocalDate start;

    @NotNull
    private LocalDate end;

    public Discount() {}

    public Discount( Merchandise merch, int discountPercentage, String start, String end) {
        this.product = merch;
        this.start = LocalDate.parse(start, WolfWRApp.timeFormat);
        this.discountPercentage = discountPercentage;
        this.end = LocalDate.parse(end, WolfWRApp.timeFormat);
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public Merchandise getProduct() {
        return product;
    }

    public void setProduct(Merchandise product) {
        this.product = product;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Embeddable
    public static class DiscountID extends DomainObject {

        private long productID;

        private LocalDate start;

        private long storeID;

        public DiscountID() {}

        public DiscountID(long productId, LocalDate start, long storeID) {
            this.productID = productId;
            this.start = start;
            this.storeID = storeID;
        }

        public long getProductID() {
            return productID;
        }

        public void setProductID(long merchID) {
            this.productID = merchID;
        }

        public LocalDate getStart() {
            return start;
        }

        public void setStart(LocalDate start) {
            this.start = start;
        }

        public long getStoreID() {
            return storeID;
        }

        public void setStoreID(long storeID) {
            this.storeID = storeID;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            DiscountID that = (DiscountID) o;
            return productID == that.productID &&
                    Objects.equals(start, that.start) &&
                    this.storeID == that.getStoreID();
        }

        @Override
        public int hashCode() {
            return Objects.hash(productID, start, storeID);
        }
    }
}
