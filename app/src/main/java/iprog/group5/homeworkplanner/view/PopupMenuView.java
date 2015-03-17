package iprog.group5.homeworkplanner.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Erica on 2015-03-17.
 */
public class PopupMenuView implements Observer{

    // Base variables
    PlannerModel model;
    View view;

    TextView popupTitle;
    TextView typeText;

    ImageView closeBtn;

    public PopupMenuView(PlannerModel model, View view, int week_nr, int day_nr, int position, String type) {
        // Model and root view
        this.model = model;
        this.view = view;

        // Subscribe to Observer
        model.addObserver(this);

        Day day = model.getDaysOfWeek(week_nr).get(day_nr);

        popupTitle = (TextView) view.findViewById(R.id.popup_title);
        typeText = (TextView) view.findViewById(R.id.type_text);

        closeBtn = (ImageView) view.findViewById(R.id.popup_close_btn);

        if (type.equalsIgnoreCase("uppgift")) {
            popupTitle.setText(day.getAssignment().getSubject().getName());
            typeText.setText(type);
        } else {
            if (day.getSession(position).getAssignment().getSubject().getName().equalsIgnoreCase("custom")) {
                popupTitle.setText(day.getSession(position).getAssignment().getTitle());
                typeText.setText("");
            } else {
                 popupTitle.setText(day.getSession(position).getAssignment().getSubject().getName());
                typeText.setText(type);
            }
        }
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
