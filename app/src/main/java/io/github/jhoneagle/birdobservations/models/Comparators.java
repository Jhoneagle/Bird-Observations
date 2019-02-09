package io.github.jhoneagle.birdobservations.models;

import java.util.Comparator;

/**
 * Collection of used comparator classes to sort lists.
 */
public class Comparators {
    /**
     * Comparator to short observations by name of species.
     */
    static class ObservationByNameComparator implements Comparator<Observation> {
        public int compare(Observation c1, Observation c2) {
            return c1.getNameOfSpecies().compareTo(c2.getNameOfSpecies());
        }
    }

    /**
     * Comparator to short observations by rarity of species.
     */
    static class ObservationByRarityComparator implements Comparator<Observation> {
        public int compare(Observation c1, Observation c2) {
            if (c1.getRarity().contains(c2.getRarity())) {
                return 0;
            } else {
                if (c1.getRarity().contains("Common")) {
                    return -1;
                } else if (c1.getRarity().contains("Extremely rare")) {
                    return 1;
                } else {
                    if (c2.getRarity().contains("Common")) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

    /**
     * Comparator to short observations by their timestamp of creation.
     */
    static class ObservationByTimeComparator implements Comparator<Observation> {
        public int compare(Observation c1, Observation c2) {
            return c2.getTimestamp().compareTo(c1.getTimestamp());
        }
    }

    /**
     * Object of comparator classes to avoid unnecessary initialize of objects each time.
     */
    public static final ObservationByNameComparator obByName = new ObservationByNameComparator();
    public static final ObservationByRarityComparator obByRarity = new ObservationByRarityComparator();
    public static final ObservationByTimeComparator obByTime = new ObservationByTimeComparator();
}
