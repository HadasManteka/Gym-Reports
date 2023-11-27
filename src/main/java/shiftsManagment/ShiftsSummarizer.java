package shiftsManagment;

import Files.FileReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import util.TimeCalculation;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.HashMap;

public class ShiftsSummarizer {

    FileReader fr;
    XSSFSheet sheet;
    int studioRowIndex = -1;
    int sundayColIndex = -1;
    HashMap<String, Double> hoursPerEmployeeMap = new HashMap<>();
    HashMap<String, Integer> studiosPerEmployee = new HashMap<>();
    HashMap<String, Integer>[] countStudiosPerDay;



    public ShiftsSummarizer() {
        try {
            fr = new FileReader("C:\\Users\\Hadas\\Desktop\\novShifts.xlsx");
            sheet = fr.getSheet();
            countStudiosPerDay = (HashMap<String, Integer>[]) new HashMap<?,?>[7];
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

    }

    public void calcAllReports() throws ParseException {
        hoursOfWorkingPerEmployee();
        summarizeStudio();
    }

    public void summarizeStudio() {
        XSSFCell cell;

        if (studioRowIndex != -1) {
            for (int c = sundayColIndex; c < 7; c++) {
                for (int r = studioRowIndex; r < sheet.getPhysicalNumberOfRows(); r++) {
                    cell = sheet.getRow(r).getCell(c);
                    if (cell != null) {
                        if (TimeCalculation.isHours(cell.toString())) {
                            String workerName = sheet.getRow(r + 1).getCell(c).toString().replaceAll(" ", "");
                            studiosPerEmployee.put(workerName, studiosPerEmployee.get(workerName) != null ?
                                    studiosPerEmployee.get(workerName) + 1 : 1);

                            String studioName = sheet.getRow(r - 1).getCell(c).toString().replaceAll(" ", "");
                            countStudiosPerDay[c-sundayColIndex].put(studioName, countStudiosPerDay[c-sundayColIndex].get(studioName) != null ?
                                    countStudiosPerDay[c-sundayColIndex].get(studioName) + 1 : 1);
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

    public static void printResult(HashMap sum) {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        sum.keySet().forEach(name -> out.println(name + ": " + sum.get(name)));
    }
}
