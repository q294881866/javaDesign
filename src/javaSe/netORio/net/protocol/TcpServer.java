package javaSe.netORio.net.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String tmp = null;
		ServerSocket ss = new ServerSocket(10002);
		Socket s = ss.accept();
		tmp = s.getInetAddress().getHostAddress();
		System.out.println(tmp);

		// 获取数据
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		while (null != tmp) {
			System.out.println(tmp);
			tmp = in.readLine();
		}

		// 返回数据
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.flush();
		s.close();
		ss.close();

	}

}
