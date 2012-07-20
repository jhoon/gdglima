package org.limagtug.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.util.Log;

public class Calculadora {

	private static final String CATEGORIA = "libro";
	private int suma;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;

	public Calculadora(String ip, int puerto) throws IOException {
		Log.i(CATEGORIA, "Conectando en el socket " + ip + ", puerto: "
				+ puerto);
		// Abre el socket
		socket = new Socket(ip, puerto);
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		Log.i(CATEGORIA, "Conexión realizada con éxito!");
	}

	public int sumar(int n1, int n2) throws IOException {
		try {
			Log.i(CATEGORIA, "Enviando números... " + n1 + " + " + n2);
			// Envia los números
			out.writeInt(n1);
			out.writeInt(n2);
			// Envia
			out.flush();
			Log.i(CATEGORIA, "Leyendo respuesta...");
			// Lee respuesta
			suma = in.readInt();
			Log.i(CATEGORIA, "suma: " + suma);
			return suma;
		} finally {
			// Siempre cerrar los streams
			close();
		}
	}

	private void close() throws IOException {
		out.close();
		in.close();
		// Cerrar el socket
		socket.close();
	}

}
