import shiftsManagment.ShiftsSummarizer;
import util.PrettyPrinter;

import java.io.IOException;
import java.text.ParseException;

public class Application {

    public static void main(String[] args) throws IOException, ParseException {
        ShiftsSummarizer shiftSum = new ShiftsSummarizer("C:\\Users\\Hadas\\Desktop\\novShifts.xlsx");
        shiftSum.calcAllReports();
        PrettyPrinter.printStudiosPerDay(shiftSum.getCountStudiosPerDay());
        PrettyPrinter.printHasWithTitle("Hours of each employee: ", shiftSum.getHoursPerEmployeeMap());
        PrettyPrinter.printHasWithTitle("Number of studios of each employee: ", shiftSum.getStudiosPerEmployee());

    }
}
