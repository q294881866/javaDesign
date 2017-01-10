package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/*
 * 	1.��Դ��Ŀ��
 * 	2.�����ܵ�����Դ��Ŀ��������
 * 	3.IO����
 * 	4.�ر���Դ
 */

/**
 * �����ַ����ֽ���:�����ǹܵ����Ͳ�һ���Ͳ����ĵ�λ��һ��
 * 
 * @author will
 * 
 */
public class IODemo {

	/**
	 * ʹ���ַ�������ȡ��ȡ����
	 * 
	 * @param srcF
	 */
	public static void read(File srcFile) {
		try (Reader reader = new FileReader(srcFile);) {
			// IO����
			char[] buff = new char[1024];// ����һ����СΪ1024���ַ��Ļ�����

			int len = 0;// ��ʾ��ȡ���ַ�����
			while ((len = reader.read(buff)) != -1) {
				// ������Ȼ�ڻ�������
				String data = new String(buff, 0, len);
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(File destFile) throws IOException {
		Writer w = new FileWriter(destFile);
		w.write("���ϻ���".toCharArray());
		w.write("����������������,������,�ǺǺ�");
		w.write("\n");
		w.write("�Ұ���,������!");
		w.write("\n");

		// w.flush();
		w.close();
	}

	public static void main(String[] args) throws IOException {
		// File srcFile = new File("src/io/CharDemo.java");
		// read(srcFile);
		// write(new File("SpringBrother.java"));

//		copy("src/io/CharDemo.java", "src/io/CopyCharDemo.java");
		/**
		 *  FileReader ��ֱ�����ϲ��ṩ�Ǿ��� Unicode ����õ����ݡ�
        *
		*���ԣ�FileReader ��Ҫ��Ե����� Unicode Ϊ�������ļ������� JSON, XML, TXT �ȵȡ�
		*
		*�� FileReader ��ȡ�� Unicode ���ļ�������������ļ����õ��Ļ������룬����������Ի�ԭԭʼ���ݣ������ⲻ����õ�������
		 */
		 copy("ps.jpg","dios.jpg");
	}

	public static void copy(String src, String dest) {

		try (
		// ����Դ
		Reader r = new FileReader(src);
				Writer w = new FileWriter(dest);) {
			char[] buff = new char[1024];
			int len = 0;
			while ((len = r.read(buff)) != -1) {
				w.write(buff, 0, len);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ʹ��Java7��ʼ���ֵ��Զ��ر���Դ��������
	 * 
	 * @param srcFile
	 * @param targetFile
	 */

	/**
	 * Java7�Զ��ر���Դ�Ĵ����ʽ: try ( //����д����Դ�Ĵ���,���ﴴ������������ʵ��
	 * java.lang.AutoCloseable�ӿ�
	 * 
	 * �������� ���� = java.lang.AutoCloseable��ʵ��; ) {
	 * 
	 * }catch(Exception e){
	 * 
	 * }
	 * 
	 */
	public static void copyByjava7(File srcFile, File targetFile) {

		try (	InputStream in = new FileInputStream(srcFile);
				OutputStream out = new FileOutputStream(targetFile);
		// The resource type Date does not implement java.lang.AutoCloseable
		// Date d = new Date();
		// OOXX ox = new OOXX();����
		)
		// int d = 17;//����д����
		{
			/**
			 * IO����
			 */

			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff)) != -1) {
				out.write(buff, 0, len);
			}
		} catch (IOException e) {
			// �����쳣�Ĵ���
		}

	}
	
	public static void skip() throws IOException {
		Reader r = new FileReader("stream.txt");
		
		long i = r.skip(3);
		System.out.println(i);
		
		char c = (char)r.read();
		System.out.println(c);
	}
}


class OOXX implements AutoCloseable {
	public void close() throws Exception {
	}

}