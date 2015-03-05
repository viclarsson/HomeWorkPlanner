package iprog.group5.homeworkplanner.view;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Niklas on 2015-03-03.
 */
public class WeekScheduleView implements Observer {

    View rootView;
    PlannerModel model;

    //Make variables for elements
    Button buttonMonday;
    Button buttonTuesday;
    Button buttonWednesday;
    Button buttonThursday;
    Button buttonFriday;

    public WeekScheduleView(PlannerModel model, View rootView) {
        this.rootView = rootView;
        this.model = model;

        buttonMonday = (Button) rootView.findViewById(R.id.buttonMonday);
        buttonTuesday = (Button) rootView.findViewById(R.id.buttonTuesday);
        buttonWednesday = (Button) rootView.findViewById(R.id.buttonWednesday);
        buttonThursday = (Button) rootView.findViewById(R.id.buttonThursday);
        buttonFriday = (Button) rootView.findViewById(R.id.buttonFriday);


        // Checks the models assignments and if there are any, updates the buttons
        ArrayList<Assignment> assignments = model.getAssignments();
        for(int i=0; i<assignments.size(); i++) {
            Assignment currAssignment = assignments.get(i);

            if( currAssignment.getDeadline() == 1) {
                buttonMonday.setText("Deadline \n" + currAssignment.getSubject().getName());
            }
            else if( currAssignment.getDeadline() == 2) {
                buttonTuesday.setText("Deadline \n" + currAssignment.getSubject().getName());
            }
            else if( currAssignment.getDeadline() == 3) {
                buttonWednesday.setText("Deadline \n" + currAssignment.getSubject().getName());
            }
            else if( currAssignment.getDeadline() == 4) {
                buttonThursday.setText("Deadline \n" + currAssignment.getSubject().getName());
            }
            else if( currAssignment.getDeadline() == 5) {
                buttonFriday.setText("Deadline \n" + currAssignment.getSubject().getName());
            }
        }

    }

    @Override
    public void update(Observable observable, Object data) {

    }
}

