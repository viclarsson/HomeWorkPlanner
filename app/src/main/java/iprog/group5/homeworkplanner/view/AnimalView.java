package iprog.group5.homeworkplanner.view;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.adapters.OverviewWeeksListAdapter;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-03-06.
 */
public class AnimalView implements Observer {

    // Base variables
    PlannerModel model;
    View view;

    // View elements
    ImageView animal;
    TextView bubble;


    public AnimalView(PlannerModel model, View view) {
        // Model and root view
        this.model = model;
        this.view = view;

        // Subscribe to Observer
        model.addObserver(this);

        animal = (ImageView) view.findViewById(R.id.animal);
        // If we want to edit the bubble text
        bubble = (TextView) view.findViewById(R.id.bubble);
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
