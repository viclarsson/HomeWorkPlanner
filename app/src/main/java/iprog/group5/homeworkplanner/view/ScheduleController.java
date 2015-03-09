package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iprog.group5.homeworkplanner.OverviewActivity;
import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.ScheduleActivity;
import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.HomeWorkSession;
import iprog.group5.homeworkplanner.model.PlannerModel;

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
    }

    @Override
    public void onClick(View v) {
        if (v == view.done) {
            Intent intent = new Intent(activity, OverviewActivity.class);
            v.getContext().startActivity(intent);
        }

        if (v == view.stats) {
            // TODO: Open a dialog with stats. Send info with intent
            Toast.makeText(v.getContext(), "(TODO dialog) Stats will show!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        if(v.findViewById(R.id.endTime) != null) {
            // TODO: Fix intent to open a dialog about this particular session. Send info with intent with putExtra or something.
            HomeWorkSession session = (HomeWorkSession) v.findViewById(R.id.endTime).getTag();
            if (session.getAssignment() != null) {
                if(session.getAssignment().getSubject() == null) {
                    Toast.makeText(v.getContext(), "School scheduled!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(), "(TODO dialog) " + session.getAssignment().getTitle() + ", " + session.getAssignment().getDescription(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            // TODO: Fix intent to open a dialog about this assignment. Send info with intent with putExtra or something.
            Day day = (Day) v.findViewById(R.id.date).getTag();
            if (day.getAssignment() != null) {
                Toast.makeText(v.getContext(), "(TODO dialog) " + day.getAssignment().getTitle() + ", " + day.getAssignment().getDescription(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        TextView date = (TextView) view.findViewById(R.id.date);
        Day day = (Day) date.getTag();
        Assignment assignment = day.getAssignment();
        if(assignment == null) {
            return false;
        }

        DragShadow dragShadow = new DragShadow(view);
        ClipData data = ClipData.newPlainText(("" + position),"");
        view.startDrag(data, dragShadow, view, 0);

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
                int dayNumber = 0;
                ListView target = (ListView) v;
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