package org.limagtug.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraSocket {

	private static final int PUERTO = 7777;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PUERTO);
		System.out.println("Socket abierto en el puerto 7777");
		while (true) {
			System.out.println("esperando...");
			Socket socket = serverSocket.accept();
			System.out.println("conectó!");
			new CalculadoraSocketThread(socket).start();
		}
	}

}
