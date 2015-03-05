package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.PlannerModel;


/**
 * Created by Niklas on 2015-03-03.
 */
public class TopMenuView implements Observer {
    View rootView;
    PlannerModel model;

    //Make variables for elements
    Button leftButton;

    public TopMenuView(PlannerModel model, View rootView) {
        this.rootView = rootView;
        this.model = model;

        leftButton = (Button) rootView.findViewById(R.id.leftButton);
        leftButton.setText("WORKS");
    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
