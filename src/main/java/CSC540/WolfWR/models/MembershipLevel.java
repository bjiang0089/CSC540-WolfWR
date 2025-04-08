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
}
