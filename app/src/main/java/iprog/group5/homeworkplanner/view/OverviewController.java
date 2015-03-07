package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import iprog.group5.homeworkplanner.ScheduleActivity;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-03-06.
 */
public class OverviewController implements AdapterView.OnItemClickListener{
    public PlannerModel model;
    public OverviewView view;
    public Activity activity;

    public OverviewController(PlannerModel model, OverviewView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;

        // Add listeners here
        view.list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(activity, ScheduleActivity.class);
        intent.putExtra("position", position);
        view.getContext().startActivity(intent);
    }
}