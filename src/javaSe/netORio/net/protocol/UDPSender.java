package javaSe.netORio.net.protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(8888);
		String str = "����Ҫ�����š�\n" //
				+ "���ȣ�Ҫ�е�Ǯ�ƣ����õ��������ײ�\n"//
				+ "��Σ�Ҫ�е��Ĳɣ����˼��е�С��ݡ�\n" //
				+ "���Ҳ������Ҫ�ģ������̬�Ȼ��������ū�š�\n";
		// ʹ��DatagramPacket�����ݷ�װ���ĸö�����С�
		byte[] buf = str.getBytes();
		DatagramPacket dp = new DatagramPacket(// ���ݰ�
				buf, buf.length, InetAddress.getLocalHost(), 9999);
		ds.send(dp);
		ds.close();
	}
}
