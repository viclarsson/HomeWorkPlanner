package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.AnimalController;
import iprog.group5.homeworkplanner.view.AnimalView;
import iprog.group5.homeworkplanner.view.ScheduleController;
import iprog.group5.homeworkplanner.view.ScheduleView;


public class ScheduleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();

        // If this activity has been opened, a week number has been added to the intent.
        int id = 0;
        Intent intent = getIntent();
        if(intent != null) {
            id = intent.getExtras().getInt("week_number");
        }

        ScheduleView view = new ScheduleView(model, findViewById(R.id.base), id);
        ScheduleController controller = new ScheduleController(model, view, this, id);

        AnimalView animalView = new AnimalView(model, findViewById(R.id.base));
        AnimalController animalController = new AnimalController(model, animalView, this);
    }
}
