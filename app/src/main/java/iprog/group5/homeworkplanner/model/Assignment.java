package iprog.group5.homeworkplanner.model;

/**
 * Created by Victor on 2015-02-23.
 */
public class Assignment {
    Subject subject;
    String title;
    String description;
    int estimatedWorkLoad; // In minutes
    int deadline; // 1 = monday, 2 = tuesday etc

    public Assignment(Subject subject, String title, String description, int estimatedWorkLoad, int deadline) {
        this.subject = subject;
        this.title = title;
        this.description = description;
        this.estimatedWorkLoad = estimatedWorkLoad;
        this.deadline = deadline;
    }

     public int getDeadline() {
        return deadline;
    }

    public Subject getSubject() {
        return subject;
    }
}
