package org.limagtug.socket;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EjemploCalculadora extends Activity implements OnClickListener {
	private static final String CATEGORIA = "libro";
	private static final String IP = "10.0.2.2";
	private static final int PUERTO = 7777;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.suma);
		Button b = (Button) findViewById(R.id.btSumar);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		EditText textn1 = (EditText) findViewById(R.id.n1);
		EditText textn2 = (EditText) findViewById(R.id.n2);
		TextView textSuma = (TextView) findViewById(R.id.suma);
		int n1 = Integer.parseInt(textn1.getText().toString());
		int n2 = Integer.parseInt(textn2.getText().toString());
		Calculadora calculadora;
		try {
			calculadora = new Calculadora(IP, PUERTO);
			int suma = calculadora.sumar(n1, n2);
			String textoSuma = "SUMA: " + suma;
			textSuma.setText(textoSuma);
			Log.i(CATEGORIA, String.valueOf(textoSuma));
			textSuma.setVisibility(View.VISIBLE);
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}

	}
}