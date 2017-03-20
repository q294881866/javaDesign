package javaSe.netORio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOTcpClient {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			init();
		}
	}

	private static void init() throws IOException {
		SocketChannel sc = SocketChannel.open(new InetSocketAddress("localhost", 9999));
		sc.configureBlocking(false);// ��������ģʽΪfalse
		// ������ 	��ȡ��������ڴ� allocateDirect
		ByteBuffer bb = ByteBuffer.allocate(512);
		// 1.��������
		sc.write(ByteBuffer.wrap("Hi NIO!".getBytes()));
		// 2.����
		bb.clear();
		
		sc.read(bb);
		bb.flip();
		// �����ڴ�û�� ������Ҫ�ж� hasArray
		System.out.println(new String(bb.array()));
		sc.close();
	}
}