package com.blogspot.dmottab.limagtug.lab2.ws;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.dmottab.limagtug.lab2.ws.bo.AndroidPhone;
/**
 * @author David Motta B.
 * dmottab.blogspot.com || www.android-peru.com
 */
public class RegistrarAndroidPhone extends Activity {
	
	protected static final String TAG = RegistrarAndroidPhone.class.getSimpleName();
	
	private AndroidPhone androidPhone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.main);
		Log.i("info", "onCreate");		
		
		final EditText editNombre = (EditText) findViewById(R.id.edit_text_androidphone_nombre);				
		
		final EditText editMarca = (EditText) findViewById(R.id.edit_text_androidphone_marca);		
		
		final EditText editVersion = (EditText) findViewById(R.id.edit_text_androidphone_version);
	
		Log.i("info", "onCreate fin");
		
		Button regAndroidPhone = (Button) findViewById(R.id.button_post_registrar);
        regAndroidPhone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try 
				{	
					
					androidPhone = new AndroidPhone();		
					androidPhone.setNombre(editNombre.getText().toString());	
					androidPhone.setMarca(editMarca.getText().toString());
					androidPhone.setVersionSO(editVersion.getText().toString());
					
					//Limpiar formulario
					editNombre.setText("");
					editMarca.setText("");
					editVersion.setText("");
					
					// El URL para enviar el objeto AndroidPhone
					final String url = "http://10.24.230.49:8080/spring-android-showcase-server/sendAndroidPhone";
					
					HttpHeaders requestHeaders = new HttpHeaders();
									
					// Enviar un objeto JSON o XML  = "application/json" or "application/xml"
					requestHeaders.setContentType(MediaType.APPLICATION_JSON);
					
					// llenar el objeto androidPhone  y headers en un objeto HttpEntity que sera usado para el request
					HttpEntity<AndroidPhone> requestEntity = new HttpEntity<AndroidPhone>(androidPhone, requestHeaders);
					
					// Crear una instancia RestTemplate
					RestTemplate restTemplate = new RestTemplate();
					
					// Hacer la peticion de red, que envía el objeto androidPhone, y esperando un String en la respuesta del servidor.
					ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
									
					// Return the response body to display to the user
					showResult(response.getBody());
					
				} 
				catch(Exception e) 
				{
					Log.e(TAG, e.getMessage(), e);
				} 
				
			}
		});		
        
        Button listAndroidPhone = (Button) findViewById(R.id.button_post_listar);
        listAndroidPhone.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		
        		Intent intent = new Intent();
				intent.setClass(RegistrarAndroidPhone.this, ListarAndroidPhone.class);					
		    	startActivity(intent);
        	}
		});		
		
	}	
	
	private void showResult(String result)	{
		if (result != null)
		{			
			Toast.makeText(this, result, Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "Resultado Null!", Toast.LENGTH_LONG).show();
		}
	}

}
