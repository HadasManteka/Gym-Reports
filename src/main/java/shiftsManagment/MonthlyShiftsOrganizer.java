package shiftsManagment;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;

public class MonthlyShiftsOrganizer {

//    WeeklyShiftsOrganizer[] monthlyShiftsOrg = new WeeklyShiftsOrganizer[4];
    HashMap<String, Integer>[] countStudiosPerDay;
    HashMap<String, Integer> studiosPerEmployee;
    HashMap<String, Double> hoursPerEmployeeMap;
    String[] paths = new String[4];

    public MonthlyShiftsOrganizer(String[] paths) {
        paths = paths;
//        for (int i=0; i<=4; i++) {
//            monthlyShiftsOrg[i] = new WeeklyShiftsOrganizer(paths[i]);
//        }
    }

    public void calcAllMonth() throws ParseException {
        for (int i=0; i<=4; i++) {
            WeeklyShiftsOrganizer weekOrg = new WeeklyShiftsOrganizer(paths[i]);
            weekOrg.calcAllReports();

            weekOrg.getCountStudiosPerDay();
            mixTwoIntHash(studiosPerEmployee, weekOrg.getStudiosPerEmployee());
            mixTwoDoubleHash(hoursPerEmployeeMap, weekOrg.getHoursPerEmployeeMap());
            mixTwoIntArrayHash(countStudiosPerDay, weekOrg.getCountStudiosPerDay());
        }
    }

    public HashMap<String, Integer> mixTwoIntHash(HashMap<String, Integer> firstHash,
                                               HashMap<String, Integer> secondHash) {
        secondHash.keySet().forEach(key-> {
            if (firstHash.containsKey(key)) {
                firstHash.put(key, firstHash.get(key) + secondHash.get(key));
            }
        });

        return firstHash;
    }

    public HashMap<String, Double> mixTwoDoubleHash(HashMap<String, Double> firstHash,
                                               HashMap<String, Double> secondHash) {
        secondHash.keySet().forEach(key-> {
            if (firstHash.containsKey(key)) {
                firstHash.put(key, firstHash.get(key) + secondHash.get(key));
            }
        });

        return firstHash;
    }

    public HashMap<String, Integer>[] mixTwoIntArrayHash(HashMap<String, Integer>[] firstArrHash,
                                                    HashMap<String, Integer>[] secondArrHash) {
        for (int i=0; i<=secondArrHash.length; i++) {
            mixTwoIntHash(firstArrHash[i], secondArrHash[i]);
        }

        return firstArrHash;
    }
}
