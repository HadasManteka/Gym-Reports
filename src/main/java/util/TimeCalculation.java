package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCalculation {
    public static boolean isHours(String str) {
        return (str.contains("-") && str.contains(":"));
    }

    public static double calcTimeBetweenHours(String str) throws ParseException {
        String[] hours = str.replaceAll(" ", "").split("-");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        // Parsing the Time Period
        Date date1 = simpleDateFormat.parse(hours[0]);
        Date date2 = simpleDateFormat.parse(hours[1]);

        // Calculating the difference in milliseconds
        long differenceInMilliSeconds
                = Math.abs(date2.getTime() - date1.getTime());

        // Calculating the difference in Hours
        return ((double)differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
    }
}
