package shiftsManagment;

import java.util.HashMap;

public class OrganizationReport {

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

}
