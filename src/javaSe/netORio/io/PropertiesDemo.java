package javaSe.netORio.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public class PropertiesDemo {
	
	private static  Properties p = null;
	private static String filename = "xx.properties"; 
	
	static{
		p = new Properties();
		
		InputStream inStream = null;
		try{
			/**
			 * 1.先获得类加载器
			 * 2.得到加载器对象之后,调用 getResourceAsStream()
			 */
			inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
			p.load(inStream);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		/*
		 *  void load(InputStream inStream) 
         *			从输入流中读取属性列表（键和元素对）。 
 		*	void load(Reader reader) 
         *			按简单的面向行的格式从输入字符流中读取属性列表（键和元素对）。 
		 */
		
		
		System.out.println(p);
		//将属性列表输出到指定的输出流。
		p.list(System.out);//列出到控制台
		p.list(new PrintStream("87.java"));
		
//		以适合使用 load(Reader/InputStream) 方法的格式，将此 Properties 表中的属性列表（键和元素对）写入输出字符/输出流。 
		p.store(new FileOutputStream("输出.properties"), "不知道是啥");
		//发出一个表示此表中包含的所有属性的 XML 文档
		p.storeToXML(new FileOutputStream("xml.xml"), "注释内容");
//		p.loadFromXML(new FileInputStream("xml.xml"));
		System.out.println("--"+p);
	}
}
