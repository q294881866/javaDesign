package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/**
 *		�ڴ������Բ��ùر� 
 *
 */
public class CachedIODemo {
	public static void main(String[] args) throws IOException {
		byteStream1();
		charArray1();
	}
	
	/**
	 * �ڴ����Ķ�д
	 * @throws IOException
	 */
	public static void byteStream1() throws IOException{
		/*
		 * ������д���ڴ���
		 */
		String data  = "Ŭ���ˣ���ϧ�ˣ����������������ģ���������!";
		//���浽�ڴ���ȥ.//����-->�ڴ� 
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.write(data.getBytes());
		
		/*
		 * ������ӡ����
		 */
		
		//byte[] toByteArray() ����һ���·���� byte ���顣
		byte[] bys = bos.toByteArray();//����������
		ByteArrayInputStream bis = new  ByteArrayInputStream(bys);
		
		byte[] b= new byte[1024];
		int len = 0;
		while((len = bis.read(b)) != -1){
			String str = new String(b,0,len);
			System.out.println(str);
		}
	}
	
	
	public static void charArray1() throws IOException {
		String data = "�˺���֮�����һ���飬һ��Ե������ϧ�ң��һ�ӱ�����㲻���⣬���ֺα�ȥ��ϧ��";
		
		
		//����  -> �ڴ�
		
		CharArrayWriter cw = new  CharArrayWriter();
		
		cw.write(data);//FileOutputStream
		
		char[] cs = cw.toCharArray();//ȡ���ڴ������������
		
		
		System.out.println(cs.length);
		//���ڴ������   --->  ����
		
		
		CharArrayReader cr = new CharArrayReader(cs);
		
		char[] buff = new char[1024];
		
		int len = 0;
		while((len = cr.read(buff)) != -1){
			System.out.println(new String(buff,0,len));
		}
	}
}
