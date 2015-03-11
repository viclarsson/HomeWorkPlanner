package iprog.group5.homeworkplanner.model;

import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import iprog.group5.homeworkplanner.R;

/**
 * Created by Victor on 2015-02-23.
 */
public class PlannerModel extends Observable {

    int stars = 0;
    Hashtable<Integer, Week> weeks;
    ArrayList<Assignment> assignments;

    public PlannerModel() {
        Subject math = new Subject("Matte", Color.parseColor("#FF6600"));
        Subject swedish = new Subject("Svenska", Color.parseColor("#FF00FF"));
        Subject english = new Subject("Engelska", Color.parseColor("#66FF66"));

        weeks = new Hashtable<Integer, Week>();
        assignments = new ArrayList<Assignment>();

        Week week1 = new Week(10, 2015);

        // Adds assignments
        Assignment math1 = new Assignment(math, "Räkneläxa", "Gör 4 tal i boken.", 60);
        Assignment swedish1 = new Assignment(swedish, "Glosor", "Se veckobrev.", 30);
        Assignment english1 = new Assignment(english, "Uppsats", "Skriv och berätta om ditt sportlov på engelska. 1 A4", 60);
        assignments.add(math1);
        assignments.add(swedish1);
        assignments.add(english1);

        // Test teacher added
        week1.days.get(Week.MONDAY).setScheduledTime(0,7);
        week1.days.get(Week.TUESDAY).setScheduledTime(2,7);
        week1.days.get(Week.WEDNESDAY).setScheduledTime(0,7);
        week1.days.get(Week.THURSDAY).setScheduledTime(2,7);
        week1.days.get(Week.FRIDAY).setScheduledTime(2,5);

        week1.days.get(Week.TUESDAY).setAssignment(math1);
        week1.days.get(Week.FRIDAY).setAssignment(swedish1);
        week1.days.get(Week.WEDNESDAY).setAssignment(english1);

        HomeWorkSession testSession1 = new HomeWorkSession(math1);
        HomeWorkSession testSession2 = new HomeWorkSession(swedish1);
        HomeWorkSession testSession3 = new HomeWorkSession(swedish1);
        week1.days.get(Week.MONDAY).addSessionAtTime(5, testSession1); // this will fail because it is already scheduled
        week1.days.get(Week.WEDNESDAY).addSessionAtTime(10, testSession2);
        week1.days.get(Week.TUESDAY).addSessionAtTime(11, testSession3);

        Week week2 = new Week(11, 2015);
        weeks.put(week1.getWeekNumber(), week1);
        weeks.put(week2.getWeekNumber(), week2);
    }

    public String getStars() {
        // Stefan Nilsson hack
        //return "" + weeks.size();

        return "" + stars;
    }

    public void addStar() {
        stars++;
    }

    public void removeStar() {
        if(stars > 0) {
            stars = stars - 1;
        }
    }

    public ArrayList<Day> getDaysOfWeek(int weekNumber) {
        Week week = weeks.get(weekNumber);
        return week.getDays();
    }

    public ArrayList<HomeWorkSession> getSessionsByDay(int weekId, int dayId) {
        return weeks.get(weekId).getDay(dayId).getSessions();
    }

    public ArrayList<Week> getWeeks() {
        return new ArrayList<Week>(weeks.values());
    }
    public Week getWeek(int weekNumber) {
        return weeks.get(weekNumber);
    }

    public String addSession(int weekNumber, int dayNumber, int position, Assignment assignment) {
        Day day = weeks.get(weekNumber).getDay(dayNumber);
        String change = day.addSessionAtTime(position, new HomeWorkSession(assignment));
        if(change.equals("Schemalagt!")) {
            setChanged();
            notifyObservers();
        }
        return change;
    }

    public void removeSession(int weekNumber, int dayNumber, int position) {
        Day day = weeks.get(weekNumber).getDay(dayNumber);
        day.removeSessionAtTime(position);
        setChanged();
        notifyObservers();
    }

}
