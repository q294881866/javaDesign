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
		// 1.��ȡ�������������localhost:10002������
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		// 2.�������ݣ���ӡ�յ��ַ�����
		pw.println("TcpClient");// ����Ҫע��println�Ż��Զ�ˢ��
		System.out.println(in.readLine());
		// 3.�ر���Դ��
		in.close();
		pw.close();
		s.close();
	}
}
