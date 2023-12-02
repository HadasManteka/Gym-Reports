import shiftsManagment.MonthlyShiftsOrganizer;
import shiftsManagment.OrganizationReport;
import shiftsManagment.WeeklyShiftsOrganizer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws ParseException, IOException {
        Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("Month or week?\n" + 1 + " - week\n" + 2 + " - month\n");
        int periodChoice = scan.nextInt();
        scan.nextLine();
        String path;
        OrganizationReport orgReport;

        switch (periodChoice) {
            case 1 -> {
                System.out.println("Enter the path of the week excel");
                path = scan.nextLine();
                orgReport = new WeeklyShiftsOrganizer(path);
                orgReport.calcAllPeriod();
                orgReport.printToFile("WeekReport.txt");
            }
            case 2 -> {
                System.out.println("Enter the path of the directory");
                path = scan.nextLine();
                orgReport = new MonthlyShiftsOrganizer(path);
                orgReport.calcAllPeriod();
                orgReport.printToFile(path + File.separator + "MonthReport.txt");
            }
            default -> System.out.println("No such option");
        }

        System.out.println("Press any key to exit.");
        scan.nextLine();
    }
}
