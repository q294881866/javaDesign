package javase.netio.io;

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
			 * 1.�Ȼ���������
			 * 2.�õ�����������֮��,���� getResourceAsStream()
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
         *			���������ж�ȡ�����б�����Ԫ�ضԣ��� 
 		*	void load(Reader reader) 
         *			���򵥵������еĸ�ʽ�������ַ����ж�ȡ�����б�����Ԫ�ضԣ��� 
		 */
		
		
		System.out.println(p);
		//�������б������ָ�����������
		p.list(System.out);//�г�������̨
		p.list(new PrintStream("87.java"));
		
//		���ʺ�ʹ�� load(Reader/InputStream) �����ĸ�ʽ������ Properties ���е������б�����Ԫ�ضԣ�д������ַ�/������� 
		p.store(new FileOutputStream("���.properties"), "��֪����ɶ");
		//����һ����ʾ�˱��а������������Ե� XML �ĵ�
		p.storeToXML(new FileOutputStream("xml.xml"), "ע������");
//		p.loadFromXML(new FileInputStream("xml.xml"));
		System.out.println("--"+p);
	}
}
