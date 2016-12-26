package net;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		DatagramSocket send = new DatagramSocket();
		
		DatagramSocket rece = new DatagramSocket(10001);
		new Thread(new ChatSend(send)).start();
		new Thread(new ChatRece(rece)).start();
		
	}

}
