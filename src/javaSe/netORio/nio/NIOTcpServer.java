package javaSe.netORio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOTcpServer {
	HttpHandler handler = new DefaultHttpHandlerImpl();

	public NIOTcpServer() throws Exception {
		init();
	}

	private void init() throws Exception {
		Selector selector = Selector.open(); // 打开选择器
		ServerSocketChannel serverSC = ServerSocketChannel.open(); // 打开通道
		serverSC.configureBlocking(false); // 非阻塞
		serverSC.socket().bind(new InetSocketAddress("localhost", 9999));
		serverSC.register(selector, SelectionKey.OP_ACCEPT); // 向通道注册选择器和对应事件标识
		while (true) { // 轮询
			int nKeys = selector.select();
			if (nKeys > 0) {
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					select(key);
					it.remove();
				}
			}
		}
		
		

	}

	public static void main(String[] args) throws Exception {
		new NIOTcpServer();
	}

	private void select(SelectionKey key) throws IOException {
		if (key.isConnectable()) {
			handler.connect(key);
		} else if (key.isReadable()) {
			handler.read(key);
		} else if (key.isWritable()) {
			handler.write(key);
		} else if (key.isAcceptable()) {
			handler.accept(key);
		}
	}
}