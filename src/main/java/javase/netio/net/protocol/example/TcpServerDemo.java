package javase.netio.net.protocol.example;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(10002);
        Socket s = ss.accept();
        invoke(s);
    }

    private static void invoke(Socket s) {
        SocketHandler handler = new SocketHandler(s);
        handler.readStart();
    }

}
