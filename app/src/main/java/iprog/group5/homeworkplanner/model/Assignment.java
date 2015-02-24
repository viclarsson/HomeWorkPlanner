package iprog.group5.homeworkplanner.model;

/**
 * Created by Victor on 2015-02-23.
 */
public class Assignment {
    Subject subject;
    String title;
    String description;
    int estimatedWorkLoad; // In minutes

    public Assignment(Subject subject, String title, String description, int estimatedWorkLoad) {
        this.subject = subject;
        this.title = title;
        this.description = description;
        this.estimatedWorkLoad = estimatedWorkLoad;
    }
}
