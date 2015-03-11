package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.view.View;

import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-11.
 */
public class StatsPopupController implements View.OnClickListener {
    public PlannerModel model;
    public StatsPopupView view;
    public Activity activity;

    public StatsPopupController(PlannerModel model, StatsPopupView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;

        // Set Listeners
        view.closeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == view.closeBtn) {
            activity.finish();
        }
    }
}