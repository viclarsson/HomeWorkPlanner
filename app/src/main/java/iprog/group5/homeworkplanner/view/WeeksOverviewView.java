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
    WeekArrayAdapter adapter;


    public WeeksOverviewView(PlannerModel model, View rootView) {
        this.rootView = rootView;
        this.model = model;
        listView = (ListView) rootView.findViewById(R.id.listview);
        this.adapter = new WeekArrayAdapter(rootView.getContext(), R.layout.week_list_item, model.getWeeks());

        listView.setAdapter(adapter);
    }

    @Override
    public void update(Observable observable, Object data) {

    }
}