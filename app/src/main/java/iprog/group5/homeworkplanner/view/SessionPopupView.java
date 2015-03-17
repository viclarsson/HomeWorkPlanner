package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-10.
 */
public class SessionPopupView implements Observer {

    // Base variables
    PlannerModel model;
    View view;

    // View elements
    TextView plannedTime;
    TextView removeBtn;

    public SessionPopupView(PlannerModel model, View view, int week_nr, int day_nr, int position) {
        // Model and root view
        this.model = model;
        this.view = view;

        // Subscribe to Observer
        model.addObserver(this);

        Day day = model.getDaysOfWeek(week_nr).get(day_nr);

        removeBtn = (TextView) view.findViewById(R.id.remove_btn);
        plannedTime = (TextView) view.findViewById(R.id.planned_time);

        int planned = model.getSubjectPlannedTime(week_nr, day.getSession(position).getAssignment().getSubject());
        int breaks = model.getSubjectPlannedBreakTime(week_nr, day.getSession(position).getAssignment().getSubject());

        plannedTime.setText(planned + " min + " + breaks + " min rast");

    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
