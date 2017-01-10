package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/**
 *		内存流可以不用关闭 
 *
 */
public class CachedIODemo {
	public static void main(String[] args) throws IOException {
		byteStream1();
		charArray1();
	}
	
	/**
	 * 内存流的读写
	 * @throws IOException
	 */
	public static void byteStream1() throws IOException{
		/*
		 * 將数据写到内存中
		 */
		String data  = "努力了，珍惜了，问心无愧。其它的，交给命运!";
		//保存到内存中去.//程序-->内存 
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.write(data.getBytes());
		
		/*
		 * 处理并打印数据
		 */
		
		//byte[] toByteArray() 创建一个新分配的 byte 数组。
		byte[] bys = bos.toByteArray();//真正的数据
		ByteArrayInputStream bis = new  ByteArrayInputStream(bys);
		
		byte[] b= new byte[1024];
		int len = 0;
		while((len = bis.read(b)) != -1){
			String str = new String(b,0,len);
			System.out.println(str);
		}
	}
	
	
	public static void charArray1() throws IOException {
		String data = "人和人之间就是一份情，一份缘，你珍惜我，我会加倍奉还，你不在意，我又何必去珍惜。";
		
		
		//程序  -> 内存
		
		CharArrayWriter cw = new  CharArrayWriter();
		
		cw.write(data);//FileOutputStream
		
		char[] cs = cw.toCharArray();//取出内存输出流的数据
		
		
		System.out.println(cs.length);
		//把内存的数据   --->  程序
		
		
		CharArrayReader cr = new CharArrayReader(cs);
		
		char[] buff = new char[1024];
		
		int len = 0;
		while((len = cr.read(buff)) != -1){
			System.out.println(new String(buff,0,len));
		}
	}
}
