package shiftsManagment;

import java.io.File;
import java.text.ParseException;
import java.util.HashMap;

public class MonthlyShiftsOrganizer extends OrganizationReport implements IReport{
    File folder;

    public MonthlyShiftsOrganizer(String folderPath) {
        folder = new File(folderPath);
    }

    public void calcAllPeriod() throws ParseException {

        String excelFilesLst[] = folder.list();

        for (int i=0; i<excelFilesLst.length; i++) {
            WeeklyShiftsOrganizer weekOrg = new WeeklyShiftsOrganizer(excelFilesLst[i]);
            weekOrg.calcAllPeriod();

            weekOrg.getCountStudiosPerDay();
            studiosPerEmployee = mixTwoIntHash(studiosPerEmployee, weekOrg.getStudiosPerEmployee());
            hoursPerEmployeeMap = mixTwoDoubleHash(hoursPerEmployeeMap, weekOrg.getHoursPerEmployeeMap());
            countStudiosPerDay = mixTwoIntArrayHash(countStudiosPerDay, weekOrg.getCountStudiosPerDay());
        }
    }

    public HashMap<String, Integer> mixTwoIntHash(HashMap<String, Integer> firstHash,
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
        for (int i=0; i<secondArrHash.length; i++) {
            firstArrHash[i] = mixTwoIntHash(firstArrHash[i], secondArrHash[i]);
        }

        return firstArrHash;
    }
}
