package javase.netio.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class DefaultHttpHandlerImpl implements HttpHandler {
	ServerSocketChannel serverSC = null;
	SocketChannel sc = null;

	@Override
	public void accept(SelectionKey key) throws IOException {
		System.out.println("OP_ACCEPT");
		serverSC = (ServerSocketChannel) key.channel();
		sc = serverSC.accept();
		sc.configureBlocking(false);
		// 注册为读事件处理
		sc.register(key.selector(), SelectionKey.OP_READ);
	}

	@Override
	public void connect(SelectionKey key) throws IOException {
		System.out.println("OP_CONNECT");
	}

	@Override
	public void read(SelectionKey key) throws IOException {
		System.out.println("OP_READ");
		ByteBuffer bb = ByteBuffer.allocate(512);
		sc = (SocketChannel) key.channel();
		sc.read(bb);
//		bb.flip();
		key.attach(bb);
		sc.register(key.selector(), SelectionKey.OP_WRITE);
	}

	@Override
	public void write(SelectionKey key) throws IOException {
		System.out.println("OP_WRITE");
		ByteBuffer bb = (ByteBuffer) key.attachment();
//		bb.flip();
		if (bb.hasArray()) {
			System.out.println("接受数据："+new String(bb.array()));
		}
		sc.close();
		System.out.println("流程结束！");
	}
}