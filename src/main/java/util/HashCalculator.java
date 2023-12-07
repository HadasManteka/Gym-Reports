package util;

import entities.DayOfStudios;
import entities.StudioHoldsDays;

import java.util.*;

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

    public static Map<String, Object[]> reshapeArrayMap(ArrayList<DayOfStudios> arrOfDays) {
        int sizeOfMonth = arrOfDays.size() + 1;
        Map<String, Object[]> arrOfStudios = new HashMap<>();

        arrOfDays.forEach(day->
            day.getCountStudiosPerDay().keySet().forEach(studio -> {
                StudioHoldsDays[] daysList;
                if (arrOfStudios.get(studio) == null) {
                    daysList = new StudioHoldsDays[sizeOfMonth];
                    daysList[0] = new StudioHoldsDays("", studio);
                } else {
                    daysList = (StudioHoldsDays[]) arrOfStudios.get(studio);
                }

                daysList[day.getDayNumber()] = new StudioHoldsDays(day.getDayName(), String.valueOf(day.getCountStudiosPerDay().get(studio)));
                arrOfStudios.put(studio, daysList);
        }));

        return arrOfStudios;
//        studentData.put(
//                "1",
//                new Object[] { "Roll No", "NAME", "Year" });

//        arrOfDays.sort(Comparator.comparing(DayOfStudios::getDayNumber));
    }
}
