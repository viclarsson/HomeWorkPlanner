package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.PopupMenuController;
import iprog.group5.homeworkplanner.view.PopupMenuView;
import iprog.group5.homeworkplanner.view.StatsPopupView;

/**
 * Created by Erica on 2015-03-09.
 */
public class StatsPopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_popup);

        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();

        Intent intent = getIntent();
        int week_nr = 0;

        if(intent != null) {
            week_nr = intent.getExtras().getInt("stats_week");
        }

        PopupMenuView menuView = new PopupMenuView(model, findViewById(R.id.base), week_nr, "statistik");
        PopupMenuController popupMenuController = new PopupMenuController(model, menuView, this);

        StatsPopupView view = new StatsPopupView(model, findViewById(R.id.base), week_nr);
    }
}