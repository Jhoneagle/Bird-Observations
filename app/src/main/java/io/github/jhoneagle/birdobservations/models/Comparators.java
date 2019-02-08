package io.github.jhoneagle.birdobservations.models;

import java.util.Comparator;

public class Comparators {
    static class ObservationByNameComparator implements Comparator<Observation> {
        public int compare(Observation c1, Observation c2) {
            return c1.getNameOfSpecies().compareTo(c2.getNameOfSpecies());
        }
    }

    static class ObservationByRarityComparator implements Comparator<Observation> {
        public int compare(Observation c1, Observation c2) {
            return c1.getRarity().compareTo(c2.getRarity());
        }
    }

    static class ObservationByTimeComparator implements Comparator<Observation> {
        public int compare(Observation c1, Observation c2) {
            return c1.getTimestamp().compareTo(c2.getTimestamp());
        }
    }

    public static final ObservationByNameComparator obByName = new ObservationByNameComparator();
    public static final ObservationByRarityComparator obByRarity = new ObservationByRarityComparator();
    public static final ObservationByTimeComparator obByTime = new ObservationByTimeComparator();
}
