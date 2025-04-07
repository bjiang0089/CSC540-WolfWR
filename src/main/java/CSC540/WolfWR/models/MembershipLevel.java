package CSC540.WolfWR.models;

import jakarta.persistence.*;

@Entity
@Table(name="membership_levels")
public class MembershipLevel extends DomainObject{
    @Id
    private String name;

    // Only Platinum Members receive rewards as of out current design
    private float rewardRate;

    private int cost;

    public MembershipLevel(String name, float rewardRate, int cost) {
        this.name = name;
        this.cost = cost;
        this.rewardRate = rewardRate;
    }

    public MembershipLevel() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(float rewardRate) {
        this.rewardRate = rewardRate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String toString() {
        return String.format("Name: %10s. Rewards: %.2f. Cost: %3d.", this.name, this.rewardRate, this.cost);
    }
}
