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
        if (firstHash == null) {
            return secondHash;
        }

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
//        arrOfDays.sort(Comparator.comparing(DayOfStudios::getDayNumber));
    }

    public static HashMap<String, Double> sortHash(HashMap<String, Double> map) {
        Comparator<Object> valueComparator
                = (e1, e2) -> {
            if (e1.toString().contains("סופש")) {
                return 1;
            } else if (e2.toString().contains("סופש")) {
                return -1;
            }
            return 0;
        };

        Object[] keys = map.keySet().toArray();
        Arrays.sort(keys, valueComparator);
        LinkedHashMap<String, Double> sortedByValue = new LinkedHashMap<>(map.size());

        // copying entries from List to Map
        for(Object key : keys) {
            sortedByValue.put(key.toString(), map.get(key.toString()));
        }

        return sortedByValue;
    }

    public static Map<String, ? extends Number> adaptHash(Map<String, ? extends Number> map) {
        HashMap<String, Number> adaptMap = new LinkedHashMap<>(map);
        double sum = map.values().stream().mapToDouble(val -> Double.parseDouble(val.toString())).sum();

        adaptMap.put("", null);
        adaptMap.put("סיכום", sum);

        return adaptMap;
    }
}
