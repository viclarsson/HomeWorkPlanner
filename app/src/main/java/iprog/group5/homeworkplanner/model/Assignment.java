package iprog.group5.homeworkplanner.model;

/**
 * Created by Victor on 2015-02-23.
 */
public class Assignment {
    Subject subject;
    String title;
    String description;
    int estimatedWorkLoad; // In minutes
    int deadlineDayNumber; // day in week

    public Assignment(Subject subject, String title, String description, int estimatedWorkLoad) {
        this.subject = subject;
        this.title = title;
        this.description = description;
        this.estimatedWorkLoad = estimatedWorkLoad;
    }

    public void setDeadlineDayNumber(int dayNumber) {
        this.deadlineDayNumber = dayNumber;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getDeadlineDayNumber() {
        return deadlineDayNumber;
    }
}
