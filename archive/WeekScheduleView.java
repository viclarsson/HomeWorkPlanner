package iprog.group5.homeworkplanner.archive;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    FrameLayout buttonMonday;
    FrameLayout buttonTuesday;
    FrameLayout buttonWednesday;
    FrameLayout buttonThursday;
    FrameLayout buttonFriday;
    TextView textMonday;
    TextView textTuesday;
    TextView textWednesday;
    TextView textThursday;
    TextView textFriday;
    ImageView iconMonday;
    ImageView iconTuesday;
    ImageView iconWednesday;
    ImageView iconThursday;
    ImageView iconFriday;

    public WeekScheduleView(PlannerModel model, View rootView) {
        this.rootView = rootView;
        this.model = model;

        buttonMonday = (FrameLayout) rootView.findViewById(R.id.buttonMonday);
        buttonTuesday = (FrameLayout) rootView.findViewById(R.id.buttonTuesday);
        buttonWednesday = (FrameLayout) rootView.findViewById(R.id.buttonWednesday);
        buttonThursday = (FrameLayout) rootView.findViewById(R.id.buttonThursday);
        buttonFriday = (FrameLayout) rootView.findViewById(R.id.buttonFriday);

        textMonday = (TextView) rootView.findViewById(R.id.deadlineMonday);
        textTuesday = (TextView) rootView.findViewById(R.id.deadlineTuesday);
        textWednesday = (TextView) rootView.findViewById(R.id.deadlineWednesday);
        textThursday = (TextView) rootView.findViewById(R.id.deadlineThursday);
        textFriday = (TextView) rootView.findViewById(R.id.deadlineFriday);

        iconMonday = (ImageView) rootView.findViewById(R.id.iconMonday);
        iconTuesday = (ImageView) rootView.findViewById(R.id.iconTuesday);
        iconWednesday = (ImageView) rootView.findViewById(R.id.iconWednesday);
        iconThursday = (ImageView) rootView.findViewById(R.id.iconThursday);
        iconFriday = (ImageView) rootView.findViewById(R.id.iconFriday);


        // Checks the models assignments and if there are any, updates the buttons
        ArrayList<Assignment> assignments = model.getAssignments();
        for(int i=0; i<assignments.size(); i++) {
            Assignment currAssignment = assignments.get(i);

            if( currAssignment.getDeadline() == 1) {
                textMonday.setText(currAssignment.getSubject().getName());
                buttonMonday.setBackgroundColor(currAssignment.getSubject().getColor());
                iconMonday.setImageResource(R.drawable.dragndrop);
            }
            else if( currAssignment.getDeadline() == 2) {
                textTuesday.setText(currAssignment.getSubject().getName());
                buttonTuesday.setBackgroundColor(currAssignment.getSubject().getColor());
                iconTuesday.setImageResource(R.drawable.dragndrop);
            }
            else if( currAssignment.getDeadline() == 3) {
                textWednesday.setText(currAssignment.getSubject().getName());
                buttonWednesday.setBackgroundColor(currAssignment.getSubject().getColor());
                iconWednesday.setImageResource(R.drawable.dragndrop);
            }
            else if( currAssignment.getDeadline() == 4) {
                textThursday.setText(currAssignment.getSubject().getName());
                buttonThursday.setBackgroundColor(currAssignment.getSubject().getColor());
                iconThursday.setImageResource(R.drawable.dragndrop);
            }
            else if( currAssignment.getDeadline() == 5) {
                textFriday.setText(currAssignment.getSubject().getName());
                buttonFriday.setBackgroundColor(currAssignment.getSubject().getColor());
                iconFriday.setImageResource(R.drawable.dragndrop);
            }
        }

    }

    @Override
    public void update(Observable observable, Object data) {

    }
}

