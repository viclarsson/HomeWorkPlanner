package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import iprog.group5.homeworkplanner.AssignmentPopupActivity;
import iprog.group5.homeworkplanner.OverviewActivity;
import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.ScheduleActivity;
import iprog.group5.homeworkplanner.SessionPopupActivity;
import iprog.group5.homeworkplanner.StatsPopupActivity;
import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.HomeWorkSession;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.model.Week;

/**
 * Created by Victor on 2015-03-06.
 */
public class ScheduleController implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnDragListener {
    public PlannerModel model;
    public ScheduleView view;
    public Activity activity;
    int weekNumber;

    public ScheduleController(PlannerModel model, ScheduleView view, Activity activity, int weekNumber) {
        this.model = model;
        this.view = view;
        this.activity = activity;
        this.weekNumber = weekNumber;

        // Add listeners here
        view.deadlineHeadings.setOnItemLongClickListener(this);
        view.stats.setOnClickListener(this);
        view.done.setOnClickListener(this);
        view.deadlineHeadings.setOnItemClickListener(this);

        // Drag listeners
        view.monday.setOnDragListener(this);
        view.tuesday.setOnDragListener(this);
        view.wednesday.setOnDragListener(this);
        view.thursday.setOnDragListener(this);
        view.friday.setOnDragListener(this);

        // ListItem click listeners
        view.monday.setOnItemClickListener(this);
        view.tuesday.setOnItemClickListener(this);
        view.wednesday.setOnItemClickListener(this);
        view.thursday.setOnItemClickListener(this);
        view.friday.setOnItemClickListener(this);

        // ListItem click listeners
        view.monday.setOnItemLongClickListener(this);
        view.tuesday.setOnItemLongClickListener(this);
        view.wednesday.setOnItemLongClickListener(this);
        view.thursday.setOnItemLongClickListener(this);
        view.friday.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == view.done) {
            Intent intent = new Intent(activity, OverviewActivity.class);
            v.getContext().startActivity(intent);
        }

        if (v == view.stats) {
            Intent intent = new Intent(activity, StatsPopupActivity.class);
            intent.putExtra("stats_week", weekNumber);
            v.getContext().startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // Check if in listview
        if(v.findViewById(R.id.endTime) != null) {
            HomeWorkSession session = (HomeWorkSession) v.findViewById(R.id.endTime).getTag();
            // Is it scheduled?
            if (session.getAssignment() != null) {

                // Is it school scheduled?
                if(session.getAssignment().getSubject() == null) {
                    Toast.makeText(v.getContext(), "School scheduled!", Toast.LENGTH_LONG).show();

                // Is it a user defined activity?
                } else if(session.getAssignment().getSubject().getName().equals("own")) {
                    Day day = (Day) parent.getTag();
                    model.removeOwnSession(weekNumber, day.getDayNumber(), session.getAssignment());
                    Toast.makeText(v.getContext(), "Test remove", Toast.LENGTH_SHORT).show();

                // Must be scheduled activity from deadline
                } else {
                    Day day = (Day) parent.getTag();
                    Intent intent = new Intent(activity, SessionPopupActivity.class);
                    intent.putExtra("session_week", weekNumber);
                    intent.putExtra("session_day", day.getDayNumber());
                    intent.putExtra("position", position);
                    v.getContext().startActivity(intent);
                }
            }
        } else {
            // Not listview, must be deadlineheadings
            Day day = (Day) v.findViewById(R.id.date).getTag();
            if (day.getAssignment() != null) {
                Intent intent = new Intent(activity, AssignmentPopupActivity.class);
                intent.putExtra("assignment_week", weekNumber);
                intent.putExtra("assignment_day", day.getDayNumber());
                v.getContext().startActivity(intent);
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

        // Deadline Headings On Long Click
        if(parent == view.deadlineHeadings) {
            // Element id=date has a tag attached to it with the day object
            TextView date = (TextView) v.findViewById(R.id.date);
            Day day = (Day) date.getTag();
            Assignment assignment = day.getAssignment();
            // If assigment == null => no assignment => no drag
            if (assignment == null) {
                return false;
            }
            // Vibrate
            Vibrator vib = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(50);
            // Create dragshadow and add the position to the ClipData
            DragShadow dragShadow = new DragShadow(v);
            ClipData data = ClipData.newPlainText(("" + position), "");
            v.startDrag(data, dragShadow, v, 0);
            return true;
        } else {

            // Must be some listView.
            TextView end = (TextView) v.findViewById(R.id.endTime);
            HomeWorkSession session = (HomeWorkSession) end.getTag();

            // If assigment == null => nothing planned here!
            if(session.getAssignment() == null) {
                int dayNumber = determineDayByTargetView(parent);
                // TODO: Open dialog for making user defined activity and add this code
                Toast.makeText(v.getContext(), "" + model.addOwnSession(weekNumber, dayNumber, position, 3), Toast.LENGTH_SHORT).show();
            }
        }

        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int dragEvent = event.getAction();

        switch(dragEvent)
        {
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.i("Drag Event", "Entered");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.i("Drag Event", "Exited");
                break;
            case DragEvent.ACTION_DROP:
                ListView target = (ListView) v;
                int dayNumber = determineDayByTargetView(target);
                LinearLayout dragged = (LinearLayout) event.getLocalState();
                int draggedDayNumber = Integer.parseInt(event.getClipData().getDescription().getLabel().toString());
                int position = target.pointToPosition((int) event.getX(),(int) event.getY());

                Assignment assignment = model.getDaysOfWeek(weekNumber).get(draggedDayNumber).getAssignment();
                String errorOrSuccess = model.addSession(weekNumber, dayNumber, position, assignment);
                Toast.makeText(activity.getApplicationContext(), errorOrSuccess, Toast.LENGTH_SHORT).show();
                //Toast.makeText(activity.getApplicationContext(), (dayNumber + "/" + position + "/" + draggedDayNumber), Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    /**
     * Get the day number by the target clicked.
     * @param target
     * @return
     */
    public int determineDayByTargetView(View target) {
        int dayNumber = -1;
        if(target == view.monday) {
            dayNumber = 0;
        } else if(target == view.tuesday) {
            dayNumber = 1;
        }
        else if(target == view.wednesday) {
            dayNumber = 2;
        }
        else if(target == view.thursday) {
            dayNumber = 3;
        }
        else if(target == view.friday) {
            dayNumber = 4;
        }
        return dayNumber;
    }

    private class DragShadow extends View.DragShadowBuilder {
        ColorDrawable greyBox;

        public DragShadow(View view) {
            super(view);
            ColorDrawable theColor = (ColorDrawable) view.findViewById(R.id.button).getBackground();
            greyBox = new ColorDrawable(theColor.getColor());
        }

        @Override
        public void onDrawShadow(Canvas canvas)
        {
            greyBox.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint)
        {
            View v = getView();
            int height = (int) 30;
            int width = (int) v.getWidth();
            greyBox.setBounds(0, 0, width, height);
            shadowSize.set(width, height);
            // Place the point of drag in the center of the figure (drag it in the center)
            shadowTouchPoint.set((int)width/2, (int)height/2);
        }
    }
}