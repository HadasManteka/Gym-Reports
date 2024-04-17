package shiftsManagment;

import Files.PrintToExcel;
import Files.PrintToFile;
import entities.DayOfStudios;
import util.HashCalculator;
import util.PrettyPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ReportOrganizer implements IReport{

    HashMap<String, Double> hoursPerEmployeeMap = new HashMap<>();
    HashMap<String, Integer> studiosPerEmployee = new HashMap<>();
    ArrayList<DayOfStudios> countStudiosPerDay = new ArrayList<>();

    public HashMap<String, Double> getHoursPerEmployeeMap() {
        return hoursPerEmployeeMap;
    }

    public ArrayList<DayOfStudios> getCountStudiosPerDay() {
        return countStudiosPerDay;
    }

    public HashMap<String, Integer> getStudiosPerEmployee() {
        return studiosPerEmployee;
    }

    public int getNumberOfStudiosSum() {
        return studiosPerEmployee.keySet().stream().mapToInt(emp -> studiosPerEmployee.get(emp)).sum();
    }

    public double getTotalGymHours() {
        return hoursPerEmployeeMap.keySet().stream().mapToDouble(emp -> hoursPerEmployeeMap.get(emp)).sum();
    }

    public void printToFile(String path) throws IOException {
        String data = PrettyPrinter.printStudiosPerDay(getCountStudiosPerDay()) +
                PrettyPrinter.printHashWithTitle("Hours of each employee: ", getHoursPerEmployeeMap()) +
                PrettyPrinter.printHashWithTitle("Number of studios of each employee: ", getStudiosPerEmployee()) +
                "Total hours:  " + getTotalGymHours() + '\n' +
                "Number of studios: " + getNumberOfStudiosSum();
        PrintToFile.write(path, data);

    }

    public void printToExcel(String path) throws IOException {
        PrintToExcel printExcel = new PrintToExcel(path);
        printExcel.createTableSheet(HashCalculator.reshapeArrayMap((getCountStudiosPerDay())), " Studios in month ");
        printExcel.writeMap(HashCalculator.adaptHash(getHoursPerEmployeeMap()), " Gym trainers ");
        printExcel.writeMap(HashCalculator.adaptHash(getStudiosPerEmployee()), " Studio trainers ");
        printExcel.write();
    }
}
