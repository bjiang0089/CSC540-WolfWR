package CSC540.WolfWR.models;

import CSC540.WolfWR.WolfWRApp;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a discount applied to a specific {@link Merchandise} product.
 * This entity is used to track the discount percentage, the start and end dates
 * for when the discount is applicable, and the associated product.
 * <p>
 * The {@code Discount} entity is mapped to the database with composite primary key
 * consisting of the product ID and the discount start date, which is handled by
 * the {@link DiscountID} class.
 * </p>
 * 
 * <p>
 * The {@link Discount} object contains information about the product being discounted,
 * the percentage of discount, and the duration of the discount (start and end dates).
 * </p>
 */
@Entity
@IdClass(Discount.DiscountID.class)
public class Discount extends DomainObject {

    /**
     * The merchandise product to which the discount is applied.
     * Mapped to the database using a foreign key relationship with the {@link Merchandise}.
     */
    @ManyToOne
    @JoinColumn(name = "productID")
    @MapsId("productID")
    @Id
    private Merchandise productID;

    /**
     * The percentage of the discount on the product.
     * The value must be between 0 and 100 (inclusive).
     */
    @Min(0)
    @Max(100)
    @NotNull
    private int discountPercentage;

    /**
     * The start date when the discount becomes active.
     */
    @MapsId("start")
    @Id
    private LocalDate start;

    /**
     * The end date when the discount expires.
     */
    @NotNull
    private LocalDate end;

    /**
     * Default constructor for the {@link Discount} entity.
     */
    public Discount() {}

    /**
     * Constructs a new {@code Discount} with the specified merchandise, discount percentage,
     * start date, and end date.
     *
     * @param merch             The {@link Merchandise} object to which the discount is applied.
     * @param discountPercentage The percentage of discount to be applied.
     * @param start             The start date for the discount, formatted as a string.
     * @param end               The end date for the discount, formatted as a string.
     */
    public Discount( Merchandise merch, int discountPercentage, String start, String end) {
        this.productID = merch;
        this.start = LocalDate.parse(start, WolfWRApp.timeFormat);
        this.discountPercentage = discountPercentage;
        this.end = LocalDate.parse(end, WolfWRApp.timeFormat);
    }

    /**
     * Gets the merchandise product associated with this discount.
     * 
     * @return The {@link Merchandise} product for the discount.
     */
    public Merchandise getProductID() {
        return productID;
    }

    /**
     * Sets the merchandise product associated with this discount.
     *
     * @param merch The {@link Merchandise} product to be associated with the discount.
     */
    public void setProductID(Merchandise merch) {
        this.productID = merch;
    }

    /**
     * Gets the discount percentage for this product.
     * 
     * @return The percentage of discount (between 0 and 100).
     */
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Sets the discount percentage for this product.
     * 
     * @param discountPercentage The discount percentage (between 0 and 100).
     */
    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * Gets the start date for this discount.
     * 
     * @return The start date of the discount.
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * Sets the start date for this discount.
     * 
     * @param start The start date of the discount.
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * Gets the end date for this discount.
     * 
     * @return The end date of the discount.
     */
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Sets the end date for this discount.
     * 
     * @param end The end date of the discount.
     */
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * Represents the composite primary key for the {@link Discount} entity.
     * This class is used to define the combination of {@link Merchandise productID}
     * and {@link LocalDate start} as the primary key for the {@link Discount} table.
     */
    @Embeddable
    public static class DiscountID extends DomainObject {

        /**
         * The product ID of the merchandise.
         */
        private long productID;

        /**
         * The start date for the discount.
         */
        private LocalDate start;

        /**
         * Default constructor for the {@link DiscountID}.
         */
        public DiscountID() {}

        /**
         * Constructs a new {@code DiscountID} with the specified product ID and start date.
         *
         * @param productId The product ID of the merchandise.
         * @param start     The start date for the discount.
         */
        public DiscountID(long productId, LocalDate start) {
            this.productID = productId;
            this.start = start;
        }

        /**
         * Gets the product ID for the merchandise.
         * 
         * @return The product ID.
         */
        public long getProductID() {
            return productID;
        }

        /**
         * Sets the product ID for the merchandise.
         * 
         * @param merchID The product ID.
         */
        public void setProductID(long merchID) {
            this.productID = merchID;
        }

        /**
         * Gets the start date for the discount.
         * 
         * @return The start date of the discount.
         */
        public LocalDate getStart() {
            return start;
        }

        /**
         * Sets the start date for the discount.
         * 
         * @param start The start date of the discount.
         */
        public void setStart(LocalDate start) {
            this.start = start;
        }

        /**
         * Determines if two {@link DiscountID} objects are equal based on the product ID and start date.
         *
         * @param o The object to compare.
         * @return {@code true} if both {@link DiscountID} objects have the same product ID and start date, otherwise {@code false}.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            DiscountID that = (DiscountID) o;
            return productID == that.productID && Objects.equals(start, that.start);
        }

        /**
         * Generates a hash code for the {@link DiscountID} based on the product ID and start date.
         *
         * @return The hash code of this {@link DiscountID}.
         */
        @Override
        public int hashCode() {
            return Objects.hash(productID, start);
        }
    }
}
