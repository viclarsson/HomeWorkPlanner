package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import iprog.group5.homeworkplanner.ScheduleActivity;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-10.
 */
public class SessionPopupController implements View.OnClickListener {
    public PlannerModel model;
    public SessionPopupView view;
    public Activity activity;

    int week_nr;
    int day_nr;
    int position;

    public SessionPopupController(PlannerModel model, SessionPopupView view, Activity activity, int week_nr, int day_nr, int position) {
        this.model = model;
        this.view = view;
        this.activity = activity;

        this.week_nr = week_nr;
        this.day_nr = day_nr;
        this.position = position;

        // Add listeners here
        view.removeBtn.setOnClickListener(this);
        view.closeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // On remove button click
        if (v == view.removeBtn) {
            model.removeSession(week_nr, day_nr, position);
            activity.finish(); // Close the dialog
        }
        if (v == view.closeBtn) {
            activity.finish(); // Close the dialog
        }
    }
}
