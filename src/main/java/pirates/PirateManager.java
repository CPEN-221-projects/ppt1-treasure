package pirates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class PirateManager {

    /**
     * Generate a list of pirates by assigning treasures to the pirate
     * using the name of the pirate in the pirate-to-treasure mapping
     * @param treasures the sequence of treasures, is not empty
     * @param pirateToTreasureMap pirateToTreasureMap[i] is the name of the pirate
     *                            that is given treasures[i], and is not empty
     * @return a list of pirates with each pirate being assigned the appropriate treasures
     * @throws IllegalArgumentException if treasures.length != pirateToTreasureMap.length
     */
    public static List<Pirate> buildPiratesWithTreasure(Treasure[] treasures, String[] pirateToTreasureMap) {

        if (treasures.length != pirateToTreasureMap.length) {
            throw new IllegalArgumentException();
        }

        List<Pirate> pirateList = new ArrayList<>();

        for (int i = 0; i < pirateToTreasureMap.length; i++) {
            String currentPirateName = pirateToTreasureMap[i];

            Pirate currentPirate;

            if (hasPirate(pirateList, currentPirateName)) {
                 currentPirate = getPirate(pirateList, currentPirateName);
            } else {
                 currentPirate = new Pirate(pirateToTreasureMap[i]);
            }

            currentPirate.addTreasure(treasures[i]);

            if (!pirateList.contains(currentPirate)) {
                pirateList.add(currentPirate);
            }
        }

        return pirateList;
    }

    private static boolean hasPirate(List<Pirate> pirateList, String currentPirateName) {
        for (Pirate pirate: pirateList) {
            if (pirate.getName().equals(currentPirateName)) {
                return true;
            }
        }
        return false;
    }

    private static Pirate getPirate(List<Pirate> pirateList, String currentPirateName) {
        for (Pirate pirate: pirateList) {
            if (pirate.getName().equals(currentPirateName)) {
                return pirate;
            }
        }
        return null;
    }

    /**
     * Is the allocation of treasure to pirates balanced?
     *
     * @param treasures the original list of treasures, is not null
     * @param pirates the list of pirates after the treasures have been allocated
     * @param deviationPercentage the allowed variation from the mean
     *                            for the per-pirate treasure value,
     *                            is between 0 and 200
     * @return true if the allocation is balanced and false otherwise
     */
    public static boolean isBalanced(Treasure[] treasures, List<Pirate> pirates, int deviationPercentage) {

        Set<Treasure> pirateTreasures = new HashSet<>();

        for (Pirate pirate: pirates) {
            for (Treasure treasure: pirate.getTreasures()) {
                pirateTreasures.add(treasure);
            }
        }

        int totalTreasureValue = 0;
        for (Treasure treasure: treasures) {
            if (!setContainsTreasure(pirateTreasures, treasure)) {
                return false;
            }
            totalTreasureValue += treasure.value;
        }

        double valueMin = totalTreasureValue / pirates.size() * (100 - deviationPercentage) / 100;
        double valueMax = totalTreasureValue / pirates.size() * (100 + deviationPercentage) / 100;

        for (Pirate pirate: pirates) {
            if (pirate.getTreasureValue() < valueMin || pirate.getTreasureValue() > valueMax) {
                return false;
            }
        }
        return true;
    }

    private static boolean setContainsTreasure(Set<Treasure> pirateTreasures, Treasure treasure) {
        for (Treasure currentTreasure: pirateTreasures) {
            if (currentTreasure.name.equals(treasure.name) && currentTreasure.value == treasure.value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return a list of pirates with a maximal balanced allocation
     * to Barbarossa and Jones
     * (see problem description)
     *
     * @param treasures the treasure to allocation, is not null
     * @return pirates with a maximal balanced allocation
     * to Barbarossa and Jones
     */
    public static List<Pirate> balancedAllocation(Treasure[] treasures) {

        return null;
    }

}
