package iprog.group5.homeworkplanner.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

/**
 * Created by Victor on 2015-02-23.
 */
public class Day {
    int dayNumber;
    ArrayList<HomeWorkSession> sessions;
    // Assignment to be done to this day
    Assignment assignment;

    public Day(int dayNumber) {
        this.dayNumber = dayNumber;
        // We start at 8.00 and end at 21:00 with 30 min sessions
        this.sessions = new ArrayList<HomeWorkSession>(26);
        for(int i = 0; i < 26; i++) {
            this.sessions.add(i, new HomeWorkSession(null));
        }
    }

    /**
     * Add a session based on 24 hour clock.
     * @param time
     * @param session
     */
    public boolean addSessionAtTime(int position, HomeWorkSession session) {
        // We add on intervals of 30 min from 8-21.
        // Protocol: int time = 8.5 => 08:30, 20 => 20.00 etc.

        // Check to see if the session is placed before the deadline
        if(this.getDayNumber() < session.getAssignment().getDeadlineDayNumber()) {
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
