package iprog.group5.homeworkplanner.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Victor on 2015-02-23.
 */
public class Week {
    // To be used as reference to days
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
        // Add days (Mon = 0, Fri = 4) to the days list
        for(int i = 0; i < 5; i++) {
            //Create a new day object with a new calendar object with the correct date set
            Calendar tmp = (Calendar) calendar.clone();
            tmp.add(Calendar.DATE, i);
            this.days.add(i, new Day(i, tmp));
        }
    }

    /**
     * Get overview list title
     * @return  the list title
     */
    public String getListTitle() {
        Day firstDay = days.get(calendar.getFirstDayOfWeek());
        Day lastDay = days.get(4);
        return firstDay.getDay() + " " + firstDay.getMonthText() + " - " + lastDay.getDay() + " " + lastDay.getMonthText();
    }

    /**
     * Returns true if there are sessions added to this week
     * @return
     */
    public boolean isPlanned() {
        boolean planned = false;
        if(!this.getSessionSubjectsCount().isEmpty()) {
            planned = true;
        }
        return planned;
    }

    /**
     * Return the list of days
     * @return
     */
    public ArrayList<Day> getDays() {
        return days;
    }

    /**
     * Return the number of assignments this week.
     * @return
     */
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

    public Assignment getAssignmentBySubject(Subject subject) {

        for (Day d : days) {
            Assignment a = d.getAssignment();

            if (a != null) {
                if (a.getSubject() == subject) {
                    return a;
                }
            }
        }

        return null;
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

    public Day getDay(int dayNumber) {
        return days.get(dayNumber);
    }

    /**
     * Set a new list of days.
     * @param days
     */
    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    /**
     * Return the week number
     * @return
     */
    public int getWeekNumber() {
        return weekNumber;
    }

    // TODO: Remove one of these variables? (Year is set more correctly, see below)
    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
    }

    /**
     * Set the year
     * @param year
     */
    public void setYear(int year) {
        calendar.set(Calendar.YEAR, year);
    }

    /**
     * Get the year
     * @return
     */
    public int getYear() { return calendar.get(Calendar.YEAR); }
}
