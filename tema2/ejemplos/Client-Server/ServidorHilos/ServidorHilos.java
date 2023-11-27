package ServidorHilos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorHilos {
    public static int extraerNumero(String linea){
        /* 1. Comprobar si es un número
         * 2. Ver si el número es correcto (32a75)
         * 3. Ver si tiene de 1 a 8 cifras
         */
        int numero;
        try{
            numero=Integer.parseInt(linea);
        }
        catch (NumberFormatException e){
            numero=0;
        }
        /* Si el número es mayor de 100 millones no
         * es válido tampoco
         */
        if (numero>=100000000){
            numero=0;
        }
        return numero;
    }

    public static int calcular(String op, String n1, String n2){
        int resultado=0;
        char simbolo=op.charAt(0);
        int num1=extraerNumero(n1);
        int num2=extraerNumero(n2);
        if (simbolo=='+'){
            resultado=num1+num2;
        }
        return resultado;
    }

    public static void escuchar() throws IOException {
        System.out.println("Arrancado el servidor");
        ServerSocket socketEscucha=null;
        try {
            socketEscucha=new ServerSocket(9876);
        } catch (IOException e) {
            System.out.println(
                    "No se pudo poner un socket "+
                            "a escuchar en TCP 9876");
            return;
        }
        while (true){
            Socket conexion=socketEscucha.accept();
            System.out.println("Conexion recibida! " );

            Peticion p=new Peticion(conexion);
            Thread hilo=new Thread (p);
            hilo.start();

            System.out.println("Resultado! "  );
        }
    }
    public  static void main(String[] args) throws IOException {
        escuchar();
    }
}


