package javaSe.netORio.net.protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.lang.*;

public class UDPReceiver {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(9999);
		// �������ݣ���ȻҲ���Իظ����Ͷ�����
		byte[] bs = new byte[1024];// ������
		DatagramPacket dp = new DatagramPacket(bs, bs.length);
		ds.receive(dp);// ����ʽ�ġ�

		System.out.println("��ȡ���Ͷˣ�http:/" + dp.getAddress() + ":" + dp.getPort()
				+ " ����\n" + new String(dp.getData(), 0, dp.getLength()));
		ds.close();
	}
}
