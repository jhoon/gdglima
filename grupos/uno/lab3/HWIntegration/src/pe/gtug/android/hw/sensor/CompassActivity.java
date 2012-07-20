package pe.gtug.android.hw.sensor;

import java.util.List;

import pe.gtug.android.hw.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class CompassActivity extends Activity implements SensorEventListener
{
    private SensorManager sensorManager;

    private boolean isSensorRunning;

    private CompassView compassView;

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass);
        
        compassView = (CompassView) findViewById(R.id.compassView);
        
        init();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (isSensorRunning)
        {
            sensorManager.unregisterListener(this);
        }
    }

    /**
     * Realiza la verificacion si existe el sensor de orientacion o no. De
     * existir, registra el listener que permitira obtener la informacion de
     * orientacion.
     */
    private void init()
    {
        this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> availableSensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

        if (availableSensors.size() > 0)
        {
            sensorManager.registerListener(this, availableSensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
            isSensorRunning = true;

            Toast.makeText(this, "Start ORIENTATION sensor", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "No ORIENTATION sensor found", Toast.LENGTH_LONG).show();
            isSensorRunning = false;
            finish();
        }
    }

    /**
     * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor,
     *      int)
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    /**
     * Al cambio de orientacion, se actualiza el compass.
     * 
     * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
     */
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        compassView.updateDirection((float) event.values[0]);
    }
}