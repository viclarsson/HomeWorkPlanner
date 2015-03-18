package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.view.View;

import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-13.
 */
public class AddCustomSessionPopupController implements View.OnClickListener {
    public PlannerModel model;
    public AddCustomSessionPopupView view;
    public Activity activity;
    public int week_nr;
    public int day_nr;
    public int position;
    public Assignment assignment;

    public AddCustomSessionPopupController(PlannerModel model, AddCustomSessionPopupView view, Activity activity, int week_nr, int day_nr, int position) {
        this.model = model;
        this.view = view;
        this.activity = activity;
        this.week_nr = week_nr;
        this.day_nr = day_nr;
        this.position = position;
        this.assignment = model.getWeek(week_nr).getDay(day_nr).getAssignment();

        // Set Listeners
        view.saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == view.saveBtn) {
            //When save button is clicked, get values from input fields
            int blocks = view.numberPicker.getValue();
            String title = view.titleField.getText().toString();
            String description = view.descriptionField.getText().toString();

            model.addCustomSession(week_nr,day_nr, position, blocks, title, description);

            activity.finish();
        }
    }
}
