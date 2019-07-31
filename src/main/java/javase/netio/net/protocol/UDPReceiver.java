package javase.netio.net.protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.lang.*;

public class UDPReceiver {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(9999);
		// 接收数据，当然也可以回复发送端数据
		byte[] bs = new byte[1024];// 缓冲区
		DatagramPacket dp = new DatagramPacket(bs, bs.length);
		ds.receive(dp);// 阻塞式的。

		System.out.println("获取发送端：http:/" + dp.getAddress() + ":" + dp.getPort()
				+ " 数据\n" + new String(dp.getData(), 0, dp.getLength()));
		ds.close();
	}
}
