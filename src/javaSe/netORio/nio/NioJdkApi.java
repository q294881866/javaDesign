package javaSe.netORio.nio;

import java.nio.MappedByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.InterruptibleChannel;
import java.nio.channels.NetworkChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.CopyOnWriteArrayList;

public class NioJdkApi {

	public static void main(String[] args) {
		/*
		 * �ⲿ
		 */
		Channels channels;
		Pipe pipe;
		SelectionKey key;
		Selector selector;

		/*
		 * ������
		 */
		FileChannel channel1;
		SelectableChannel channel2;
		ServerSocketChannel channel3;
		SocketChannel channel4;
		DatagramChannel channel5;

		/*
		 * �ӿ�
		 */
		Channel channel0;
		InterruptibleChannel channel6;
		NetworkChannel channel7;

		ReadableByteChannel channel8;
		ByteChannel channel9;
		SeekableByteChannel channel10;
		ScatteringByteChannel channel11;
		WritableByteChannel channel12;
		GatheringByteChannel channel13;

	}
	
	
	private void langApis() {
		Compiler compiler;
		System.out.println();
		Thread thread;
		ThreadGroup group;
		
		CopyOnWriteArrayList<String> list;
	}
}
