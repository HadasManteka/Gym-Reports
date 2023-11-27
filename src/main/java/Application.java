import shiftsManagment.WeeklyShiftsOrganizer;
import util.PrettyPrinter;

import java.io.IOException;
import java.text.ParseException;

public class Application {

    public static void main(String[] args) throws IOException, ParseException {
        WeeklyShiftsOrganizer shiftSum = new WeeklyShiftsOrganizer("C:\\Users\\Hadas\\Desktop\\novShifts.xlsx");
        shiftSum.calcAllReports();
        PrettyPrinter.printStudiosPerDay(shiftSum.getCountStudiosPerDay());
        PrettyPrinter.printHashWithTitle("Hours of each employee: ", shiftSum.getHoursPerEmployeeMap());
        PrettyPrinter.printHashWithTitle("Number of studios of each employee: ", shiftSum.getStudiosPerEmployee());

    }
}
