package iprog.group5.homeworkplanner.view;

import android.content.Context;
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
    Context context;

    TextView popupTitle;
    TextView typeText;

    ImageView closeBtn;

    public PopupMenuView(PlannerModel model, View view, int week_nr, int day_nr, int position, String type) {
        // Model and root view
        this.model = model;
        this.view = view;
        this.context = view.getContext();

        // Subscribe to Observer
        model.addObserver(this);

        Day day = model.getDaysOfWeek(week_nr).get(day_nr);

        popupTitle = (TextView) view.findViewById(R.id.popup_title);
        typeText = (TextView) view.findViewById(R.id.type_text);

        closeBtn = (ImageView) view.findViewById(R.id.popup_close_btn);

        /*
        If clicked item is an assignment, get subject from the clicked assignment. Else get subject
        from the clicked sessions assignment.
         */
        if (type.equalsIgnoreCase("assignment")) {
            popupTitle.setText(day.getAssignment().getSubject().getName());
            typeText.setText(context.getText(R.string.assignment));
        } else if(type.equalsIgnoreCase("addcustom")) {
            popupTitle.setText(context.getText(R.string.add_activity));
            typeText.setText("");
        } else {
            /*
            If the clicked session is a custom assignment, don't set a type and set the menu title
            to the session title instead of the session subject. This could probably be done in a
            better way...
             */
            if (day.getSession(position).getAssignment().getSubject().getName().equalsIgnoreCase("custom")) {
                popupTitle.setText(day.getSession(position).getAssignment().getTitle());
                typeText.setText(context.getText(R.string.custom_activity));
            } else {
                popupTitle.setText(day.getSession(position).getAssignment().getSubject().getName());
                typeText.setText(context.getText(R.string.homework_session));
            }
        }
    }

    /**
     * When not initiated with a day number and position, this menu must be for the stats view.
     *
     * @param model
     * @param view
     * @param week_nr
     * @param type
     */
    public PopupMenuView(PlannerModel model, View view, int week_nr, String type) {
        // Model and root view
        this.model = model;
        this.view = view;
        this.context = view.getContext();

        // Subscribe to Observer
        model.addObserver(this);

        popupTitle = (TextView) view.findViewById(R.id.popup_title);
        typeText = (TextView) view.findViewById(R.id.type_text);

        closeBtn = (ImageView) view.findViewById(R.id.popup_close_btn);

        popupTitle.setText("" + context.getText(R.string.week) + " " + week_nr);
        typeText.setText(context.getText(R.string.stats));
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
