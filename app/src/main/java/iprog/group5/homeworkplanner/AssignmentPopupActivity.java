package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.AssignmentPopupView;
import iprog.group5.homeworkplanner.view.AssignmentPopupController;
import iprog.group5.homeworkplanner.view.PopupInstructionsView;
import iprog.group5.homeworkplanner.view.PopupMenuController;
import iprog.group5.homeworkplanner.view.PopupMenuView;

/**
 * Created by Erica on 2015-03-09.
 */
public class AssignmentPopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_popup);

        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();

        Intent intent = getIntent();
        int day_nr = 0;
        int week_nr = 0;
        int position = 0;
        if(intent != null) {
            day_nr = intent.getExtras().getInt("assignment_day");
            week_nr = intent.getExtras().getInt("assignment_week");
        }

        AssignmentPopupView view = new AssignmentPopupView(model, findViewById(R.id.base), week_nr, day_nr);
        AssignmentPopupController assignmentPopupController = new AssignmentPopupController(model, view, this, week_nr, day_nr);

        PopupMenuView menuView = new PopupMenuView(model, findViewById(R.id.base), week_nr, day_nr, position, "assignment");
        PopupMenuController popupMenuController = new PopupMenuController(model, menuView, this);

        PopupInstructionsView instructionsView = new PopupInstructionsView(model, findViewById(R.id.base), week_nr, day_nr, position, "assignment");
    }
}
