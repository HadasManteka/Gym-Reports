import shiftsManagment.ShiftsSummarizer;

import java.io.IOException;
import java.text.ParseException;

public class Application {

    public static void main(String[] args) throws IOException, ParseException {
        ShiftsSummarizer shiftSum = new ShiftsSummarizer();
        shiftSum.hoursOfWorkingPerEmployee();
        System.out.println("----");
        shiftSum.summarizeStudio();

    }
}
