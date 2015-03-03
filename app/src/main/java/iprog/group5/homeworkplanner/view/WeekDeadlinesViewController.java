package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Niklas on 2015-03-03.
 */
public class WeekDeadlinesViewController{

    public PlannerModel model;
    public WeekDeadlinesView view;
    public Activity activity;

    public WeekDeadlinesViewController(PlannerModel model, WeekDeadlinesView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;
    }

}
