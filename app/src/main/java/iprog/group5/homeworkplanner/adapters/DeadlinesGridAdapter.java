package iprog.group5.homeworkplanner.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.Week;

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
        TextView date = (TextView) item.findViewById(R.id.date);
        TextView subject = (TextView) item.findViewById(R.id.subject);

        ImageView icon = (ImageView) item.findViewById(R.id.icon);
        FrameLayout button = (FrameLayout) item.findViewById(R.id.button);
        date.setTag(day);
        Assignment assignment = day.getAssignment();
        if(assignment != null) {
            // Chosen State
            button.setBackgroundColor(assignment.getSubject().getColor());
            icon.setImageResource(R.drawable.dragndrop);
            subject.setText(assignment.getSubject().getName());
        }

        return item;
    }
}