package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-13.
 */
public class AddCustomSessionPopupView implements Observer {

    // Base variables
    PlannerModel model;
    View view;

    //View variables
    Button saveBtn;
    EditText descriptionField;
    EditText titleField;
    EditText hours;
    EditText minutes;

    public AddCustomSessionPopupView(PlannerModel model, View view) {
        // Model and root view
        this.model = model;
        this.view = view;

        // Subscribe to Observer
        model.addObserver(this);

        saveBtn = (Button) view.findViewById(R.id.save_btn);

        //Initiate number picker with max and min possible values
       /* numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        numberPicker.setMaxValue(6);
        numberPicker.setMinValue(1);*/

        titleField = (EditText) view.findViewById(R.id.title_field);
        descriptionField = (EditText) view.findViewById(R.id.description_field);
        hours = (EditText) view.findViewById(R.id.hours);
        minutes = (EditText) view.findViewById(R.id.minutes);

    }

    @Override
    public void update(Observable observable, Object data) {
    }
}

