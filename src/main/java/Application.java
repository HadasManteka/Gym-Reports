import shiftsManagment.MonthlyShiftsOrganizer;
import shiftsManagment.ReportOrganizer;
import shiftsManagment.WeeklyShiftsOrganizer;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Application {

    static Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);

    public static void main(String[] args) {
        try {
            System.out.println("Month or week?\n" + 1 + " - week\n" + 2 + " - month\n");
            int periodChoice = scan.nextInt();
            scan.nextLine();
            String path = readPath();
            ReportOrganizer orgReport;

            switch (periodChoice) {
                case 1 -> {
                    orgReport = new WeeklyShiftsOrganizer(path);
                    orgReport.calcAllPeriod();
                    orgReport.printToFile("WeekReport.txt");
                }
                case 2 -> {
                    orgReport = new MonthlyShiftsOrganizer(path);
                    orgReport.calcAllPeriod();
                    String destPath = calcFileInDirectoryPath(path, "GYM.xlsx");
                    orgReport.printToExcel(destPath);
                    System.out.println("File saved in path: " + destPath);
                }

                default -> System.out.println("No such option, run program again");
            }

            System.out.println("Press any key to exit.");
            scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String calcFileInDirectoryPath(String originPath, String fileName) {
        return originPath + File.separator + fileName;
    }

    public static String readPath() throws Exception {
        System.out.println("Enter the path");
        String path = scan.nextLine();
        if (!new File(path).exists()) {
            throw new Exception("Path not exists");
        }
        return path;
    }
}
