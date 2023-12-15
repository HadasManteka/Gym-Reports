import shiftsManagment.MonthlyShiftsOrganizer;
import shiftsManagment.ReportOrganizer;
import shiftsManagment.WeeklyShiftsOrganizer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws ParseException, IOException {
        try {
            Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);
            System.out.println("Month or week?\n" + 1 + " - week\n" + 2 + " - month\n");
            int periodChoice = scan.nextInt();
            scan.nextLine();
            String path;
            ReportOrganizer orgReport;
            System.out.println("Enter the path");
            path = scan.nextLine();
            if (!new File(path).exists()) {
                throw new Exception("Path not exists");
            }

            switch (periodChoice) {
                case 1 -> {
                    orgReport = new WeeklyShiftsOrganizer(path);
                    orgReport.calcAllPeriod();
                    orgReport.printToFile("WeekReport.txt");
                }
                case 2 -> {
                    orgReport = new MonthlyShiftsOrganizer(path);
                    orgReport.calcAllPeriod();
                    orgReport.printToFile(path + File.separator + "MonthReport.txt");
                    orgReport.printToExcel(path + File.separator + "GYM.xlsx");
                }
                default -> System.out.println("No such option");
            }

            System.out.println("Press any key to exit.");
            scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
