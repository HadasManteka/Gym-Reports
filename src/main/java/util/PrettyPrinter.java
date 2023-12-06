package util;

import entities.DayOfStudios;

import java.util.ArrayList;
import java.util.HashMap;

public class PrettyPrinter {
//    static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    public static String printStudiosPerDay(ArrayList<DayOfStudios> studiosEachDay) {
        StringBuilder data = new StringBuilder();
        for (DayOfStudios dayOfStudios : studiosEachDay) {
            data.append(printHashWithTitle(dayOfStudios.getDayNumber() + "-" +
                    dayOfStudios.getDayName() + " : ", dayOfStudios.getCountStudiosPerDay()));
        }
        return data.toString();
    }

    public static String printHashWithTitle(String title, HashMap hashResult) {
        return title + '\n' + printHashResult(hashResult) + '\n';
    }

    public static String printHashResult(HashMap hashResult) {
        StringBuilder data = new StringBuilder();
        hashResult.keySet().forEach(name ->
                data.append(name).append(": ").append(hashResult.get(name)).append('\n'));
        return data.toString();
    }

}
