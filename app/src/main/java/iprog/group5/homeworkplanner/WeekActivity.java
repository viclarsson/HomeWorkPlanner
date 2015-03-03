package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.os.Bundle;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.*;

public class WeekActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();


        // Test view and controller
        WeekView view = new WeekView(model, findViewById(R.id.base));
        WeekController controller = new WeekController(model, view, this);

        // Initialize this activity:s views and controllers (?)
        TopMenuView topMenuView = new TopMenuView(model, findViewById(R.id.base));
        TopMenuViewController topMenuViewController = new TopMenuViewController(model, topMenuView, this);

        WeekDeadlinesView weekDeadlinesView = new WeekDeadlinesView(model, findViewById(R.id.base));
        WeekDeadlinesViewController weekDeadlinesViewController = new WeekDeadlinesViewController(model, weekDeadlinesView, this);

    }
}
