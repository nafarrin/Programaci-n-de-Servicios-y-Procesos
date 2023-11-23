package ServidorHilos;

import java.io.InputStream;
import java.io.*;
import java.net.Socket;

public class Peticion implements Runnable{
    BufferedReader bfr;
    PrintWriter pw;
    Socket socket;
    public Peticion(Socket socket){
        this.socket=socket;
    }
    public int extraerNumero(String linea){
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

    public int calcular(String op, String n1, String n2){
        int resultado=0;
        char simbolo=op.charAt(0);
        int num1=this.extraerNumero(n1);
        int num2=this.extraerNumero(n2);
        if (simbolo=='+'){
            resultado=num1+num2;
        }
        return resultado;
    }
    public void run(){
        try {
            InputStream is=socket.getInputStream();
            InputStreamReader isr=
                    new InputStreamReader(is);
            bfr=new BufferedReader(isr);
            OutputStream os=socket.getOutputStream();
            pw=new PrintWriter(os);
            String linea;
            while (true){
                linea = bfr.readLine();
                String num1=bfr.readLine();
                String num2=bfr.readLine();
                /* Calculamos el resultado*/
                Integer result=this.calcular(linea, num1, num2);
                System.out.println("El servidor dio resultado:"+result);
                pw.write(result.toString()+"\n");
                pw.flush();
            }
        } catch (IOException e) {
        }
    }
}
