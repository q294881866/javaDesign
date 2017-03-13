package javaSe.netORio.net.protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver {

	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(9999);
		// 1.接收数据
		byte[] bs = new byte[1024];//缓冲区
		DatagramPacket dp = new DatagramPacket(bs,bs.length);
		ds.receive(dp);//阻塞式的。
		System.out.println(new String(dp.getData(),0,dp.getLength()));
//		// 2.发送数据
//		bs = "好男人".getBytes();
//		ds.send(new DatagramPacket(bs, bs.length,dp.getAddress(),dp.getPort()));
		
		ds.close();
	}
	

}
