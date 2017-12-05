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
		sc.configureBlocking(false);// 设置阻塞模式为false
		// 缓冲区 	获取申请堆外内存 allocateDirect
		ByteBuffer bb = ByteBuffer.allocate(512);
		// 1.发送数据
		sc.write(ByteBuffer.wrap("Hi NIO!".getBytes()));
		// 2.数据
		bb.clear();
		
		sc.read(bb);
		bb.flip();
		// 堆外内存没有 数组需要判断 hasArray
		System.out.println(new String(bb.array()));
		sc.close();
	}
}