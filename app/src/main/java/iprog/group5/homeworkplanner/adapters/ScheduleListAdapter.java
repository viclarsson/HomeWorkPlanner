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
        HomeWorkSession session = sessions.get(i);
        View item = view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        item = inflater.inflate(R.layout.homeworksession_block, viewGroup, false);
        RelativeLayout block = (RelativeLayout) item.findViewById(R.id.block);
        Assignment assignment = session.getAssignment();
        if(assignment != null) {
            block.setBackgroundColor(assignment.getSubject().getColor());
        }
        TextView end = (TextView) item.findViewById(R.id.endTime);
        end.setText("" + 00 + ":" + ((i%2)*3) + "0");
        return item;
    }
}