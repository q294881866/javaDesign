package javaSe.netORio.nio;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class NIOUdpServer {

	public static void main(String[] args) throws Exception {
		Selector selector = Selector.open();
		DatagramChannel channel = DatagramChannel.open();
		channel.configureBlocking(false);
		DatagramSocket socket = channel.socket();
		socket.setReuseAddress(true);
		socket.bind(new InetSocketAddress(11111));
		channel.register(selector, SelectionKey.OP_READ);
		while (selector.select() > 0) {
			System.out.println("����channel����");
			/* �õ��Ѿ��������˵�SelectionKey�ļ��� */
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = (SelectionKey) iterator.next();
				if (key.isReadable()) {
					reveice(key);
				}
				iterator.remove();
			}
		}
	}

	public static synchronized void reveice(SelectionKey key) throws Exception {
		DatagramChannel channel = (DatagramChannel) key.channel();
		String content = "";
		// create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(1024);// java��һ��(utf-8)����3�ֽ�,gbk����ռ2���ֽ�
		buf.clear();
		SocketAddress address = channel.receive(buf); // read into buffer.
														// ���ؿͻ��˵ĵ�ַ��Ϣ
		String clientAddress = address.toString().replace("/", "").split(":")[0];
		String clientPost = address.toString().replace("/", "").split(":")[1];


		// �ڶ��η�
		ByteBuffer buf3 = ByteBuffer.allocate(65507);
		buf3.clear();
		buf3.put("�������".getBytes());
		buf3.flip();
		channel.send(buf3, new InetSocketAddress(clientAddress, Integer.parseInt(clientPost))); // ����Ϣ���͸��ͻ���
	}

}
