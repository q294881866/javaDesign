package javaSe.netORio.net.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket(InetAddress.getLocalHost().getHostAddress(), 10002);
		// 1.获取输入输出流，向localhost:10002发数据
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		// 2.发送数据，打印收到字符串。
		pw.println("TcpClient");// 这里要注意println才会自动刷新
		System.out.println(in.readLine());
		// 3.关闭资源。
		in.close();
		pw.close();
		s.close();
	}
}
