package iprog.group5.homeworkplanner;

import android.app.Application;

import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-02-23.
 */
public class HomeWorkPlannerApplication extends Application {
    private PlannerModel model = new PlannerModel();

    public PlannerModel getModel() {
        return model;
    }

    public void setModel(PlannerModel model) {
        this.model = model;
    }
 }
