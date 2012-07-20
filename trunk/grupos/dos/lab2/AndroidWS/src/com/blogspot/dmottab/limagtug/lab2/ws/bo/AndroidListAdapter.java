package com.blogspot.dmottab.limagtug.lab2.ws.bo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blogspot.dmottab.limagtug.lab2.ws.R;

/**
 * @author David Motta B.
 * dmottab.blogspot.com || www.android-peru.com
 */
public class AndroidListAdapter extends BaseAdapter 
{
	private List<AndroidPhone> androidPhones;
	private final LayoutInflater _layoutInflater;

	public AndroidListAdapter(Context context, List<AndroidPhone> states) 
	{
		androidPhones = states;
		_layoutInflater = LayoutInflater.from(context);
	}

	public int getCount() 
	{
		return androidPhones.size();
	}

	public AndroidPhone getItem(int position) 
	{
		return androidPhones.get(position);
	}

	public long getItemId(int position) 
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{
		AndroidPhone androidPhone = getItem(position);
		
		View view = convertView;
		
		if (view == null)
		{
			view = _layoutInflater.inflate(R.layout.listar_android_phone, parent, false);
		}

		TextView t = (TextView) view.findViewById(R.id.android_phones); 
		t.setText(androidPhone.getNombre()+"-"+androidPhone.getMarca()+"-"+androidPhone.getVersionSO());
		
		return view;
	}
}
