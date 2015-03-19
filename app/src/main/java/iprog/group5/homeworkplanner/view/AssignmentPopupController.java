package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Niklas on 2015-03-11.
 */
public class AssignmentPopupController implements View.OnClickListener {
    public PlannerModel model;
    public AssignmentPopupView view;
    public Activity activity;
    public Context context;
    public int week_nr;
    public int day_nr;
    public Assignment assignment;

    public AssignmentPopupController(PlannerModel model, AssignmentPopupView view, Activity activity, int week_nr, int day_nr) {
        this.model = model;
        this.view = view;
        this.activity = activity;
        this.week_nr = week_nr;
        this.day_nr = day_nr;
        this.assignment = model.getWeek(week_nr).getDay(day_nr).getAssignment();

        // Set Listeners
        view.assignmentDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == view.assignmentDone) {
            // If the assignment isn't finish, set it to finished and add a star
            if(!assignment.isFinished()) {
                model.addStar();
                model.setTempAnimalMessage((String) v.getContext().getResources().getText(R.string.earned_star));
                assignment.setFinished(true);
            } else if(assignment.isFinished()) {
                model.removeStar();
                model.setTempAnimalMessage((String) v.getContext().getResources().getText(R.string.remove_star));
                assignment.setFinished(false);
            }
            // Close dialog
            activity.finish();
        }
    }
}
