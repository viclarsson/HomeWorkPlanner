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

    int stars = 0;
    ArrayList<Week> weeks;

    public PlannerModel() {
        Subject math = new Subject("Math", Color.parseColor("#FF6600"));
        Subject swedish = new Subject("Svenska", Color.parseColor("#FF00FF"));

        weeks = new ArrayList<Week>();

        Week week1 = new Week(1);

        // Adds assignments
        Assignment math1 = new Assignment(math, "Räkneläxa", "Gör 4 tal i boken.", 60);
        Assignment swedish1 = new Assignment(swedish, "Glosor", "Se veckobrev.", 30);

        week1.days.get(Week.TUESDAY).setAssignment(math1);
        week1.days.get(Week.FRIDAY).setAssignment(swedish1);

        HomeWorkSession testSession1 = new HomeWorkSession(math1);
        HomeWorkSession testSession2 = new HomeWorkSession(swedish1);
        HomeWorkSession testSession3 = new HomeWorkSession(swedish1);
        week1.days.get(Week.MONDAY).addSessionAtTime(5, testSession1);
        week1.days.get(Week.WEDNESDAY).addSessionAtTime(10, testSession2);
        week1.days.get(Week.TUESDAY).addSessionAtTime(11, testSession3);

        Week week2 = new Week(2);
        weeks.add(week1);
        weeks.add(week2);
    }

    public String getStars() {
        // Stefan Nilsson hack
        return "" + weeks.size();
    }

    public ArrayList<Day> getDaysOfWeek(int weekNumber) {
        Week week = weeks.get(weekNumber);
        return week.getDays();
    }

    public ArrayList<HomeWorkSession> getSessionsByDay(int weekId, int dayId) {
        return weeks.get(weekId).getDay(dayId).getSessions();
    }

    public ArrayList<Week> getWeeks() {
        return this.weeks;
    }

    public boolean addSession(int weekNumber, int dayNumber, int position, Assignment assignment) {
        Day day = weeks.get(weekNumber).getDay(dayNumber);
        boolean change = day.addSessionAtTime(position, new HomeWorkSession(assignment));
        if(change) {
            setChanged();
            notifyObservers();
        }
        return change;
    }

}
