package CSC540.WolfWR.models;

/**
 * Represents the membership levels available in the system.
 * <p>
 * The membership levels define the tiers of membership for users, which can be one of the following:
 * <ul>
 *     <li>GOLD</li>
 *     <li>SILVER</li>
 *     <li>PLATINUM</li>
 * </ul>
 * This enum provides methods to retrieve the name of the membership level as a string and 
 * to convert a string into the corresponding membership level.
 * </p>
 */
public enum MembershipLevel {
    /** Gold membership level. */
    GOLD,
    /** Silver membership level. */
    SILVER,
    /** Platinum membership level. */
    PLATINUM;

    /**
     * Returns the name of the membership level as a string.
     * <p>
     * For example:
     * <ul>
     *     <li>GOLD returns "Gold"</li>
     *     <li>SILVER returns "Silver"</li>
     *     <li>PLATINUM returns "Platinum"</li>
     * </ul>
     * </p>
     *
     * @return The name of the membership level as a string.
     */
    public String getName() {
        if (this.equals(GOLD))
            return "Gold";
        else if (this.equals(SILVER))
            return "Silver";
        else if (this.equals(PLATINUM))
            return "Platinum";
        return null;
    }

    /**
     * Converts a string representation of a membership level to the corresponding {@link MembershipLevel} enum.
     * <p>
     * The string comparison is case-insensitive and trims any leading or trailing whitespace.
     * </p>
     * 
     * @param level The string representing the membership level (e.g., "gold", "silver", "platinum").
     * @return The corresponding {@link MembershipLevel} enum, or {@code null} if no match is found.
     */
    public static MembershipLevel getLevel(String level) {
        return switch (level.toLowerCase().trim()) {
            case "gold" -> GOLD;
            case "silver" -> SILVER;
            case "platinum" -> PLATINUM;
            default -> null;
        };
    }
}
