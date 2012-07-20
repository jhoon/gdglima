package org.limagtug.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Calculadora {

   private int n1;
   private int n2;
   private int suma;

   public Calculadora(DataInputStream in) throws IOException {
      n1 = in.readInt();
      n2 = in.readInt();
   }

   public int sumar() {
      return suma = n1 + n2;
   }

   public void enviar(DataOutputStream out) throws IOException {
      out.writeInt(suma);
   }

}
