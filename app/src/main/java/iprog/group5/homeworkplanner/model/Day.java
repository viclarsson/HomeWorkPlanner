package iprog.group5.homeworkplanner.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InvalidPropertiesFormatException;

/**
 * Created by Victor on 2015-02-23.
 */
public class Day {
    final static int numberOfSlots = 26;
    Calendar calendar;
    int dayNumber;
    ArrayList<HomeWorkSession> sessions;
    // Assignment to be done to this day
    Assignment assignment;

    public Day(int dayNumber, Calendar calendar) {
        this.calendar = calendar;
        this.dayNumber = dayNumber;
        // We start at 8.00 and end at 21:00 with 30 min sessions
        this.sessions = new ArrayList<HomeWorkSession>(numberOfSlots);
        for(int i = 0; i < numberOfSlots; i++) {
            this.sessions.add(i, new HomeWorkSession(null));
        }
    }

    public void setScheduledTime(int startPosition, int endPosition) {
        for(int i = startPosition; i < endPosition; i++) {
            this.sessions.set(i, new HomeWorkSession());
        }
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    /**
     * Add a session based on 24 hour clock.
     * @param position
     * @param session
     */
    public boolean addSessionAtTime(int position, HomeWorkSession session) {
        // We add on intervals of 30 min from 8-21.
        // Protocol: int time = 8.5 => 08:30, 20 => 20.00 etc.

        // Check to see if the session is placed before the deadline
        if(this.getDayNumber() < session.getAssignment().getDeadlineDayNumber() && sessions.get(position).getAssignment() == null) {
            sessions.set(position, session);
            return true;
        }
        return false;
    }

    public void removeSessionAtTime(int position) {
        sessions.set(position, new HomeWorkSession(null));
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
        assignment.setDeadlineDayNumber(this.dayNumber);
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public int getDay() { return calendar.get(Calendar.DAY_OF_MONTH); }

    /**
     * Get the text representation of a this day
     *
     * @return The text representation of this day
     */
    public String getDayText() {
        switch(calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY: return "MÅN";
            case Calendar.TUESDAY: return "TIS";
            case Calendar.WEDNESDAY: return "ONS";
            case Calendar.THURSDAY: return "TOR";
            case Calendar.FRIDAY: return "FRE";
        }
        return "Unknown";
    }

    /**
     * Get the text representation of this day's month
     *
     * @return The text representation of this day's month
     */
    public int getMonth() { return calendar.get(Calendar.MONTH); }

    public String getMonthText() {
        switch(calendar.get(Calendar.MONTH)) {
            case Calendar.JANUARY: return "Januari";
            case Calendar.FEBRUARY: return "Februari";
            case Calendar.MARCH : return "Mars";
            case Calendar.APRIL : return "April";
            case Calendar.MAY : return "Maj";
            case Calendar.JUNE : return "Juni";
            case Calendar.JULY : return "Juli";
            case Calendar.AUGUST : return "Augusti";
            case Calendar.SEPTEMBER : return "September";
            case Calendar.OCTOBER : return "Oktober";
            case Calendar.NOVEMBER : return "November";
            case Calendar.DECEMBER : return "December";
        }
        return "Unknown";
    }

    public ArrayList<HomeWorkSession> getSessions() {
        return sessions;
    }

   /* // Fix Exception
    private int convertToArrayListIndex(double time) {
        if(time < 8 || time > 21) {
            // Not valid time.
            return 0;
        }
        // Get the correct ArrayList index and add.
       return (int) ((time - 8) * 2);
    }*/
}
