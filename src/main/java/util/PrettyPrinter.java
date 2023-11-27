package util;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PrettyPrinter {
    static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    public static void printStudiosPerDay(HashMap[] studiosEachDay) {
        for (int dayIdx=0; dayIdx<studiosEachDay.length; dayIdx++) {
            printHashWithTitle("day " + (dayIdx+1) + ": ", studiosEachDay[dayIdx]);
        }
    }

    public static void printHashWithTitle(String title, HashMap hashResult) {
        out.println(title);
        printHashResult(hashResult);
        out.println();
    }

    public static void printHashResult(HashMap hashResult) {
        hashResult.keySet().forEach(name -> out.println(name + ": " + hashResult.get(name)));
    }

}
