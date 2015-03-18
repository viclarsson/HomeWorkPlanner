package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.AddCustomSessionPopupController;
import iprog.group5.homeworkplanner.view.AddCustomSessionPopupView;
import iprog.group5.homeworkplanner.view.AssignmentPopupController;
import iprog.group5.homeworkplanner.view.AssignmentPopupView;
import iprog.group5.homeworkplanner.view.PopupMenuController;
import iprog.group5.homeworkplanner.view.PopupMenuView;

/**
 * Created by Erica on 2015-03-13.
 */
public class AddCustomSessionPopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_session_popup);

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

        PopupMenuView menuView = new PopupMenuView(model, findViewById(R.id.base), week_nr, day_nr, position, "AddCustom");
        PopupMenuController popupMenuController = new PopupMenuController(model, menuView, this);

        AddCustomSessionPopupView view = new AddCustomSessionPopupView(model, findViewById(R.id.base));
        AddCustomSessionPopupController addCustomSessionPopupController = new AddCustomSessionPopupController(model, view, this, week_nr, day_nr, position);
    }
}