package util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {
    public static LocalDate getMondayOfCurrentWeek() {
        LocalDate today = LocalDate.now();
        return today.with(DayOfWeek.MONDAY);
    }
}
