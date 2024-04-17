package shiftsManagment;

import Files.ReadFromFile;
import entities.DayOfStudios;
import enums.StudioGroupType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import util.HashCalculator;
import util.TimeCalculation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class WeeklyShiftsOrganizer extends ReportOrganizer {

    XSSFSheet sheet;
    int studioRowIndex = -1;
    int sundayColIndex = -1;
    int sundayRowIndex = -1;
    ReadFromFile fr;

    public WeeklyShiftsOrganizer(String filePath) {
        try {
            fr = new ReadFromFile(filePath);
            sheet = fr.getSheet();
        } catch (IOException | InvalidFormatException e) {
            System.out.println("Problem with reading: " + filePath);
        }
    }

    @Override
    public void calcAllPeriod() throws ParseException {
        calcWorkHoursOfEachEmployee();
        summarizeStudio();
        fr.close();
    }


    public void summarizeStudio() {
        XSSFCell cell;

        if (sundayColIndex == -1 || sundayRowIndex == -1) {
            System.out.println("There is no sunday column in one of the files");
        } else if (studioRowIndex == -1) {
            System.out.println("Cant find the start of the studios");
        } else {
            for (int c = sundayColIndex; c < 7 + sundayColIndex; c++) {

                HashMap<String, Integer> currentDayStudioHash = new HashMap<>();

                for (int r = studioRowIndex; r < sheet.getPhysicalNumberOfRows(); r++) {
                    cell = sheet.getRow(r).getCell(c);
                    if (cell != null) {
                        if (TimeCalculation.isHours(cell.toString())) {
                            String workerName = sheet.getRow(r + 1).getCell(c).toString().trim();
                            studiosPerEmployee.put(workerName, studiosPerEmployee.get(workerName) != null ?
                                    studiosPerEmployee.get(workerName) + 1 : 1);

                            // TODO think what is going on with separate document

                            String studioName = StudioGroupType.getGroupTitle(sheet.getRow(r - 1)
                                    .getCell(c).toString().trim());
                            currentDayStudioHash.put(studioName,
                                    currentDayStudioHash.get(studioName) != null ?
                                    currentDayStudioHash.get(studioName) + 1 : 1);
                        }
                    }
                }

                if (!currentDayStudioHash.isEmpty()) {
                    String dayName = sheet.getRow(sundayRowIndex).getCell(c).toString();
                    Integer dayNumber = (int)Double.parseDouble(sheet.getRow(sundayRowIndex - 2).getCell(c).toString());
                    countStudiosPerDay.add(new DayOfStudios(dayNumber, dayName, currentDayStudioHash));
                }
            }
        }
    }

    public void calcWorkHoursOfEachEmployee() throws ParseException {
        XSSFRow row;
        XSSFCell cell;
        boolean keep = true;
        int rows = sheet.getPhysicalNumberOfRows();
        ArrayList<Integer> holidayColIndexes = new ArrayList<>();

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
                            sundayRowIndex = r;
                        } else if (cell.toString().contains("שבת") || cell.toString().contains("חג")) {
                            holidayColIndexes.add(c);
                        } else if (TimeCalculation.isHours(cell.toString())) {
                            String workerName = sheet.getRow(r + 1).getCell(c).toString().trim();
                            double timeToAdd = TimeCalculation.calcTimeBetweenHours(cell.toString());
                            if (holidayColIndexes.contains(c)) {
                                workerName = workerName + " - חג";
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
