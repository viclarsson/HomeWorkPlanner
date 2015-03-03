package iprog.group5.homeworkplanner.view;

import android.view.View;
import java.util.Observable;
import java.util.Observer;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Niklas on 2015-03-03.
 */
public class HelperView implements Observer {
    View rootView;
    PlannerModel model;

    public HelperView(PlannerModel model, View rootView) {
        this.rootView = rootView;
        this.model = model;
    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
