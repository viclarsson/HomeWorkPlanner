package iprog.group5.homeworkplanner.model;

/**
 * Created by Victor on 2015-02-23.
 */
public class Subject {
    String name;
    int color;

    public Subject(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }
}
