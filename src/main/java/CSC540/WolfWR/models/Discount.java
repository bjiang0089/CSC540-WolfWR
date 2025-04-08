package CSC540.WolfWR.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Discount extends DomainObject {
    @EmbeddedId
    private DiscountID discountID;

    @ManyToOne
    @JoinColumn(name = "productID")
    @MapsId("merchID")
    private Merchandise merch;

    @Min(0)
    @Max(100)
    @NotNull
    private int discountPercentage;

    @MapsId("start")
    private LocalDate start;

    @NotNull
    private LocalDate end;

    public Discount() {}

    public Discount( Merchandise merch, LocalDate start, int discountPercentage, LocalDate end) {
        this.discountID = new DiscountID(merch.getProductID(), start);
        this.merch = merch;
        this.start = start;
        this.discountPercentage = discountPercentage;
        this.end = end;
    }

    public Merchandise getMerch() {
        return merch;
    }

    public void setMerch(Merchandise merch) {
        this.merch = merch;
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

        private long merchID;

        private LocalDate start;

        public DiscountID() {}

        public DiscountID(long merchID, LocalDate start) {
            this.merchID = merchID;
            this.start = start;
        }

        public long getMerchID() {
            return merchID;
        }

        public void setMerchID(long merchID) {
            this.merchID = merchID;
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
            return merchID == that.merchID && Objects.equals(start, that.start);
        }

        @Override
        public int hashCode() {
            return Objects.hash(merchID, start);
        }
    }
}
