package pe.gtug.android.hw.display;

import java.util.List;

import pe.gtug.android.hw.R;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.Toast;

public class GestureActivity extends Activity implements OnGesturePerformedListener
{
    private GestureLibrary gestureLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawboard);

        loadResources();

        GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestures);
        gestureOverlayView.addOnGesturePerformedListener(this);
    }

    /**
     * @see android.gesture.GestureOverlayView.OnGesturePerformedListener#onGesturePerformed(android.gesture.GestureOverlayView,
     *      android.gesture.Gesture)
     */
    @Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture)
    {
        validateGesture(gesture);
    }

    /**
     * @param gesture
     */
    private void validateGesture(Gesture gesture)
    {
        List<Prediction> predictions = gestureLibrary.recognize(gesture);

        if (predictions.size() > 0)
        {
            Prediction prediction = predictions.get(0);

            if (prediction.score > 1.0)
            {
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
            }
        }
    }

     /**
     * 
     */
    private void loadResources()
    {
        this.gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.spells);

        if (!gestureLibrary.load())
        {
            finish();
        }
    }
}