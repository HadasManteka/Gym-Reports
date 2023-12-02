package util;

import entities.DayOfStudios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class HashCalculator {

    public static HashMap<String, Integer> mixTwoIntHash(HashMap<String, Integer> firstHash,
                                                  HashMap<String, Integer> secondHash) {
        if (firstHash == null) {
            return secondHash;
        }

        secondHash.keySet().forEach(key -> {
            if (firstHash.containsKey(key)) {
                firstHash.replace(key, firstHash.get(key) + secondHash.get(key));
            } else {
                firstHash.put(key, secondHash.get(key));
            }
        });

        return firstHash;
    }

    public static HashMap<String, Double> mixTwoDoubleHash(HashMap<String, Double> firstHash,
                                                    HashMap<String, Double> secondHash) {
        secondHash.keySet().forEach(key-> {
            if (firstHash.containsKey(key)) {
                firstHash.replace(key, firstHash.get(key) + secondHash.get(key));
            } else {
                firstHash.put(key, secondHash.get(key));
            }
        });

        return firstHash;
    }

    public static void sortDaysArray(ArrayList<DayOfStudios> arrOfDays) {
        arrOfDays.sort(Comparator.comparing(DayOfStudios::getDayNumber));
    }
}
