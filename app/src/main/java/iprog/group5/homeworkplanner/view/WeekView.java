package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-02-23.
 */
public class WeekView implements Observer {

    View rootView;
    PlannerModel model;

    //Make variables for elements
    Button greenButton;

    public WeekView(PlannerModel model, View rootView) {
        this.rootView = rootView;
        this.model = model;
    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
