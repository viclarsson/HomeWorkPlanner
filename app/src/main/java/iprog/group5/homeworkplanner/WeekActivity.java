package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.os.Bundle;

import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.WeekController;
import iprog.group5.homeworkplanner.view.WeekView;


public class WeekActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();

        WeekView view = new WeekView(model, findViewById(R.id.base));
        WeekController controller = new WeekController(model, view, this);
    }
}
