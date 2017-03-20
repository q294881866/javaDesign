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
		Selector selector = Selector.open(); // ��ѡ����
		ServerSocketChannel serverSC = ServerSocketChannel.open(); // ��ͨ��
		serverSC.configureBlocking(false); // ������
		serverSC.socket().bind(new InetSocketAddress("localhost", 9999));
		serverSC.register(selector, SelectionKey.OP_ACCEPT); // ��ͨ��ע��ѡ�����Ͷ�Ӧ�¼���ʶ
		while (true) { // ��ѯ
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