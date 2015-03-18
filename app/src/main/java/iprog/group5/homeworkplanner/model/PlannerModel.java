package iprog.group5.homeworkplanner.model;

import android.graphics.Color;
import android.os.Handler;
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

    // Sets a temporary message for the tiger
    public void setTempAnimalMessage(String msg, int time) {
        if(!animalHandlerRunning) {
            Handler handler = new Handler();
            final String theBaseAnimalMessage = baseAnimalMessage;
            String tempMessage = msg;

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
        setTempAnimalMessage(randomAnimalMessageList.get(randomNumber), 2000);
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

    public HomeWorkSession getSession(int weekNumber, int dayNumber, int position) {
        return weeks.get(weekNumber).getDay(dayNumber).getSession(position);
    }
    public int getWeekSubjectSessionCount(int week_nr, Subject subject) {
        return getWeek(week_nr).getSessionSubjectsCount().get(subject);
    }

    public int getSubjectPlannedTime(int week_nr, Subject subject) {
        return getWeekSubjectSessionCount(week_nr, subject) * 30;
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
     * TODO: Use this function when adding session, see method above. Return string with message?
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
        Assignment assignment = new Assignment(new Subject("custom", Color.parseColor("#22dddd")), title, description, "", 0);
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
        ArrayList<HomeWorkSession> sessions = getDaysOfWeek(weekNumber).get(dayNumber).getSessions();
        Assignment firstSession = sessions.get(position).getAssignment();
        System.out.println(firstSession.getTitle());
        Assignment tmp = null;
        for(HomeWorkSession s : sessions) {
            tmp = s.getAssignment();
            if(tmp != null && tmp.equals(firstSession)) {
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

        randomAnimalMessageList = new ArrayList<String>();
        randomAnimalMessageList.add("Rawr!");
        randomAnimalMessageList.add("If you click on me I will say something.");
        randomAnimalMessageList.add("I'm a tiger!");
        randomAnimalMessageList.add("You have earned " + getStars() + " stars so far.");
        randomAnimalMessageList.add("Remember to take a 5 minute break every session.");

        weeks = new Hashtable<Integer, Week>();

        Week week1 = new Week(10, 2015);

        // Adds assignments
        Assignment math1 = new Assignment(math, "Räkneläxa", "Gör 4 tal i boken.", "Viktigt att förstå division!",60);
        Assignment swedish1 = new Assignment(swedish, "Glosor","Orden till kapitel 2.", "Se veckobrev.", 30);
        Assignment english1 = new Assignment(english, "Uppsats", "Skriv och berätta om ditt sportlov på engelska. 1 A4", "Försök få med orden från kaitel 6 i boken.", 60);

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
