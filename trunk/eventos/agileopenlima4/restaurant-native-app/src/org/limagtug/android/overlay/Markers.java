package org.limagtug.android.overlay;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * Clase que representa un layer donde se colocaran los diferentes markers a
 * agregar al mapa.
 * 
 * @author limagtug
 * 
 */
public class Markers extends ItemizedOverlay<OverlayItem>
{
	private List<OverlayItem> markers;

	private Context context;

	/**
	 * @param defaultMarker
	 */
	public Markers(Drawable defaultMarker)
	{
		super(boundCenterBottom(defaultMarker));
		markers = new ArrayList<OverlayItem>();
		populate();
	}

	/**
	 * @param defaultMarker
	 * @param context
	 */
	public Markers(Drawable defaultMarker, Context context)
	{
		super(boundCenterBottom(defaultMarker));
		this.context = context;
		markers = new ArrayList<OverlayItem>();
		populate();
	}

	/**
	 * @param marker
	 */
	public void addMarker(OverlayItem marker)
	{
		markers.add(marker);
		populate();
	}

	/**
	 * 
	 */
	public void removeMarkers()
	{
		markers.clear();
	}

	/**
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected OverlayItem createItem(int i)
	{
		return markers.get(i);
	}

	/**
	 * @see com.google.android.maps.ItemizedOverlay#size()
	 */
	@Override
	public int size()
	{
		return markers.size();
	}

	/**
	 * @see com.google.android.maps.ItemizedOverlay#onTap(int)
	 */
	@Override
	protected boolean onTap(int i)
	{
		String toast = "Lugar: " + markers.get(i).getTitle();
		toast += "\nDescripcion: " + markers.get(i).getSnippet();
		Toast.makeText(this.context, toast, Toast.LENGTH_LONG).show();

		return true;
	}
}