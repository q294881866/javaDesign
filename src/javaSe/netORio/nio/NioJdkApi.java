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
import java.sql.Types;
import java.util.Collections;
import java.util.EnumMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import javaSe.special.enums.Color;
import sun.management.snmp.jvmmib.EnumJvmJITCompilerTimeMonitoring;

public class NioJdkApi {

	public static void main(String[] args) {
		/*
		 * 外部
		 */
		Channels channels;
		Pipe pipe;
		SelectionKey key;
		Selector selector;

		/*
		 * 抽象类
		 */
		FileChannel channel1;
		SelectableChannel channel2;
		ServerSocketChannel channel3;
		SocketChannel channel4;
		DatagramChannel channel5;

		/*
		 * 接口
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
		ThreadLocal<String > local;
		Types type;
	}
	
	
	public void collections(){
		CopyOnWriteArrayList<String> list;
		EnumMap<Color, String> enumMap;
		Collections collections;
		ConcurrentLinkedQueue<String> queue;
	}
	
	
}
