package iprog.group5.homeworkplanner.archive;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import iprog.group5.homeworkplanner.model.PlannerModel;

/**
 * Created by Victor on 2015-02-23.
 */

// Implement listeners here.
public class WeekController implements View.OnClickListener {
    public PlannerModel model;
    public WeekView view;
    public Activity activity;

    public WeekController(PlannerModel model, WeekView view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;

        // Add listeners here.
        view.greenButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       Button btn = (Button) v;
        // If more click event, we have to check which fired the event.
        if(btn == view.greenButton) {
            btn.setText("Changed from Controller!");
        }
    }
}

//WeekController controller = new WeekController(model, weekView, this);

       /* // Add Listeners to the buttons
        findViewById(R.id.buttonMonday).setOnLongClickListener(longListen);
        findViewById(R.id.buttonTuesday).setOnLongClickListener(longListen);
        findViewById(R.id.buttonWednesday).setOnLongClickListener(longListen);
        findViewById(R.id.buttonThursday).setOnLongClickListener(longListen);
        findViewById(R.id.buttonFriday).setOnLongClickListener(longListen);

        findViewById(R.id.textTarget).setOnDragListener(DropListener);

    }

    // Listener for the drag-able objects
    View.OnLongClickListener longListen = new View.OnLongClickListener() {


        @Override
        public boolean onLongClick(View v)
        {
           DragShadow dragShadow = new DragShadow(v);

           // Calculate with textarea to get text from
           String sending = null;
           TextView tmp = null;
           switch (v.getId()) {
               case R.id.buttonMonday:
                   tmp = (TextView) v.findViewById(R.id.deadlineMonday);
                   break;
               case R.id.buttonTuesday:
                   tmp = (TextView) v.findViewById(R.id.deadlineTuesday);
                   break;
               case R.id.buttonWednesday:
                   tmp = (TextView) v.findViewById(R.id.deadlineWednesday);
                   break;
               case R.id.buttonThursday:
                   tmp = (TextView) v.findViewById(R.id.deadlineThursday);
                   break;
               case R.id.buttonFriday:
                   tmp = (TextView) v.findViewById(R.id.deadlineFriday);
                   break;
           }
            ClipData data = ClipData.newPlainText(tmp.getText(), "");
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
                   // TextView dragged = (TextView) event.getLocalState();
                    String data = event.getClipData().getDescription().getLabel().toString();
                    target.setText(data);
                    Toast toast = Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG);
                    toast.show();

                    break;
            }

            return true;
        }
    };
*/
