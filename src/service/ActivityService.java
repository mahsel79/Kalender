package service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ActivityService {
    private Map<LocalDate, String> activities = new HashMap<>();

    public void addActivity(LocalDate date, String activity) {
        activities.put(date, activity);
    }

    public String getActivity(LocalDate date) {
        return activities.getOrDefault(date, "");
    }
}
