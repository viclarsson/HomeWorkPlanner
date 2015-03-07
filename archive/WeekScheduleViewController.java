package iprog.group5.homeworkplanner.archive;

import android.app.Activity;

import iprog.group5.homeworkplanner.archive.WeekScheduleView;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Niklas on 2015-03-03.
 */
public class WeekScheduleViewController {
    public PlannerModel model;
    public WeekScheduleView view;
    public Activity activity;

    public WeekScheduleViewController(PlannerModel model, WeekScheduleView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;


    }

}
