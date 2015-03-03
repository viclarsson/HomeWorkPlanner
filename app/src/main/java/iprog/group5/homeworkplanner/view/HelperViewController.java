package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Niklas on 2015-03-03.
 */
public class HelperViewController {

    public PlannerModel model;
    public HelperView view;
    public Activity activity;

    public HelperViewController(PlannerModel model, HelperView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;
    }
}
