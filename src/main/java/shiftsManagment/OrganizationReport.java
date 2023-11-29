package shiftsManagment;

import Files.PrintToFile;
import util.PrettyPrinter;

import java.io.IOException;
import java.util.HashMap;

public abstract class OrganizationReport implements IReport{

    HashMap<String, Double> hoursPerEmployeeMap = new HashMap<>();
    HashMap<String, Integer> studiosPerEmployee = new HashMap<>();
    HashMap<String, Integer>[] countStudiosPerDay = (HashMap<String, Integer>[]) new HashMap<?,?>[7];

    public HashMap<String, Double> getHoursPerEmployeeMap() {
        return hoursPerEmployeeMap;
    }

    public HashMap<String, Integer>[] getCountStudiosPerDay() {
        return countStudiosPerDay;
    }

    public HashMap<String, Integer> getStudiosPerEmployee() {
        return studiosPerEmployee;
    }

    public void printToFile(String path) throws IOException {
        StringBuilder data = new StringBuilder();
        data.append(PrettyPrinter.printStudiosPerDay(getCountStudiosPerDay()));
        data.append(PrettyPrinter.printHashWithTitle("Hours of each employee: ", getHoursPerEmployeeMap()));
        data.append(PrettyPrinter.printHashWithTitle("Number of studios of each employee: ", getStudiosPerEmployee()));
        PrintToFile.write(path, data.toString());

    }
}
