package iprog.group5.homeworkplanner;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-02-23.
 */
public class HomeWorkPlannerApplication extends Application {
    private PlannerModel model;
    private Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        this.context = this;
        this.model = new PlannerModel(context);
    }

    public Context getContext() {
        return context;
    }

    public PlannerModel getModel() {
        return model;
    }

    public void setModel(PlannerModel model) {
        this.model = model;
    }
 }
