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

    @ManyToOne
    @JoinColumn(name = "productID")
    @MapsId("productID")
    @Id
    private Merchandise productID;

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
        this.productID = merch;
        this.start = LocalDate.parse(start, WolfWRApp.timeFormat);
        this.discountPercentage = discountPercentage;
        this.end = LocalDate.parse(end, WolfWRApp.timeFormat);
    }


    public Merchandise getProductID() {
        return productID;
    }

    public void setProductID(Merchandise merch) {
        this.productID = merch;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
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

        public DiscountID() {}

        public DiscountID(long productId, LocalDate start) {
            this.productID = productId;
            this.start = start;
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

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            DiscountID that = (DiscountID) o;
            return productID == that.productID && Objects.equals(start, that.start);
        }

        @Override
        public int hashCode() {
            return Objects.hash(productID, start);
        }
    }
}
