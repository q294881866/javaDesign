package javaSe.netORio.net.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(10002);
        Socket s = ss.accept();
        String tmp = s.getInetAddress().getHostAddress();
        System.out.println(tmp);

        // 用缓冲流，获取数据
        BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));
        tmp = in.readLine();
        System.out.println(tmp);

        // 返回数据，自动刷新数据
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("TcpServer");
        in.close();
        out.close();
        s.close();
        ss.close();
    }

}
