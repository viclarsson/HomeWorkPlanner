package iprog.group5.homeworkplanner.view;

import android.media.Image;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.adapters.DeadlinesGridAdapter;
import iprog.group5.homeworkplanner.adapters.OverviewWeeksListAdapter;
import iprog.group5.homeworkplanner.adapters.ScheduleListAdapter;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-03-06.
 */
public class ScheduleView implements Observer {

    // Base variables
    PlannerModel model;
    View view;

    // View elements
    GridView deadlineHeadings;
    TextView bubble;
    ImageView stats;
    ImageView done;

    // Schedule Lists
    ListView monday;
    ListView tuesday;
    ListView wednesday;
    ListView thursday;
    ListView friday;


    public ScheduleView(PlannerModel model, View view, int weekNumber) {
        // Model and root view
        this.model = model;
        this.view = view;

        // Subscribe to Observer
        model.addObserver(this);

        // Set up deadline headings
        deadlineHeadings = (GridView) view.findViewById(R.id.deadlineHeadings);
        deadlineHeadings.setAdapter(new DeadlinesGridAdapter(model.getDaysOfWeek(weekNumber), view.getContext()));

        // Set up bubble
        bubble = (TextView) view.findViewById(R.id.bubble);
        bubble.setText(R.string.grab_deadline);

        // Set up Menu buttons
        stats = (ImageView) view.findViewById(R.id.btnStats);
        done = (ImageView) view.findViewById(R.id.btnDone);

        // Set up schedule lists
        monday = (ListView) view.findViewById(R.id.listMonday);
        tuesday = (ListView) view.findViewById(R.id.listTuesday);
        wednesday = (ListView) view.findViewById(R.id.listWednesday);
        thursday = (ListView) view.findViewById(R.id.listThursday);
        friday = (ListView) view.findViewById(R.id.listFriday);
        monday.setAdapter(new ScheduleListAdapter(model.getSessionsByDay(weekNumber, 0), view.getContext()));
        tuesday.setAdapter(new ScheduleListAdapter(model.getSessionsByDay(weekNumber, 1), view.getContext()));
        wednesday.setAdapter(new ScheduleListAdapter(model.getSessionsByDay(weekNumber, 2), view.getContext()));
        thursday.setAdapter(new ScheduleListAdapter(model.getSessionsByDay(weekNumber, 3), view.getContext()));
        friday.setAdapter(new ScheduleListAdapter(model.getSessionsByDay(weekNumber, 4), view.getContext()));
    }

    @Override
    public void update(Observable observable, Object data) {
        monday.invalidateViews();
        tuesday.invalidateViews();
        wednesday.invalidateViews();
        thursday.invalidateViews();
        friday.invalidateViews();
    }
}
