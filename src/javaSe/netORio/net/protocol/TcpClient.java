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
		// ����10002�˿ڣ��򱾻�������
		Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),10002);
		// 1.������
		OutputStream out = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(out);
		pw.println("TcpClient");
		pw.close();
		/*
		 * ���ͨ��Ϊ������ģʽ�����׳� IllegalBlockingModeException��
		 * ������� write ���� 
		 * �������� read ����
		 */
		
		//��ȡ����˷��ص�����,ʹ��socket��ȡ���� 
		InputStream in = socket.getInputStream();
		int length = in.available();
		if (length>0) {
			byte[] bs = new byte[length];
			System.out.println(new String(bs));
		}
		//�ر���Դ��
		socket.close();
	}

}
