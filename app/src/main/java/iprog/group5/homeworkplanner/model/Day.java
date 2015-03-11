package iprog.group5.homeworkplanner.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Victor on 2015-02-23.
 */
public class Day {
    final static int numberOfSessions = 28; // two extra for spacing
    Calendar calendar;
    int dayNumber;
    ArrayList<HomeWorkSession> sessions;
    // Assignment to be done to this day
    Assignment assignment;

    /**
     * Initialize the Day with the daynumber and Calendar object. Also add unscheduled sessions.
     * @param dayNumber
     * @param calendar
     */
    public Day(int dayNumber, Calendar calendar) {
        this.calendar = calendar;
        this.dayNumber = dayNumber;
        // We start at 8.00 and end at 21:00 with 30 min sessions
        this.sessions = new ArrayList<HomeWorkSession>(numberOfSessions);
        for(int i = 0; i < numberOfSessions; i++) {
            this.sessions.add(i, new HomeWorkSession(null));
        }
    }

    /**
     * Sets teacher scheduled sessions based on start and end position.
     * @param startPosition
     * @param endPosition
     */
    public void setScheduledTime(int startPosition, int endPosition) {
        for(int i = startPosition; i < endPosition; i++) {
            // HomeworkSession without paramters means teacher scheduled
            this.sessions.set(i, new HomeWorkSession());
        }
    }

    /**
     * Get the total number of sessions.
     * @return
     */
    public int getNumberOfSessions() {
        return numberOfSessions;
    }

    /**
     * Add a session based on the position in sessionslist
     * @param position
     * @param session
     */
    public String addSessionAtTime(int position, HomeWorkSession session) {
        // TODO: Evaluate protocol for error messages.
        // Check if able to add
        if(this.getDayNumber() >= session.getAssignment().getDeadlineDayNumber()) {
            return "(FIX THIS) Schemalägg inte efter deadline.";
        } else if(sessions.get(position).getAssignment() != null) {
            return "(FIX THIS) Du går i skolan denna tid.";
        } else if(position == 0) {
            return "(FIX THIS) Du måste hinna äta frukost denna tid.";
        } else if(position == (numberOfSessions -1)) {
            return "(FIX THIS) Du borde gå och lägga dig vid denna tid...";
        } else {
            sessions.set(position, session);
            return "Schemalagt!";
        }
    }

    /**
     * "Removes" (sets unscheduled) for given session position
     * @param position
     */
    public void removeSessionAtTime(int position) {
        sessions.get(position).setUnscheduled();
    }

    /**
     * Relate an assignment to this day (Becomes the deadline)
     * @param assignment
     */
    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
        assignment.setDeadlineDayNumber(this.dayNumber);
    }

    /**
     * Get the assignment related to this day
     * @return
     */
    public Assignment getAssignment() {
        return assignment;
    }


    // TODO: One of these are unnessasary?

    /**
     * Get the current day number
     * @return
     */
    public int getDayNumber() {
        return dayNumber;
    }

    /**
     * Get the current day number
     * @return
     */
    public int getDay() { return calendar.get(Calendar.DAY_OF_MONTH); }

    /**
     * Get the text representation of a this day
     * TODO: Add these as strings in resources
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
        return "";
    }

    /**
     * Get the text representation of this day's month
     * TODO: Add these as strings in resources
     * @return The text representation of this day's month
     */
    public int getMonth() { return calendar.get(Calendar.MONTH); }

    public String getMonthText() {
        switch(calendar.get(Calendar.MONTH)) {
            case Calendar.JANUARY: return "Jan";
            case Calendar.FEBRUARY: return "Feb";
            case Calendar.MARCH : return "Mar";
            case Calendar.APRIL : return "Apr";
            case Calendar.MAY : return "Maj";
            case Calendar.JUNE : return "Jun";
            case Calendar.JULY : return "Jul";
            case Calendar.AUGUST : return "Aug";
            case Calendar.SEPTEMBER : return "Sep";
            case Calendar.OCTOBER : return "Okt";
            case Calendar.NOVEMBER : return "Nov";
            case Calendar.DECEMBER : return "Dec";
        }
        return "";
    }

    /**
     * Return a list of all sessions this day
     * @return
     */
    public ArrayList<HomeWorkSession> getSessions() {
        return sessions;
    }
}
