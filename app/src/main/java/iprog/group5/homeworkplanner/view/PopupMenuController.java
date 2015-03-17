package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.view.View;

import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-17.
 */
public class PopupMenuController implements View.OnClickListener {
    public PlannerModel model;
    public PopupMenuView view;
    public Activity activity;

    public PopupMenuController(PlannerModel model, PopupMenuView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;

        // Add listeners here
        view.closeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == view.closeBtn) {
            activity.finish(); // Close the dialog
        }
    }
}
