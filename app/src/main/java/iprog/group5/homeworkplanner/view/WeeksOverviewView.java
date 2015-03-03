package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.model.Week;

/**
 * Created by Erica on 2015-03-03.
 */
public class WeeksOverviewView implements Observer {

    View rootView;
    PlannerModel model;


    public WeeksOverviewView(PlannerModel model, View rootView) {
        this.rootView = rootView;
        this.model = model;

        for (Week week : model.getWeeks()) {
            //TODO: Add week row for every week, maybe with AdapterView?
        }
    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
