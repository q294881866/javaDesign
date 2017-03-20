package javaSe.netORio.net.protocol;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 监听10002端口，向本机发数据
		Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),10002);
		// 1.发数据
		OutputStream out = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(out);
		pw.println("TcpClient");
		pw.close();
		/*
		 * 如果通道为非阻塞模式，将抛出 IllegalBlockingModeException。
		 * 输出流的 write 操作 
		 * 输入流的 read 操作
		 */
		
		//读取服务端返回的数据,使用socket读取流。 
		InputStream in = socket.getInputStream();
		int length = in.available();
		if (length>0) {
			byte[] bs = new byte[length];
			System.out.println(new String(bs));
		}
		//关闭资源。
		socket.close();
	}

}
