package com.blogspot.dmottab.limagtug.lab2.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.blogspot.dmottab.limagtug.lab2.ws.bo.AndroidListAdapter;
import com.blogspot.dmottab.limagtug.lab2.ws.bo.AndroidPhone;
/**
 * @author David Motta B.
 * dmottab.blogspot.com || www.android-peru.com
 */
public class ListarAndroidPhone extends ListActivity {
	
	protected static final String TAG = ListarAndroidPhone.class.getSimpleName();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("info", "onCreate");		
		
		try {
			
			final String url = "http://10.24.230.49:8080/spring-android-showcase-server/androidPhones";
			
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(acceptableMediaTypes);
			
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
			
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<AndroidPhone[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, AndroidPhone[].class);
							
			List androidPhones = Arrays.asList(responseEntity.getBody());
			showResult(androidPhones);
		} 
		catch(Exception e) 
		{
			Log.e(TAG, e.getMessage(), e);
		} 
		
	}	
	
	private void showResult(List androidPhones)	{
		if (androidPhones != null)
		{	
			AndroidListAdapter adapter = new AndroidListAdapter(this, androidPhones);
			setListAdapter(adapter);
		}
		else
		{
			Toast.makeText(this, "Resultado Null!", Toast.LENGTH_LONG).show();
		}
	}

}
