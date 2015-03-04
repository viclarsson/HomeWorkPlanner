package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import android.widget.ListView;

import java.util.ArrayList;
import android.content.Context;


import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.model.Week;

/**
 * Created by Erica on 2015-03-03.
 */
public class WeeksOverviewView implements Observer {

    View rootView;
    PlannerModel model;
    ListView listView;
    MyArrayAdapter adapter;


    public WeeksOverviewView(PlannerModel model, View rootView) {
        listView = (ListView) rootView.findViewById(R.id.week_list);
        this.rootView = rootView;
        this.model = model;
        this.adapter = new MyArrayAdapter(rootView.getContext(), R.layout.week_list_item, model.getWeeks());

        listView.setAdapter(adapter);
    }

    @Override
    public void update(Observable observable, Object data) {

    }

    //This adapter turns every week into a list item for the week overview view
    private class MyArrayAdapter extends ArrayAdapter<Week> {
        Context context;
        int layoutResourceId;
        ArrayList<Week> weeks = new ArrayList<Week>();

        private MyArrayAdapter(Context context, int layoutResourceId, ArrayList<Week> weeks) {
            super(context, layoutResourceId, weeks);
            this.context = context;
            this.weeks = weeks;
            this.layoutResourceId = layoutResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;

            TextView number;
            TextView description;
            Button show;

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            item = inflater.inflate(layoutResourceId, parent, false);
            number = (TextView) item.findViewById(R.id.week_number);
            description = (TextView) item.findViewById(R.id.week_description);
            show = (Button) item.findViewById(R.id.show_week);

            Week week = weeks.get(position);
            number.setText(week.getWeekNumber());
            description.setText("Test");

            return item;
        }
    }
}
