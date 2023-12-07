package entities;


public class StudioHoldsDays {
    private String dayName;
    private String numberOfStudio;

    public StudioHoldsDays(String dayName, String numberOfStudio) {
        this.dayName = dayName;
        this.numberOfStudio = numberOfStudio;
    }

    public String getDayName() {
        return dayName;
    }

    public String getNumberOfStudio() {
        return numberOfStudio;
    }
}
