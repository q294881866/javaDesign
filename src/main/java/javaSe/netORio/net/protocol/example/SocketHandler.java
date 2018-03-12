package javaSe.netORio.net.protocol.example;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class SocketHandler implements Closeable{

    BufferedReader in;
    PrintWriter out;
    
    private Thread read;

    public SocketHandler(Socket s) {
        try {
            in =  new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void readStart() {
        read = new Thread(new ReaderService(this));
        read.start();
    }
    
    public void write(String ret) {
        out.println(ret);
    }



    @Override
    public void close() throws IOException {
        in.close();
        out.close();
    }

}
