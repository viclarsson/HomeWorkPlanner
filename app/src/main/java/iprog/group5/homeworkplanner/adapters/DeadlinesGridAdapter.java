package iprog.group5.homeworkplanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.Day;

public class DeadlinesGridAdapter extends BaseAdapter {

    ArrayList<Day> days;
    Context context;

    public DeadlinesGridAdapter(ArrayList<Day> days, Context context) {
        this.context = context;
        this.days = days;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Day day = days.get(i);
        View item = view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        item = inflater.inflate(R.layout.deadline_heading, viewGroup, false);

        // Get and set relevant info
        TextView weekday = (TextView) item.findViewById(R.id.weekday);
        TextView date = (TextView) item.findViewById(R.id.date);
        TextView subject = (TextView) item.findViewById(R.id.subject);
        TextView dragButtonText = (TextView) item.findViewById(R.id.dragButtonText);
        ImageView icon = (ImageView) item.findViewById(R.id.dragButtonIcon);
        weekday.setText(day.getDayText());
        date.setText(day.getDay() + " " + day.getMonthText());
        // Set the day as tag to the block
        LinearLayout block = (LinearLayout) item.findViewById(R.id.dragHeadingBlock);
        block.setTag(day);

        // Check if there is an assignment due to this day
        Assignment assignment = day.getAssignment();
        if(assignment != null) {
            // It is!
            block.setBackgroundColor(assignment.getSubject().getColor());
            icon.setImageResource(R.drawable.drag_icon_dark);
            dragButtonText.setText(context.getText(R.string.dragHomework));
            subject.setText(assignment.getSubject().getName());
        }

        return item;
    }
}