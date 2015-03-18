package iprog.group5.homeworkplanner.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.model.Subject;

/**
 * Created by Erica on 2015-03-10.
 */
public class SessionPopupView implements Observer {

    // Base variables
    PlannerModel model;
    View view;
    Context context;

    // View elements
    TextView plannedTime;
    TextView removeBtn;

    public SessionPopupView(PlannerModel model, View view, int week_nr, int day_nr, int position) {
        // Model and root view
        this.model = model;
        this.view = view;
        this.context = view.getContext();

        // Subscribe to Observer
        model.addObserver(this);

        Day day = model.getDaysOfWeek(week_nr).get(day_nr);

        removeBtn = (TextView) view.findViewById(R.id.remove_btn);
        plannedTime = (TextView) view.findViewById(R.id.planned_time);

        Subject subject = day.getSession(position).getAssignment().getSubject();

        LinearLayout plannedTimeContainer = (LinearLayout) view.findViewById(R.id.planned_time_container);

        if (subject.getName().equalsIgnoreCase("custom")) {
            plannedTime.setText("");
            plannedTimeContainer.setVisibility(View.GONE);
        } else {
            int planned = model.getSubjectPlannedTime(week_nr, subject);
            int breaks = model.getSubjectPlannedBreakTime(week_nr, subject);

            plannedTime.setText(planned + " " + context.getResources().getText(R.string.min) + " + " + breaks + " " + context.getResources().getText(R.string.min_break));
        }
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
