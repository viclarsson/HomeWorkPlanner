package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.os.Bundle;

import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.OverviewController;
import iprog.group5.homeworkplanner.view.OverviewView;

public class OverviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();

        OverviewView view = new OverviewView(model, findViewById(R.id.base));
        OverviewController controller = new OverviewController(model, view, this);
    }
}
