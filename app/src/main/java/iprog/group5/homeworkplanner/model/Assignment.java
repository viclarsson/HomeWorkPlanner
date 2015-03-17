package iprog.group5.homeworkplanner.model;

/**
 * Created by Victor on 2015-02-23.
 */
public class Assignment {
    Subject subject;
    String title;
    String description;
    String forParents;
    int estimatedWorkLoad; // In minutes
    int deadlineDayNumber; // day in week
    boolean finished = false;

    /**
     * Initialize the assignment with info. See constructor below for non-parameter call.
     * @param subject
     * @param title
     * @param description
     * @param forParents
     * @param estimatedWorkLoad
     */
    public Assignment(Subject subject, String title, String description, String forParents, int estimatedWorkLoad) {
        this.subject = subject;
        this.title = title;
        this.description = description;
        this.forParents = forParents;
        this.estimatedWorkLoad = estimatedWorkLoad;
    }

    /**
     * Initialize the Assignement with no subject. This means that this session is set by the teacher.
     */
    public Assignment() {
        // Null = Teacher scheduled!
        this.subject = null;
    }

    /**
     * Set the daynumber of when this assignment is due.
     * @param dayNumber
     */
    public void setDeadlineDayNumber(int dayNumber) {
        this.deadlineDayNumber = dayNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getForParents() { return forParents; }

    public String getTitle() {
        return title;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getDeadlineDayNumber() {
        return deadlineDayNumber;
    }

    public int getEstimatedWorkLoad() { return estimatedWorkLoad; }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished() {
        finished = true;
    }
}
