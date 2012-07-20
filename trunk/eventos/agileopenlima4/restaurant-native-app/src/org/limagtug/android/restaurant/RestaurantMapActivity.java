package org.limagtug.android.restaurant;

import org.limagtug.android.overlay.Markers;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * Actividad encargada de controlar las acciones sobre el mapa de restaurantes.
 * 
 * @author limagtug
 * 
 */
public class RestaurantMapActivity extends MapActivity implements LocationListener
{
	private MapView restaurantMapView;
	private MapController mapController;

	private Markers restaurantMarkers;
	private Markers myLocationMarkers;

	/**
	 * @see com.google.android.maps.MapActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.map);
		
		// Se inicializa LocationManager para obtener nuestra posicion local
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

		// Se inicializa el mapa.
		initialize();

		// Se agregan markers en el mapa
		addMarkerOnMap(restaurantMarkers, -12.104333, -76.963086, "UPC", "Campus UPC"); // Campus UPC
		addMarkerOnMap(restaurantMarkers, -12.086443, -76.976631, "Jockey Plaza", "C.C. Jockey Plaza"); // Jockey Plaza
		addMarkerOnMap(restaurantMarkers, -12.132047, -77.0305, "Larcomar", "C.C. Larcomar"); // Larcomar
	}

	/**
	 * Inicializa los controles en el mapa.
	 */
	private void initialize()
	{
		// Se obtiene la referencia a la vista del map
		restaurantMapView = (MapView) findViewById(R.id.restaurantMapView);
		restaurantMapView.setBuiltInZoomControls(true);

		// Se inicializa la lista de markers
		restaurantMarkers = new Markers(getResources().getDrawable(R.drawable.restaurant_marker), this);
		myLocationMarkers = new Markers(getResources().getDrawable(R.drawable.position_marker), this);

		// Se centra el mapa en un punto dado con un zoom especifico
		mapController = restaurantMapView.getController();
		mapController.setZoom(14);
		mapController.setCenter(new GeoPoint((int) (-12.10502 * 1E6), (int) (-76.965408 * 1E6)));
	}

	/**
	 * Agrega marcadores en el mapa.
	 * 
	 * @param customLatitude
	 * @param customLongitude
	 */
	private void addMarkerOnMap(Markers markersToUse, double customLatitude, double customLongitude, String title, String snippet)
	{
		OverlayItem marker = new OverlayItem(new GeoPoint((int) (customLatitude * 1E6), (int) (customLongitude * 1E6)), title, snippet);
		markersToUse.addMarker(marker);
		restaurantMapView.getOverlays().add(markersToUse);
		restaurantMapView.postInvalidate();
	}

	/**
	 * @see com.google.android.maps.MapActivity#isRouteDisplayed()
	 */
	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}

	@Override
	public void onLocationChanged(Location location)
	{
		myLocationMarkers.removeMarkers();
		addMarkerOnMap(myLocationMarkers, location.getLatitude(), location.getLongitude(), "I'm Here", "This is my current location");
		mapController.animateTo(new GeoPoint((int) (location.getLatitude() * 1000000), (int) (location.getLongitude() * 1000000)));
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		
	}

	@Override
	public void onProviderEnabled(String provider)
	{
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		
	}
}