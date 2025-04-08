package CSC540.WolfWR.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

@Entity
public class Discount extends DomainObject {

    @Id
    private long discountID;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Merchandise merch;

    @Min(0)
    @Max(100)
    private int discountPercentage;

    private LocalDate start;

    private LocalDate end;

    public Discount() {}

    public Discount(long discountID, Merchandise merch, LocalDate start, int discountPercentage, LocalDate end) {
        this.discountID = discountID;
        this.merch = merch;
        this.start = start;
        this.discountPercentage = discountPercentage;
        this.end = end;
    }

    public long getDiscountID() {
        return discountID;
    }

    public void setDiscountID(long discountID) {
        this.discountID = discountID;
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
}
