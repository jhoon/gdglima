package pe.gtug.android.hw;

import pe.gtug.android.hw.display.GestureActivity;
import pe.gtug.android.hw.gps.GPSMap;
import pe.gtug.android.hw.gps.GPSSolo;
import pe.gtug.android.hw.sensor.CompassActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Demo extends TabActivity
{
    private static final String TAG = "Demo - ";
    
    private TabHost tabHost;
    private Resources resources;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        
        initialize();
        
        // Se agrega tabs
        addTab("gestures", "Gestures", GestureActivity.class, R.drawable.tab_list);
        addTab("compass", "Compass", CompassActivity.class, R.drawable.tab_list);
        addTab("gpssolo", "GPSSolo", GPSSolo.class, R.drawable.tab_list);
        addTab("gpsmap", "GPSMap", GPSMap.class, R.drawable.tab_list);
    }

    /**
     * @param specification
     * @param title
     * @param activity
     * @param view
     */
    private void addTab(String specification, String title, Class<?> activity, int view)
    {
        Log.d(TAG, "addTab");
        
        TabSpec spec;
        Intent intent;
        
        intent = new Intent(this, activity);
        spec = tabHost.newTabSpec(specification).setIndicator(title, resources.getDrawable(view)).setContent(intent);
        
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }
    
    /**
     * 
     */
    private void initialize()
    {
        this.tabHost = getTabHost();
        this.resources = getResources();
    }
}