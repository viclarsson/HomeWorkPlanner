package iprog.group5.homeworkplanner.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.model.Subject;

/**
 * Created by Erica on 2015-03-09.
 */
public class StatsPopupView implements Observer {

    // Base variables
    PlannerModel model;
    View view;

    public StatsPopupView(PlannerModel model, View view, int week_nr) {
        // Model and root view
        this.model = model;
        this.view = view;

        LinearLayout chart =  (LinearLayout) view.findViewById(R.id.chart);
        HashMap<Subject, Integer> sessionCounts = model.getWeek(week_nr).getSessionSubjectsCount();

        for (HashMap.Entry<Subject, Integer> entry : sessionCounts.entrySet()) {
            Subject subject = entry.getKey();
            int value = entry.getValue();

            TextView newView = new TextView(view.getContext());
            newView.setWidth(70);
            newView.setHeight(value * 50);
            newView.setBackgroundColor(subject.getColor());
            newView.setText(value + "");
            newView.setGravity(Gravity.BOTTOM);

            chart.addView(newView);
        }
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
