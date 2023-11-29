package shiftsManagment;

import util.HashCalculator;

import java.io.File;
import java.text.ParseException;
import java.util.HashMap;

public class MonthlyShiftsOrganizer extends OrganizationReport{
    File folder;

    public MonthlyShiftsOrganizer(String folderPath) {
        folder = new File(folderPath);
    }

    @Override
    public void calcAllPeriod() throws ParseException {

        String[] excelFilesLst = folder.list();

        for (int i = 0; i < excelFilesLst.length; i++) {
            if (excelFilesLst[i].endsWith(".xlsx")) {
                WeeklyShiftsOrganizer weekOrg = new WeeklyShiftsOrganizer(folder.getPath() +
                        File.separator + excelFilesLst[i]);
                weekOrg.calcAllPeriod();

                weekOrg.getCountStudiosPerDay();
                studiosPerEmployee = HashCalculator.mixTwoIntHash(studiosPerEmployee, weekOrg.getStudiosPerEmployee());
                hoursPerEmployeeMap = HashCalculator.mixTwoDoubleHash(hoursPerEmployeeMap, weekOrg.getHoursPerEmployeeMap());
                countStudiosPerDay = HashCalculator.mixTwoIntArrayHash(countStudiosPerDay, weekOrg.getCountStudiosPerDay());
            }
        }
    }
}
