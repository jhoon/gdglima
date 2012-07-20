package org.limagtug.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CalculadoraSocketThread extends Thread {

   private final Socket socket;

   public CalculadoraSocketThread(Socket socket) {
      this.socket = socket;
   }

   public void run() {
      try {
         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
         DataInputStream in = new DataInputStream(socket.getInputStream());
         Calculadora calc = new Calculadora(in);
         calc.sumar();
         calc.enviar(out);
         out.close();
         in.close();
         socket.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
