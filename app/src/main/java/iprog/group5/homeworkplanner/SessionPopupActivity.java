package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.AssignmentPopupView;
import iprog.group5.homeworkplanner.view.SessionPopupController;
import iprog.group5.homeworkplanner.view.SessionPopupView;

/**
 * Created by Erica on 2015-03-10.
 */
public class SessionPopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_popup);

        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();

        Intent intent = getIntent();
        int day_nr = 0;
        int week_nr = 0;
        int position = 0;
        if(intent != null) {
            day_nr = intent.getExtras().getInt("session_day");
            week_nr = intent.getExtras().getInt("session_week");
            position = intent.getExtras().getInt("position");
        }

        SessionPopupView view = new SessionPopupView(model, findViewById(R.id.base), week_nr, day_nr);
        SessionPopupController controller = new SessionPopupController(model, view, this,week_nr, day_nr, position);
    }
}
