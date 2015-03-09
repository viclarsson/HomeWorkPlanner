package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-09.
 */
public class StatsPopupView implements Observer {

    // Base variables
    PlannerModel model;
    View view;

    TextView weekStatsTest;

    public StatsPopupView(PlannerModel model, View view, int week_nr) {
        // Model and root view
        this.model = model;
        this.view = view;

        weekStatsTest = (TextView) view.findViewById(R.id.week_stats_test);

        weekStatsTest.setText("Här kan vi ha lite statistik för veckan.");
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
