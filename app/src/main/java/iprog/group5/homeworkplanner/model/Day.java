package iprog.group5.homeworkplanner.model;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

/**
 * Created by Victor on 2015-02-23.
 */
public class Day {
    ArrayList<HomeWorkSession> sessions;
    // Assignment to be done to this day
    Assignment assignment;

    public Day() {
        // We start at 8.00 and end at 21:00 with 30 min sessions
        this.sessions = new ArrayList<HomeWorkSession>(26);
        for(int i = 0; i < 26; i++) {
            this.sessions.add(i, null);
        }
    }

    /**
     * Add a session based on 24 hour clock.
     * @param time
     * @param session
     */
    public void addSessionAtTime(double time, HomeWorkSession session) {
        // We add on intervals of 30 min from 8-21.
        // Protocol: int time = 8.5 => 08:30, 20 => 20.00 etc.

        int slot = convertToArrayListIndex(time);
        sessions.add(slot, session);
    }

    public void removeSessionAtTime(double time) {
        int slot = convertToArrayListIndex(time);
        sessions.set(slot, null);
    }

    // Fix Exception
    private int convertToArrayListIndex(double time) {
        if(time < 8 || time > 21) {
            // Not valid time.
            return 0;
        }
        // Get the correct ArrayList index and add.
       return (int) (time - 8) * 2;
    }
}
