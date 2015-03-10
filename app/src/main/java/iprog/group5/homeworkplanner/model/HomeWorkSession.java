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
    public HomeWorkSession() {
        this.breakTime = 0;
        this.assignment = new Assignment();
    }

    public int getBreakTime() {
        return breakTime;
    }

    public Assignment getAssignment() {
        return assignment;
    }
}
