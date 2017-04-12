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
		String str = "男人要有三才。\n" //
				+ "首先，要有点钱财，不用担心油盐米柴。\n"//
				+ "其次，要有点文采，让人家有点小崇拜。\n" //
				+ "最后，也是最重要的，对你的态度基本上像个奴才。\n";
		// 使用DatagramPacket将数据封装到的该对象包中。
		byte[] buf = str.getBytes();
		DatagramPacket dp = new DatagramPacket(// 数据包
				buf, buf.length, InetAddress.getLocalHost(), 9999);
		ds.send(dp);
		ds.close();
	}
}
