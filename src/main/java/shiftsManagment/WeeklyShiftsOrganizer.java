package shiftsManagment;

import Files.FileReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import util.TimeCalculation;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class WeeklyShiftsOrganizer {

    XSSFSheet sheet;
    int studioRowIndex = -1;
    int sundayColIndex = -1;
    HashMap<String, Double> hoursPerEmployeeMap = new HashMap<>();
    HashMap<String, Integer> studiosPerEmployee = new HashMap<>();
    HashMap<String, Integer>[] countStudiosPerDay;

    public WeeklyShiftsOrganizer(String filePath) {
        try {
            FileReader fr = new FileReader(filePath);
            sheet = fr.getSheet();
            countStudiosPerDay = (HashMap<String, Integer>[]) new HashMap<?,?>[7];
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, Double> getHoursPerEmployeeMap() {
        return hoursPerEmployeeMap;
    }

    public HashMap<String, Integer>[] getCountStudiosPerDay() {
        return countStudiosPerDay;
    }

    public HashMap<String, Integer> getStudiosPerEmployee() {
        return studiosPerEmployee;
    }

    public void calcAllReports() throws ParseException {
        hoursOfWorkingPerEmployee();
        summarizeStudio();
    }


    public void summarizeStudio() {
        XSSFCell cell;

        if (studioRowIndex != -1) {
            for (int c = sundayColIndex; c < 7 + sundayColIndex; c++) {
                countStudiosPerDay[c-sundayColIndex] = new HashMap<>();
                HashMap<String, Integer> currentDayStudioHash = countStudiosPerDay[c-sundayColIndex];

                for (int r = studioRowIndex; r < sheet.getPhysicalNumberOfRows(); r++) {
                    cell = sheet.getRow(r).getCell(c);
                    if (cell != null) {
                        if (TimeCalculation.isHours(cell.toString())) {
                            String workerName = sheet.getRow(r + 1).getCell(c).toString()
                                                        .replaceAll(" ", "");
                            studiosPerEmployee.put(workerName, studiosPerEmployee.get(workerName) != null ?
                                    studiosPerEmployee.get(workerName) + 1 : 1);

                            // TODO think what is going on with separate document

                            String studioName = sheet.getRow(r - 1).getCell(c).toString()
                                                        .replaceAll(" ", "");
                            currentDayStudioHash.put(studioName, currentDayStudioHash.get(studioName) != null ?
                                    currentDayStudioHash.get(studioName) + 1 : 1);
                        }
                    }
                }
            }
        }
    }

    public void hoursOfWorkingPerEmployee() throws ParseException {
        XSSFRow row;
        XSSFCell cell;
        boolean keep = true;
        int rows = sheet.getPhysicalNumberOfRows();
        int saturdayColIndex = -1;

        for (int r = 0; r < rows && keep; r++) {
            row = sheet.getRow(r);
            if (row != null) {
                for (int c = 0; c < row.getPhysicalNumberOfCells() && keep; c++) {
                    cell = row.getCell(c);
                    if (cell != null) {
                        if (cell.toString().contains("סטודיו") || cell.toString().contains("חוגים")) {
                            keep = false;
                            studioRowIndex = r;
                        } else if (cell.toString().contains("ראשון")) {
                            sundayColIndex = c;
                        } else if (cell.toString().contains("שבת")) {
                            saturdayColIndex = c;
                        } else if (TimeCalculation.isHours(cell.toString())) {
                            String workerName = sheet.getRow(r + 1).getCell(c).toString().replaceAll(" ", "");
                            double timeToAdd = TimeCalculation.calcTimeBetweenHours(cell.toString());
                            if (c == saturdayColIndex) {
                                workerName = workerName + " - סופש";
                            }

                            hoursPerEmployeeMap.put(workerName, hoursPerEmployeeMap.get(workerName) != null ?
                                    hoursPerEmployeeMap.get(workerName) + timeToAdd : timeToAdd);
                        }
                    }
                }
            }
        }
    }
}
