package iprog.group5.homeworkplanner.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Victor on 2015-02-23.
 */
public class Week {
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;

    ArrayList<Day> days;
    int weekNumber;

    public Week(int weekNumber) {
        this.weekNumber = weekNumber;
        this.days = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            this.days.add(i, new Day());
        }
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public int getNumberOfAssignments() {
        int total = 0;
        for ( Day d : days) {
            if (d.assignment != null) {
                total++;
            }
        }
        return total;
    }

    public Day getDay(int name) {
        return days.get(name);
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }
}
