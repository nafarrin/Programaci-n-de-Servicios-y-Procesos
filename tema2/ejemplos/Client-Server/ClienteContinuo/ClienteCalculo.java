package ClienteCalculo;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteCalculo {
    public static BufferedReader getFlujo(InputStream is){
        InputStreamReader isr=
                new InputStreamReader(is);
        BufferedReader bfr=
                new BufferedReader(isr);
        return bfr;
    }
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        InetSocketAddress direccion=new
                InetSocketAddress("localhost", 9876);
        try {
            Socket socket = new Socket();
            socket.connect(direccion);
            BufferedReader bfr =
                    ClienteCalculo.getFlujo(
                            socket.getInputStream());
            PrintWriter pw = new
                    PrintWriter(socket.getOutputStream());
            pw.print("+\n");
            pw.print("42\n");
            pw.print("84\n");
            pw.flush();
            String resultado = bfr.readLine();
            System.out.println
                    ("El resultado fue:" + resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
