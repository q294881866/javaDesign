package javaSe.netORio.net.protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver {

	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(9999);
		// 1.��������
		byte[] bs = new byte[1024];//������
		DatagramPacket dp = new DatagramPacket(bs,bs.length);
		ds.receive(dp);//����ʽ�ġ�
		System.out.println(new String(dp.getData(),0,dp.getLength()));
//		// 2.��������
//		bs = "������".getBytes();
//		ds.send(new DatagramPacket(bs, bs.length,dp.getAddress(),dp.getPort()));
		
		ds.close();
	}
	

}
