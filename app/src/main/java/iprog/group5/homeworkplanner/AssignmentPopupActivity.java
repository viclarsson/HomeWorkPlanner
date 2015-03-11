package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.AssignmentPopupView;
import iprog.group5.homeworkplanner.view.AssignmentPopupViewController;

/**
 * Created by Erica on 2015-03-09.
 */
public class AssignmentPopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_popup);

        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();

        Intent intent = getIntent();
        int day_nr = 0;
        int week_nr = 0;
        if(intent != null) {
            day_nr = intent.getExtras().getInt("assignment_day");
            week_nr = intent.getExtras().getInt("assignment_week");
        }

        AssignmentPopupView view = new AssignmentPopupView(model, findViewById(R.id.base), week_nr, day_nr);
        AssignmentPopupViewController assignmentPopupViewController = new AssignmentPopupViewController(model, view, this, week_nr, day_nr);
    }
}
