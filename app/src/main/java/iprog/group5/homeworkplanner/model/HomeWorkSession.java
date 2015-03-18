package iprog.group5.homeworkplanner.model;

/**
 * Created by Victor on 2015-02-23.
 */
public class HomeWorkSession {
    int breakTime = 5;
    Assignment assignment;

    public HomeWorkSession(Assignment assignment) {
        this.assignment = assignment;
    }

    /**
     * Initialize a HomeWorkSession with empty assignment = teacher scheduled!
     */
    public HomeWorkSession() {
        this.breakTime = 0;
        this.assignment = new Assignment();
    }

    /**
     * Set this HomeWorkSession to an unscheduled session.

    public void setUnscheduled() {
        this.assignment = null;
    }     */

    public int getBreakTime() {
        return breakTime;
    }

    public Assignment getAssignment() {
        return assignment;
    }
}
