package javaSe.netORio.net.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

import javaSe.basic.Domain.User;

public class TcpServer {
	public static void main(String[] args) throws Exception {
		main0(args);
	}

    private static void main0(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10002);
		Socket s = ss.accept();
		
		InboundHandler<Boolean> handler = new ObjectInboundHandler();
		
		handler.handler(s);

		s.close();
		ss.close();
    }

}
