package iprog.group5.homeworkplanner.view;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-09.
 */
public class AssignmentPopupView implements Observer {

    // Base variables
    PlannerModel model;
    View view;

    // View elements
    TextView assignmentTitle;
    TextView assignmentDescription;
    TextView assignmentSubject;
    TextView assignmentWorkload;
    TextView assignmentFinished;

    ImageView assignmentDone;


    public AssignmentPopupView(PlannerModel model, View view, int week_nr, int day_nr) {
        // Model and root view
        this.model = model;
        this.view = view;

        // Subscribe to Observer
        model.addObserver(this);

        Day day = model.getDaysOfWeek(week_nr).get(day_nr);

        assignmentSubject = (TextView) view.findViewById(R.id.assignment_subject);
        assignmentTitle = (TextView) view.findViewById(R.id.assignment_title);
        assignmentDescription = (TextView) view.findViewById(R.id.assignment_description);
        assignmentWorkload = (TextView) view.findViewById(R.id.assignment_workload);
        assignmentFinished = (TextView) view.findViewById(R.id.assignment_finished);

        assignmentSubject.setText(day.getAssignment().getSubject().getName());
        assignmentTitle.setText(day.getAssignment().getTitle());
        assignmentDescription.setText(day.getAssignment().getDescription());
        assignmentWorkload.setText(day.getAssignment().getEstimatedWorkLoad() + "");


        assignmentDone = (ImageView) view.findViewById(R.id.assignment_done);

    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
