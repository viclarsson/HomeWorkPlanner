package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Niklas on 2015-03-03.
 */
public class WeekScheduleViewController implements View.OnClickListener {
    public PlannerModel model;
    public WeekScheduleView view;
    public Activity activity;

    public WeekScheduleViewController(PlannerModel model, WeekScheduleView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;

        // Add listeners here.
        view.greenButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        // If more click event, we have to check which fired the event.
        if(btn == view.greenButton) {
            btn.setText("Changed from Controller!");
        }
    }
}
