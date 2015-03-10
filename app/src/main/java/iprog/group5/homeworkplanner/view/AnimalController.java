package iprog.group5.homeworkplanner.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import iprog.group5.homeworkplanner.R;
import iprog.group5.homeworkplanner.ScheduleActivity;
import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-03-06.
 */
public class AnimalController implements View.OnClickListener {
    public PlannerModel model;
    public AnimalView view;
    public Activity activity;

    public AnimalController(PlannerModel model, AnimalView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;

        // Add listeners here
        view.animal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // Vibrate
        Vibrator vib = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -20.0f);
        animation.setDuration(100);
        animation.setRepeatCount(1);
        animation.setRepeatMode(2);
        view.animal.startAnimation(animation);
    }
}