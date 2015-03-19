package iprog.group5.homeworkplanner.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.model.Assignment;
import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.model.Subject;

/**
 * Created by Erica on 2015-03-09.
 */
public class StatsPopupView implements Observer {

    // Base variables
    PlannerModel model;
    View view;
    Context context;

    int barWidth = 60;

    TextView statsTime;
    TextView typeText;

    public StatsPopupView(PlannerModel model, View view, int week_nr) {
        // Model and root view
        this.model = model;
        this.view = view;
        this.context = view.getContext();

        statsTime = (TextView) view.findViewById(R.id.stats_time);

        LinearLayout chart =  (LinearLayout) view.findViewById(R.id.chart);
        HashMap<Subject, Integer> sessionCounts = model.getWeek(week_nr).getSessionSubjectsCount();

        int totalTime = model.getTotalSessionTime(week_nr);

        statsTime.setText("" + (totalTime/60) + "h " + (totalTime%60) + "min");

        for (HashMap.Entry<Subject, Integer> entry : sessionCounts.entrySet()) {
            Subject subject = entry.getKey();
            int value = entry.getValue();

            // Use inflater
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Don't count custom sessions to the stats
            if (!subject.getName().equalsIgnoreCase("custom")) {

                Assignment assignment = model.getWeek(week_nr).getAssignmentBySubject(subject);
                int amplifier = 40;

                int color = subject.getColor();
                float[] hsv = new float[3];
                Color.colorToHSV(color, hsv);
                hsv[2] *= 0.75f; // value component
                int darker_color = Color.HSVToColor(hsv);

                // Inflate the block by its XML
                View item = inflater.inflate(R.layout.distribution_block, chart, false);
                FrameLayout bar = (FrameLayout) item.findViewById(R.id.bar);
                LinearLayout.LayoutParams barDimensions = new LinearLayout.LayoutParams(0, value*amplifier, 0.5f);
                bar.setLayoutParams(barDimensions);
                bar.setBackgroundColor(color);
                TextView barText = (TextView) item.findViewById(R.id.bar_text);
                barText.setText("" + value);

                FrameLayout barDark = (FrameLayout) item.findViewById(R.id.bar_darken);
                int workload = assignment.getEstimatedWorkLoad() / 25;
                LinearLayout.LayoutParams barDarkDimensions = new LinearLayout.LayoutParams(0, workload*amplifier , 0.5f);
                barDark.setLayoutParams(barDarkDimensions);
                barDark.setBackgroundColor(darker_color);
                TextView barTextDark = (TextView) item.findViewById(R.id.bar_text_darken);
                barTextDark.setText("" + workload);

                TextView barSubject = (TextView) item.findViewById(R.id.bar_subject);
                barSubject.setText(assignment.getSubject().getName());

                chart.addView(item);


                /*
                //Create a bar for the subject
                TextView newBarView1 = new TextView(view.getContext());
                newBarView1.setWidth(barWidth);
                newBarView1.setHeight(value * barWidth);
                newBarView1.setBackgroundColor(color);
                newBarView1.setText(value + "");
                newBarView1.setGravity(Gravity.BOTTOM);

                Assignment assignment = model.getWeek(week_nr).getAssignmentBySubject(subject);

                //Create a bar for the estimated workload for the assignment in this subject
                TextView newBarView2 = new TextView(view.getContext());
                newBarView2.setWidth(barWidth);
                newBarView2.setHeight((assignment.getEstimatedWorkLoad() / 25) * barWidth);
                newBarView2.setBackgroundColor(darker_color);
                newBarView2.setText((assignment.getEstimatedWorkLoad() / 25) + "");
                newBarView2.setGravity(Gravity.BOTTOM);

                chart.addView(newBarView1);
                chart.addView(newBarView2);

                //Add subject labels below the bars
                TextView newLabelView = new TextView(view.getContext());
                newLabelView.setWidth(barWidth * 2);
                newLabelView.setText(subject.getName());
                newLabelView.setGravity(Gravity.CENTER_HORIZONTAL);
                */
            }


        }
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
