package CSC540.WolfWR.models;

public enum MembershipLevel {
    GOLD,
    SILVER,
    PLATINUM;

    public String getName() {
        if (this.equals(GOLD))
            return "Gold";
        else if (this.equals(SILVER))
            return "Silver";
        else if (this.equals(PLATINUM))
            return "Platinum";
        return null;
    }

    public static MembershipLevel getLevel(String level) {
        return switch (level.toLowerCase().trim()) {
            case "gold" -> GOLD;
            case "silver" -> SILVER;
            case "platinum" -> PLATINUM;
            default -> null;
        };
    }
}
