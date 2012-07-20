package org.limagtug.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import junit.framework.TestCase;

public class CalculadoraSocketTest extends TestCase {

	private static final String IP = "localhost";
	private static final int PUERTO = 7777;
	private Socket socket;
	private OutputStream out;
	private InputStream in;

	@Override
	protected void setUp() throws Exception {
		socket = new Socket(IP, PUERTO);
		out = socket.getOutputStream();
		in = socket.getInputStream();
	}

	public void testSuma() throws IOException {
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());
		// Envia los números
		out.writeInt(4);
		out.writeInt(5);
		// Envia
		out.flush();
		// Lee la respuesta
		int suma = in.readInt();
		System.out.println("Suma: " + suma);
		assertEquals(9, suma);
	}

	@Override
	protected void tearDown() throws Exception {
		out.close();
		in.close();
		socket.close();
	}

}
