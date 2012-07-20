package pe.gtug.android.hw.gps;

import pe.gtug.android.hw.R;
import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class GPSSolo extends Activity
{
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpssolo);

        locationListener = new CustomLocationListener();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
    
    @Override
    protected void onPause()
    {
        locationManager.removeUpdates(locationListener);
        super.onPause();
    }
    
    @Override
    protected void onStop()
    {
        locationManager.removeUpdates(locationListener);
        super.onStop();
    }

    private class CustomLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            if (location != null)
            {
                Toast.makeText(
                        getBaseContext(),
                        "Location changed: Latitude= " + location.getLatitude() + ", Longitud= "
                                + location.getLongitude(), Toast.LENGTH_SHORT).show();
            }
        }
        
        @Override
        public void onProviderDisabled(String arg0)
        {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String arg0)
        {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2)
        {
            // TODO Auto-generated method stub
        }
    }
}