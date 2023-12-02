package entities;

import java.util.HashMap;

public class DayOfStudios {
    private Integer dayNumber;
    private String dayName;
    private HashMap<String, Integer> countStudiosPerDay;

    public DayOfStudios(Integer dayNumber, String dayName, HashMap<String, Integer> countStudiosPerDay) {
        this.dayNumber = dayNumber;
        this.dayName = dayName;
        this.countStudiosPerDay = countStudiosPerDay;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public HashMap<String, Integer> getCountStudiosPerDay() {
        return countStudiosPerDay;
    }

    public void setCountStudiosPerDay(HashMap<String, Integer> countStudiosPerDay) {
        this.countStudiosPerDay = countStudiosPerDay;
    }
}
