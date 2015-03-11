package iprog.group5.homeworkplanner.model;

import android.app.Application;
import android.graphics.Color;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.view.AnimalView;

/**
 * Created by Victor on 2015-02-23.
 */
public class PlannerModel extends Observable {

    int stars = 0;
    Hashtable<Integer, Week> weeks;
    String tigerMsg = "Welcome to the Homework Planner app! Press on a week to get started.";

    public PlannerModel() {
        initializeTestData();
    }

    public String getTigerMsg() {
        return tigerMsg;
    }

    public void setTigerMsg(String msg) {
        tigerMsg = msg;

        setChanged();
        notifyObservers("tigerUpdate");
    }

    public String getStars() {
        // Stefan Nilsson hack
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
            setChangedDay(dayNumber);
        }
        return change;
    }

    /**
     * Tests if a session is free or not
     * @param weekNumber
     * @param dayNumber
     * @param position
     * @return
     */
    public boolean isFree(int weekNumber, int dayNumber, int position) {
        ArrayList<HomeWorkSession> sessions = getSessionsByDay(weekNumber, dayNumber);
        if(sessions.get(position).getAssignment() == null) {
            return true;
        }
        return false;
    }

    /**
     * Adds a activity defined by the user.
     * @param weekNumber
     * @param dayNumber
     * @param startPosition
     * @param numberOfBlocks
     * @return
     */
    public String addOwnSession(int weekNumber, int dayNumber, int startPosition, int numberOfBlocks) {
        boolean test = true;
        int end = startPosition + numberOfBlocks;
        for(int i = startPosition; i < end; i++){
            test = isFree(weekNumber, dayNumber, i);
            if(!test) {
                return "Not possible.";
            }
        }
        // All sessions were free! Add the defined activity
        Assignment assignment = new Assignment(new Subject("own", Color.parseColor("#22dddd")), "Fotboll", "Fotbollsträning", 0);
        // Set on saturday to be able to add it to all days.
        assignment.setDeadlineDayNumber(5);
        for(int i = startPosition; i < end; i++){
            addSession(weekNumber, dayNumber, i, assignment);
        }
        return "Schemalagt!";
    }

    /**
     * Removes the own defined assignment by checking the whole day for that object.
     * @param weekNumber
     * @param dayNumber
     * @param assignment
     * @return
     */
    public boolean removeOwnSession(int weekNumber, int dayNumber, Assignment assignment) {
        ArrayList<HomeWorkSession> sessions = getDaysOfWeek(weekNumber).get(dayNumber).getSessions();
        Assignment tmp = null;
        for(HomeWorkSession s : sessions) {
            tmp = s.getAssignment();
            if(tmp != null && tmp.equals(assignment)) {
                s.setUnscheduled();
            }
        }
        setChangedDay(dayNumber);
        return true;
    }

    /**
     * Only update the listView from day
     * @param dayNumber
     */
    public void setChangedDay(int dayNumber) {
        setChanged();
        // Fix dayNumber
        notifyObservers("" + dayNumber);
    }

    public void removeSession(int weekNumber, int dayNumber, int position) {
        Day day = weeks.get(weekNumber).getDay(dayNumber);
        day.removeSessionAtTime(position);
        setChangedDay(dayNumber);
    }

    /**
     * Initialize Test data
     */
    public void initializeTestData() {
        Subject math = new Subject("Matte", Color.parseColor("#FF6600"));
        Subject swedish = new Subject("Svenska", Color.parseColor("#FF00FF"));
        Subject english = new Subject("Engelska", Color.parseColor("#66FF66"));

        weeks = new Hashtable<Integer, Week>();

        Week week1 = new Week(10, 2015);

        // Adds assignments
        Assignment math1 = new Assignment(math, "Räkneläxa", "Gör 4 tal i boken.", 60);
        Assignment swedish1 = new Assignment(swedish, "Glosor", "Se veckobrev.", 30);
        Assignment english1 = new Assignment(english, "Uppsats", "Skriv och berätta om ditt sportlov på engelska. 1 A4", 60);

        // Test teacher added
        week1.days.get(Week.MONDAY).setScheduledTime(1,13);
        week1.days.get(Week.TUESDAY).setScheduledTime(2,13);
        week1.days.get(Week.WEDNESDAY).setScheduledTime(1,13);
        week1.days.get(Week.THURSDAY).setScheduledTime(2,13);
        week1.days.get(Week.FRIDAY).setScheduledTime(2,13);

        week1.days.get(Week.TUESDAY).setAssignment(math1);
        week1.days.get(Week.FRIDAY).setAssignment(swedish1);
        week1.days.get(Week.WEDNESDAY).setAssignment(english1);

        HomeWorkSession testSession1 = new HomeWorkSession(math1);
        HomeWorkSession testSession2 = new HomeWorkSession(swedish1);
        HomeWorkSession testSession3 = new HomeWorkSession(swedish1);
        week1.days.get(Week.MONDAY).addSessionAtTime(5, testSession1); // this will fail because it is already scheduled
        week1.days.get(Week.WEDNESDAY).addSessionAtTime(20, testSession2);
        week1.days.get(Week.TUESDAY).addSessionAtTime(17, testSession3);

        Week week2 = new Week(11, 2015);
        weeks.put(week1.getWeekNumber(), week1);
        weeks.put(week2.getWeekNumber(), week2);
    }

}
