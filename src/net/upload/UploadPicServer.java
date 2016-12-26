package net.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadPicServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
			
		//����tcp��socket����ˡ�
		ServerSocket ss = new ServerSocket(10006);
		
		while(true){
			Socket s = ss.accept();			
			
			new Thread(new UploadTask(s)).start();		
			
		}
		//��ȡ�ͻ��ˡ�
		
		
//		ss.close();
		
		
	}

}
