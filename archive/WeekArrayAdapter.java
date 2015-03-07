package iprog.group5.homeworkplanner.archive;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Week;

/**
 * Created by Erica on 2015-03-05.
 */
//This adapter turns every week into a list item for the week overview view
public class WeekArrayAdapter extends ArrayAdapter<Week> {
    Context context;
    int layoutResourceId;
    ArrayList<Week> weeks = new ArrayList<Week>();

    public WeekArrayAdapter(Context context, int layoutResourceId, ArrayList<Week> weeks) {
        super(context, layoutResourceId, weeks);
        this.context = context;
        this.weeks = weeks;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;

        WeekWrapper WeekWrapper = null;

        if (item == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            item = inflater.inflate(layoutResourceId, parent, false);
            WeekWrapper = new WeekWrapper();
            WeekWrapper.number = (TextView) item.findViewById(R.id.week_number);
            WeekWrapper.description = (TextView) item.findViewById(R.id.weekDescription);
            WeekWrapper.show = (ImageView) item.findViewById(R.id.show_week);
            item.setTag(WeekWrapper);
        } else {
            WeekWrapper = (WeekWrapper) item.getTag();
        }

        Week week = weeks.get(position);
        WeekWrapper.number.setText(week.getWeekNumber()+"");

        //TODO: This should generate some description of the week, like number of deadlines etc
        WeekWrapper.description.setText(week.getNumberOfAssignments() + " läxor denna vecka.");

        return item;
    }

    static class WeekWrapper {
        TextView number;
        TextView description;
        ImageView show;
    }

}
