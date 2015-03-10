package iprog.group5.homeworkplanner.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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

    Calendar calendar;

    public Week(int weekNumber, int year) {
        this.weekNumber = weekNumber;
        calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
        calendar.set(Calendar.YEAR, year);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        this.days = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            //Create a new day object with a new calendar object with the correct date set
            Calendar tmp = (Calendar) calendar.clone();
            tmp.add(Calendar.DATE, i);
            this.days.add(i, new Day(i, tmp));
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

    /**
     * Get the number of assignments/deadline per subject this week
     *
     * @return Map with subjects and number of assignments
     */
    public HashMap<Subject, Integer> getAssignmentSubjectsCount() {
        HashMap<Subject, Integer> subjectCount = new HashMap<Subject, Integer>();

        for ( Day d : days) {
            Assignment assignment = d.getAssignment();
            Subject subject = null;

            if (assignment != null) {
                subject = assignment.getSubject();
            }

            if (subject != null) {
                if (subjectCount.containsKey(subject)) {
                    int currentValue = subjectCount.get(subject);
                    subjectCount.put(subject, currentValue + 1);
                } else {
                    subjectCount.put(subject, 1);
                }
            }
        }

        return subjectCount;
    }

    /**
     * Get the number of homework sessions for every subject this week
     *
     * @return Map with subjects and number of sessions
     */
    public HashMap<Subject, Integer> getSessionSubjectsCount() {
        HashMap<Subject, Integer> subjectCount = new HashMap<Subject, Integer>();

        for (Day d : days) {
            ArrayList<HomeWorkSession> sessions = d.getSessions();

            for (HomeWorkSession session : sessions) {
                Assignment assignment = session.getAssignment();
                Subject subject = null;

                if (assignment != null) {
                    subject = assignment.getSubject();
                }

                if (subject != null) {
                    if (subjectCount.containsKey(subject)) {
                        int currentValue = subjectCount.get(subject);
                        subjectCount.put(subject, currentValue + 1);
                    } else {
                        subjectCount.put(subject, 1);
                    }
                }
            }
        }

        return subjectCount;
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
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
    }

    public void setYear(int year) {
        calendar.set(Calendar.YEAR, year);
    }

    public int getYear() { return calendar.get(Calendar.YEAR); }
}
