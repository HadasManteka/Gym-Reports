import shiftsManagment.MonthlyShiftsOrganizer;
import shiftsManagment.WeeklyShiftsOrganizer;
import util.PrettyPrinter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException, ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Month or week?\n" + 1 + " - week\n" + 2 + " - month\n");
        int periodChoice = scan.nextInt();
        scan.nextLine();
        String path;

        switch (periodChoice) {
            case 1:
                System.out.println("Enter the path of the week excel");
                path = scan.nextLine();
                WeeklyShiftsOrganizer weekShifts = new WeeklyShiftsOrganizer(path);
                weekShifts.calcAllPeriod();
                PrettyPrinter.printStudiosPerDay(weekShifts.getCountStudiosPerDay());
                PrettyPrinter.printHashWithTitle("Hours of each employee: ", weekShifts.getHoursPerEmployeeMap());
                PrettyPrinter.printHashWithTitle("Number of studios of each employee: ", weekShifts.getStudiosPerEmployee());

                break;
            case 2:
                System.out.println("Enter the path of the directory");
                path = scan.nextLine();
                MonthlyShiftsOrganizer monthlyShifts = new MonthlyShiftsOrganizer(path);
                monthlyShifts.calcAllPeriod();
                PrettyPrinter.printStudiosPerDay(monthlyShifts.getCountStudiosPerDay());
                PrettyPrinter.printHashWithTitle("Hours of each employee: ", monthlyShifts.getHoursPerEmployeeMap());
                PrettyPrinter.printHashWithTitle("Number of studios of each employee: ", monthlyShifts.getStudiosPerEmployee());

                break;
        }
    }
}
