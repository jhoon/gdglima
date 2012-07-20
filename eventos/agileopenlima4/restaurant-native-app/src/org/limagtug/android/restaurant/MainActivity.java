package org.limagtug.android.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button btnCarta = (Button) findViewById(R.id.btnCarta); 
		Button btnLocales = (Button) findViewById(R.id.btnLocales);
		
		btnCarta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "paso", Toast.LENGTH_LONG).show();
			}
		});
		
		btnLocales.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, RestaurantMapActivity.class ));
			}
		});
	}
}