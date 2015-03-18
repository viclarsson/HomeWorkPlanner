package iprog.group5.homeworkplanner.view;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-09.
 */
public class AssignmentPopupView implements Observer {

    // Base variables
    PlannerModel model;
    View view;
    Context context;

    // View elements
    TextView assignmentDone;
    TextView assignmentDeadline;

    public AssignmentPopupView(PlannerModel model, View view, int week_nr, int day_nr) {
        // Model and root view
        this.model = model;
        this.view = view;
        this.context = view.getContext();

        // Subscribe to Observer
        model.addObserver(this);

        Day day = model.getDaysOfWeek(week_nr).get(day_nr);

        assignmentDeadline = (TextView) view.findViewById(R.id.assignment_deadline);
        assignmentDone = (TextView) view.findViewById(R.id.assignment_done_btn);

        if(day.getAssignment().isFinished()) {
            assignmentDone.setText(context.getResources().getText(R.string.finished));
            assignmentDone.setBackground(context.getResources().getDrawable(R.drawable.flatbtnblue_selector));
        }

        assignmentDeadline.setText(day.getDay() + " " + day.getMonthText());
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
