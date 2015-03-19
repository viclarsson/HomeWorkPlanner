package iprog.group5.homeworkplanner.model;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Random;

/**
 * Created by Victor on 2015-02-23.
 */
public class PlannerModel extends Observable {
    // Weeks in HashTable<WeekNumber, Week Object>
    Hashtable<Integer, Week> weeks;

    // Stars
    int stars = 0;

    //What the animal should say
    String baseAnimalMessage = "Welcome to the Homework Planner app! Press on a week to get started.";
    ArrayList<String> randomAnimalMessageList;

    // Check's if the animal is running a temp message
    boolean animalHandlerRunning = false;

    public PlannerModel() {
        initializeTestData();
    }

    public boolean isAnimalHandlerRunning() {
        return animalHandlerRunning;
    }

    // Sets a temporary message for the tiger
    public void setTempAnimalMessage(String msg) {
        if(!animalHandlerRunning) {
            Handler handler = new Handler();
            final String theBaseAnimalMessage = baseAnimalMessage;
            String tempMessage = msg;
            int time = 0;

            if(msg.length() < 30){
                time = 2000;
            } else {
                time = 3000;
            }


            setBaseAnimalMessage(tempMessage);
            setChanged();
            notifyObservers("tigerUpdate");
            animalHandlerRunning = true;

            handler.postDelayed(new Runnable() {
                public void run() {
                    setBaseAnimalMessage(theBaseAnimalMessage);
                    setChanged();
                    notifyObservers("tigerUpdate");
                    animalHandlerRunning = false;
                }
            }, time);

        }
    }

    public void setRandomTempAnimalMessage() {
        Random random = new Random();
        int randomNumber = random.nextInt(randomAnimalMessageList.size());
        setTempAnimalMessage(randomAnimalMessageList.get(randomNumber));
    }

    public void setBaseAnimalMessage(String msg) {
        baseAnimalMessage = msg;
    }

    public String getAnimalMessage() {
        return baseAnimalMessage;
    }

    public String getStars() {
        // Stefan Nilsson hack
        return "" + stars;
    }

    public void addStar() {
        stars++;
        setChanged();
        notifyObservers("starChanged");
    }

    public void removeStar() {
        if(stars > 0) {
            stars = stars - 1;
        }
        setChanged();
        notifyObservers("starChanged");
    }

    public ArrayList<Day> getDaysOfWeek(int weekNumber) {
        Week week = weeks.get(weekNumber);
        return week.getDays();
    }

    public ArrayList<HomeWorkSession> getSessionsByDay(int weekId, int dayId) {
        return weeks.get(weekId).getDay(dayId).getSessions();
    }

    public HomeWorkSession getSession(int weekNumber, int dayNumber, int position) {
        return weeks.get(weekNumber).getDay(dayNumber).getSession(position);
    }
    public int getWeekSubjectSessionCount(int week_nr, Subject subject) {
        return getWeek(week_nr).getSessionSubjectsCount().get(subject);
    }

    public int getSubjectPlannedTime(int week_nr, Subject subject) {
        return getWeekSubjectSessionCount(week_nr, subject) * 25;
    }

    public int getTotalSessionTime(int week_nr) {

        ArrayList<Day> days = getDaysOfWeek(week_nr);
        int totalTime = 0;

        for( Day day : days) {
            ArrayList<HomeWorkSession> sessions = day.getSessions();

            for (HomeWorkSession session : sessions) {
                if (session.getAssignment() != null) {
                    if (session.getAssignment().getSubject() != null) {
                        totalTime++;
                    }
                }
            }
        }

        return totalTime * 25;
    }

    public int getSubjectPlannedBreakTime(int week_nr, Subject subject) {
        return getWeekSubjectSessionCount(week_nr, subject) * 5;
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
        if(change.equals("Scheduled!")) {
            // Update the list of sessions by daynumber
            //setChangedDay(dayNumber);
            setChanged();
            notifyObservers("all");
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
        // Check if the session is free and not the first or last one.
        if(sessions.get(position).getAssignment() == null) {
            if(position == (Day.numberOfSessions - 1)) {
                return false; // Last one, cannot schedule that.
            } else if(position == 0) {
                return false; // First one, cannot schedule that
            }
            // Its is free!
            return true;
        }
        return false;
    }

    /**
     * Adds a activity defined by the user. Adds #numberOfBlocks sessions from startPosition.
     * @param weekNumber
     * @param dayNumber
     * @param startPosition
     * @param numberOfBlocks
     * @return
     */
    public String addCustomSession(int weekNumber, int dayNumber, int startPosition, int numberOfBlocks, String title, String description) {
        boolean test = true;
        int end = startPosition + numberOfBlocks;

        // Before adding, we must check if all sessions were free
        for(int i = startPosition; i < end; i++){
            test = isFree(weekNumber, dayNumber, i);
            if(!test) {
                return "Not possible.";
            }
        }
        // All sessions were free! Add the defined activity
        Assignment assignment = new Assignment(new Subject("custom", Color.parseColor("#7FCFD6")), title, description, "", 0);
        // Set on saturday to be able to add it to all days.
        assignment.setDeadlineDayNumber(5);
        for(int i = startPosition; i < end; i++){
            addSession(weekNumber, dayNumber, i, assignment);
        }
        return "Scheduled!";
    }

    /**
     * Removes the own defined assignment by checking the whole day for that object.
     * @param weekNumber
     * @param dayNumber
     * @return
     */
    public boolean removeCustomSession(int weekNumber, int dayNumber, int position) {
        Day day = getDaysOfWeek(weekNumber).get(dayNumber);
        ArrayList<HomeWorkSession> sessions = day.getSessions();
        Assignment assignmentToRemove = sessions.get(position).getAssignment();
        HomeWorkSession unscheduled = day.getUnscheduledSession();
        for(int index = 0; index < sessions.size(); index++) {
            if(sessions.get(index).getAssignment() == assignmentToRemove) {
                sessions.set(index, unscheduled);
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
        Subject math = new Subject("Math", Color.parseColor("#65E681"));
        Subject swedish = new Subject("Swedish", Color.parseColor("#754CEA"));
        Subject english = new Subject("English", Color.parseColor("#DD5746"));
        Subject geography = new Subject("Geography", Color.parseColor("#32A086"));
        Subject nature = new Subject("Nature", Color.parseColor("#D84AE9"));
        Subject science = new Subject("Science", Color.parseColor("#C40075"));

        randomAnimalMessageList = new ArrayList<String>();
        randomAnimalMessageList.add("Rawr!");
        randomAnimalMessageList.add("If you click on me I will say something.");
        randomAnimalMessageList.add("I'm a tiger!");
        randomAnimalMessageList.add("You have earned " + getStars() + " stars so far.");
        randomAnimalMessageList.add("Remember to take a 5 minute break every session.");
        randomAnimalMessageList.add("You can add your own activities by holding down on where you'd like to place it. Try it out!");

        weeks = new Hashtable<Integer, Week>();

        Week week1 = new Week(10, 2015);

        // Adds assignments
        Assignment math1 = new Assignment(math, "Do assignments", "Do assignment 3a-5c in the division section.", "Really try to get the children to understand the division. Use things you have at home, like apples or pears to show them!", 90);
        Assignment swedish1 = new Assignment(swedish, "Words","In the end of chapter 5, there are some words. The children are supposed to know them all.", "There are a few words that are hard to spell. Make sure they know them all by repeating alot!", 90);
        Assignment english1 = new Assignment(english, "Small essay", "Write and describe your spring holiday! Keep it to maximum of 1 A4.", "Make sure they write full sentences and mix long and short ones.", 120);

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
