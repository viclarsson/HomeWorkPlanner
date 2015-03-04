package iprog.group5.homeworkplanner.model;

import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

import iprog.group5.homeworkplanner.R;

/**
 * Created by Victor on 2015-02-23.
 */
public class PlannerModel extends Observable {

    ArrayList<Week> weeks;

    public PlannerModel() {
        Subject math = new Subject("Math", Color.parseColor("#FF6600"));
        Subject swedish = new Subject("Svenska", Color.parseColor("#FFFF00"));

        weeks = new ArrayList<Week>();

        Week week1 = new Week(1);
        Assignment math1 = new Assignment(math, "Räkneläxa", "Gör 4 tal i boken.", 60);
        Assignment swedish1 = new Assignment(swedish, "Glosor", "Se veckobrev.", 30);
        week1.days.get(Week.TUESDAY).assignment = math1;
        week1.days.get(Week.FRIDAY).assignment = swedish1;

        HomeWorkSession testSession1 = new HomeWorkSession(math1);
        HomeWorkSession testSession2 = new HomeWorkSession(swedish1);
        week1.days.get(Week.WEDNESDAY).addSessionAtTime(15, testSession1);
        week1.days.get(Week.WEDNESDAY).addSessionAtTime(15.5, testSession2);

        weeks.add(week1);
    }

    public ArrayList<Week> getWeeks() {
        return this.weeks;
    }

}
