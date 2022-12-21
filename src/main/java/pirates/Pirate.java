package pirates;

import java.util.HashSet;
import java.util.Set;

public class Pirate {
    private String name;
    private int treasureValue;
    private Set<Treasure> treasures;

    /**
     * Create a Pirate instance with a given name
     * @param name the name of the pirate, is not null or the empty string
     */
    public Pirate(String name) {
        this.name = name;
        this.treasureValue = 0;
        this.treasures = new HashSet<>();
    }

    /**
     * Add a treasure to this pirate's collection of treasures
     *
     * @param t the treasure to add, is not null
     */
    public void addTreasure(Treasure t) {
        treasures.add(t);
        treasureValue += t.value;
    }

    /**
     * Obtain the total value of treasure held by this pirate
     *
     * @return the total value of treasure held by this pirate
     */
    public int getTreasureValue() {
        return treasureValue;
    }

    /**
     * Obtain the treasure of highest value held by this pirate
     *
     * @return the treasure of highest value held by this pirate, and if there
     * are multiple such items of the same highest value then any one of them is returned
     */
    public Treasure getHighestValueTreasure() {

        if (treasures.isEmpty()) {
            return null;
        }

        Treasure highestValueTreasure = null;
        for (Treasure treasure: treasures) {
            if (highestValueTreasure == null || treasure.value >= highestValueTreasure.value) {
                highestValueTreasure = treasure;
            }
        }
        return highestValueTreasure;
    }

    /**
     * Obtain the treasures held by this pirate
     * @return the treasures held by this pirate
     */
    public Set<Treasure> getTreasures() {
        Set<Treasure> buildSet = new HashSet<>();

        for (Treasure treasure: treasures) {
            buildSet.add(treasure);
        }

        return buildSet;
    }

    /*** do not change the methods below ***/

    /**
     * Obtain this pirate's name
     * @return this pirate's name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + this.getTreasureValue() + ")";
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Pirate) {
            Pirate that = (Pirate) other;
            if (this.hashCode() != that.hashCode()) {
                return false;
            }
            return this.name.equals(that.name);
        }
        else {
            return false;
        }
    }
}
