package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPDemo {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {

		//��ȡ��������ip��ַ���� 
		InetAddress ip = InetAddress.getLocalHost();
		
		//��ȡ����������ip��ַ����
//		ip = InetAddress.getByName("192.168.1.110");//InetAddress.getByName("my_think");
		
		System.out.println(ip.getHostAddress());
		System.out.println(ip.getHostName());
	}

}
