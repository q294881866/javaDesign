package net.protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClientDemo {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//�ͻ��˷����ݵ������
		/*
		 * Tcp���䣬�ͻ��˽����Ĺ��̡�
		 * 1������tcp�ͻ���socket����ʹ�õ���Socket����
		 * 		����ö���һ��������ȷĿ�ĵء�Ҫ���ӵ������� 
		 * 2��������ӽ����ɹ���˵�����ݴ���ͨ���ѽ�����
		 * 		��ͨ������socket�� ,�ǵײ㽨���õġ� ��Ȼ������˵������������룬���������
		 * 		��Ҫ���������������󣬿�����Socket����ȡ�� 
		 * 		����ͨ��getOutputStream(),��getInputStream()����ȡ�����ֽ�����
		 * 3��ʹ���������������д���� 
		 * 4���ر���Դ�� 
		 */
		
		
		//�����ͻ���socket����
		Socket socket = new Socket(InetAddress.getLocalHost(),10002);
		
		//��ȡsocket���е�������� 
		OutputStream out = socket.getOutputStream();
		
		
		//ʹ���������ָ��������д��ȥ��
		out.write("tcp��ʾ������������!".getBytes());
		
		//�ر���Դ��
		socket.close();
		
		
		
		
		
		
	}

}
