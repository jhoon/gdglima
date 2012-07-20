package pe.gtug.android.hw.gps;

import pe.gtug.android.hw.R;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * @author andres
 * 
 */
public class GPSMap extends MapActivity
{
    private LocationManager locationManager;

    private LocationListener locationListener;

    private MapView mapView;

    private MapController mapController;

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpsmap);

        locationListener = new CustomLocationListener();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        mapView = (MapView) findViewById(R.id.mapview);
        mapController = mapView.getController();
    }

    /**
     * @param target
     */
    public void customClickHandler(View target)
    {
        switch (target.getId())
        {
            case R.id.zoomin:
                mapController.zoomIn();
                break;
                
            case R.id.zoomout:
                mapController.zoomOut();
                break;
        }
    }

    /**
     * @see com.google.android.maps.MapActivity#isRouteDisplayed()
     */
    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
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

                
                GeoPoint geoPoint = new GeoPoint((int) (location.getLatitude() * 1E6),
                        (int) (location.getLongitude() * 1E6));
                mapController.zoomOut();
                mapController.animateTo(geoPoint);
                mapController.zoomIn();
                mapController.setZoom(16);
                mapView.invalidate();
            }
        }

        /**
         * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
         */
        @Override
        public void onProviderDisabled(String provider)
        {
            // TODO Auto-generated method stub
        }

        /**
         * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
         */
        @Override
        public void onProviderEnabled(String provider)
        {
            // TODO Auto-generated method stub
        }

        /**
         * @see android.location.LocationListener#onStatusChanged(java.lang.String,
         *      int, android.os.Bundle)
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            // TODO Auto-generated method stub
        }
    }
}