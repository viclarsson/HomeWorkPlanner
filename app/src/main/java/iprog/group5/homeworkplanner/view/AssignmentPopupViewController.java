package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Niklas on 2015-03-11.
 */
public class AssignmentPopupViewController implements View.OnClickListener {
    public PlannerModel model;
    public AssignmentPopupView view;
    public Activity activity;
    public int week_nr;
    public int day_nr;
    public Assignment assignment;

    public AssignmentPopupViewController(PlannerModel model, AssignmentPopupView view, Activity activity, int week_nr, int day_nr) {
        this.model = model;
        this.view = view;
        this.activity = activity;
        this.week_nr = week_nr;
        this.day_nr = day_nr;
        this.assignment = model.getWeek(week_nr).getDay(day_nr).getAssignment();



        view.assignmentDone.setOnClickListener(this);

        // Sets the view text if the assignment is finished or not
        if(assignment.isFinished()) {
            view.assignmentFinished.setText("Klar!");
        } else {
            view.assignmentFinished.setText("Inte klar");
        }

    }

    @Override
    public void onClick(View v) {
        if(v == view.assignmentDone) {

            // If the assignment isn't finish, set it to finished and add a star
            if(!assignment.isFinished()) {
                model.addStar();
                assignment.setFinished();
            }

            activity.finish();
        }
    }
}
