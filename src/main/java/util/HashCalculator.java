package util;

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

    public static HashMap<String, Integer>[] mixTwoIntArrayHash(HashMap<String, Integer>[] firstArrHash,
                                                         HashMap<String, Integer>[] secondArrHash) {
        for (int i=0; i<secondArrHash.length; i++) {
            firstArrHash[i] = mixTwoIntHash(firstArrHash[i], secondArrHash[i]);
        }

        return firstArrHash;
    }

}
