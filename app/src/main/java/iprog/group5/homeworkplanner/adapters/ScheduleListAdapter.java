package iprog.group5.homeworkplanner.adapters;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.Day;
import iprog.group5.homeworkplanner.model.HomeWorkSession;
import iprog.group5.homeworkplanner.model.Week;

public class ScheduleListAdapter extends BaseAdapter {

    ArrayList<HomeWorkSession> sessions;
    Context context;

    public ScheduleListAdapter(ArrayList<HomeWorkSession> sessions, Context context) {
        this.context = context;
        this.sessions = sessions;
    }

    @Override
    public int getCount() {
        return sessions.size();
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
        // Get session by position
        HomeWorkSession session = sessions.get(i);
        View item = view;

        // Inflate the block by its XML
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        item = inflater.inflate(R.layout.homeworksession_block, viewGroup, false);

        // Get the view elements of the inflated block
        FrameLayout baseBlock = (FrameLayout) item.findViewById(R.id.baseBlock);
        FrameLayout block = (FrameLayout) item.findViewById(R.id.block);

        // Get the assignment for the session
        Assignment assignment = session.getAssignment();

        // If there is a assignment => some scheduled session is there!
        if(assignment != null) {

            // No subject => School Scheduled session
            if(assignment.getSubject() == null) {
                block.setBackgroundColor(context.getResources().getColor(R.color.darkBlue));
                ViewGroup.LayoutParams params = block.getLayoutParams();
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                block.setLayoutParams(params);
            } else {
                // There is some session here!
                int color = assignment.getSubject().getColor();
                block.setBackgroundColor(color);
                /*
                === Code for darkening a color (used earlier for breaks)
                 float[] hsv = new float[3];
                Color.colorToHSV(color, hsv);
                hsv[2] *= 0.8f; // value component
                int darken = Color.HSVToColor(hsv);
                baseBlock.setBackgroundColor(darken);
                */
            }
        }

        // Start and end times are set here
        TextView start = (TextView) item.findViewById(R.id.startTime);
        TextView end = (TextView) item.findViewById(R.id.endTime);
        start.setText(getHour(i, false));
        end.setText(getHour(i, true));

        // Set the session to the endTime TextView
        // TODO: Change to set the session to the block?
        end.setTag(session);
        return item;
    }

    /**
     * Get the hour depending on the position i in the list.
     * @param i     the position of the session
     * @param end   if there is an text in bottom of the block (endTime)
     * @return      the time as a string to be output in the session in the schedule
     */
    public String getHour(int i, boolean end) {
        boolean halfHour = false;
        if(i == 0 && !end) {
            return "";
        }
        if(i == (getCount()-1) && end) {
            return "";
        }
        i++;
        int hour = 6;
        int minutes = (((i)%2)*3);
        if(minutes == 3) {
            halfHour = true;
        }
        // Return nothing if half hour (startTime)
        if(!end && halfHour) {
            return "";
        }
        for(int n=0; n <= i; n += 2) {
            hour = hour + 1;
        }
        if(end) {
            if(halfHour) {
                hour++;
                minutes = 0;
            } else {
                // Return nothing if half hour (endTime)
                return "";
                //minutes = 3;
            }
        }
        return "" + hour + ":" + minutes + "0";
    }
}