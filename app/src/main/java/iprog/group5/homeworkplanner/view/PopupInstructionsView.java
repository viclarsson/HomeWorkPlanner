package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-17.
 */
public class PopupInstructionsView implements Observer{

    // Base variables
    PlannerModel model;
    View view;

    TextView assignmentDescription;
    TextView assignmentForParents;
    TextView assignmentWorkload;

    public PopupInstructionsView(PlannerModel model, View view, int week_nr, int day_nr, int position, String type) {
        // Model and root view
        this.model = model;
        this.view = view;

        assignmentDescription = (TextView) view.findViewById(R.id.assignment_description);
        assignmentForParents = (TextView) view.findViewById(R.id.assignment_for_parents);
        assignmentWorkload = (TextView) view.findViewById(R.id.assignment_workload);

        Day day = model.getDaysOfWeek(week_nr).get(day_nr);

        if (type.equalsIgnoreCase("uppgift")) {
            assignmentDescription.setText(day.getAssignment().getDescription());
            assignmentForParents.setText(day.getAssignment().getForParents());
            assignmentWorkload.setText(day.getAssignment().getEstimatedWorkLoad() + " min");
        } else {
            assignmentDescription.setText(day.getSession(position).getAssignment().getDescription());
            assignmentForParents.setText(day.getSession(position).getAssignment().getForParents());
            assignmentWorkload.setText(day.getSession(position).getAssignment().getEstimatedWorkLoad() + " min");
        }

    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
