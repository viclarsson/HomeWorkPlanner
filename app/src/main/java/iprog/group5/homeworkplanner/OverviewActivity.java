package iprog.group5.homeworkplanner;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import iprog.group5.homeworkplanner.model.PlannerModel;
import iprog.group5.homeworkplanner.view.*;

public class OverviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        PlannerModel model = ((HomeWorkPlannerApplication) this.getApplication()).getModel();

        // Test View and Controller
        /*WeekView weekView = new WeekView(model, findViewById(R.id.base));
        WeekController controller = new WeekController(model, weekView, this);*/

        // Initialize this activity:s views and controllers
        //TopMenuView topMenuView = new TopMenuView(model, findViewById(R.id.base));
        //TopMenuViewController topMenuViewController = new TopMenuViewController(model, topMenuView, this);

        HelperView helperView = new HelperView(model, findViewById(R.id.base));
        HelperViewController helperViewController = new HelperViewController(model, helperView, this);

        WeekDeadlinesView weekDeadlinesView = new WeekDeadlinesView(model, findViewById(R.id.base));
        WeekDeadlinesViewController weekDeadlinesViewController = new WeekDeadlinesViewController(model, weekDeadlinesView, this);

        WeekScheduleView weekScheduleView = new WeekScheduleView(model, findViewById(R.id.base));
        WeekScheduleViewController weekScheduleViewController = new WeekScheduleViewController(model, weekScheduleView, this);

        WeeksOverviewView weeksOverviewView = new WeeksOverviewView(model, findViewById(R.id.base));

        // Add Listeners to the buttons
        findViewById(R.id.buttonMonday).setOnLongClickListener(longListen);
        findViewById(R.id.buttonTuesday).setOnLongClickListener(longListen);
        findViewById(R.id.buttonWednesday).setOnLongClickListener(longListen);
        findViewById(R.id.buttonThursday).setOnLongClickListener(longListen);
        findViewById(R.id.buttonFriday).setOnLongClickListener(longListen);

        //findViewById(R.id.textTarget).setOnDragListener(DropListener);

    }

    // Listener for the drag-able objects
    View.OnLongClickListener longListen = new View.OnLongClickListener() {


        @Override
        public boolean onLongClick(View v)
        {
            DragShadow dragShadow = new DragShadow(v);

            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, dragShadow, v, 0);
            return false;
        }
    };

    // The figure appearing when dragging
    private class DragShadow extends View.DragShadowBuilder {

        ColorDrawable greyBox;


        public DragShadow(View view) {
            super(view);
            ColorDrawable theColor = (ColorDrawable) view.getBackground();
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

            int height = (int) v.getHeight();
            int width = (int) v.getWidth();

            greyBox.setBounds(0, 0, width, height);

            shadowSize.set(width, height);
            // Place the point of drag in the center of the figure (drag it in the center)
            shadowTouchPoint.set((int)width/2, (int)height/2);
        }

    }

    // Event listening on the drag action
    View.OnDragListener DropListener = new View.OnDragListener()
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
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
                    TextView target = (TextView) v;
                    TextView dragged = (TextView) event.getLocalState();
                    target.setText(dragged.getText());

                    break;
            }

            return true;
        }
    };

}
