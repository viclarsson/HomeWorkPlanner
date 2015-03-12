package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.adapters.OverviewWeeksListAdapter;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.model.Week;

/**
 * Created by Victor on 2015-03-06.
 */
public class OverviewView implements Observer {

    // Base variables
    PlannerModel model;
    View view;

    // View elements
    TextView stars;
    ListView list;

    public OverviewView(PlannerModel model, View view) {
        // Model and root view
        this.model = model;
        this.view = view;

        // Subscribe to Observer
        model.addObserver(this);

        // Set up list
        list = (ListView) view.findViewById(R.id.listview);
        OverviewWeeksListAdapter adapter = new OverviewWeeksListAdapter(view.getContext(), R.layout.overview_weeks_list_item, model.getWeeks());
        list.setAdapter(adapter);

        // Set up stars
        stars = (TextView) view.findViewById(R.id.numberOfStars);

        update(null, null);
    }

    @Override
    public void update(Observable observable, Object data) {
        stars.setText(model.getStars());
    }
}
