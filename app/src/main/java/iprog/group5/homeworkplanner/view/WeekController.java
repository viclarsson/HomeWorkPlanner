package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-02-23.
 */

// Implement listeners here.
public class WeekController implements View.OnClickListener {
    public PlannerModel model;
    public WeekView view;
    public Activity activity;

    public WeekController(PlannerModel model, WeekView view, Activity activity) {
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
