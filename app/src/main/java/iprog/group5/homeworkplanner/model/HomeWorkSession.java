package iprog.group5.homeworkplanner.model;

import javax.security.auth.Subject;

/**
 * Created by Victor on 2015-02-23.
 */
public class HomeWorkSession {
    int breakTime = 5;
    Assignment assignment;

    public HomeWorkSession(Assignment assignment) {
        this.assignment = assignment;
    }
}
