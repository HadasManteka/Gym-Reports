package util;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PrettyPrinter {
    static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    public static String printStudiosPerDay(HashMap[] studiosEachDay) {
        StringBuilder data = new StringBuilder();
        for (int dayIdx=0; dayIdx<studiosEachDay.length; dayIdx++) {
            data.append(printHashWithTitle("day " + (dayIdx + 1) + ": ", studiosEachDay[dayIdx]));
        }
        return data.toString();
    }

    public static String printHashWithTitle(String title, HashMap hashResult) {
        StringBuilder data = new StringBuilder();
        data.append(title + "\n");
        out.println(title);
        data.append(printHashResult(hashResult) + '\n');
        out.println();
        return data.toString();
    }

    public static String printHashResult(HashMap hashResult) {
        StringBuilder data = new StringBuilder();
        hashResult.keySet().forEach(name -> {
            out.println(name + ": " + hashResult.get(name));
            data.append(name + ": " + hashResult.get(name) + '\n');
        });

        return data.toString();
    }

}
