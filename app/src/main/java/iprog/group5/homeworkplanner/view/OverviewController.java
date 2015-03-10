package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import iprog.group5.homeworkplanner.R;
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
        TextView weekText = (TextView) view.findViewById(R.id.week_number);
        int weekNumber = (int) Integer.parseInt((String) weekText.getText());
        intent.putExtra("week_number", weekNumber);
        view.getContext().startActivity(intent);
    }
}