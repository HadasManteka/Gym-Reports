package shiftsManagment;

import java.text.ParseException;
import java.util.HashMap;

public class MonthlyShiftsOrganizer {

    WeeklyShiftsOrganizer[] monthlyShiftsOrg = new WeeklyShiftsOrganizer[4];
    HashMap<String, Integer>[] countStudiosPerDay;


    public MonthlyShiftsOrganizer(String[] paths) {
        for (int i=0; i<=4; i++) {
            monthlyShiftsOrg[i] = new WeeklyShiftsOrganizer(paths[i]);
        }
    }

    public void calcAllMonth() throws ParseException {
        for (int i=0; i<=4; i++) {
            monthlyShiftsOrg[i].calcAllReports();
            monthlyShiftsOrg[i].getCountStudiosPerDay();
            monthlyShiftsOrg[i].getStudiosPerEmployee();

        }
    }

}
