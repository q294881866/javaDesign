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
 * 	1.找源或目标
 * 	2.创建管道并和源或目标连接上
 * 	3.IO操作
 * 	4.关闭资源
 */

/**
 * 操作字符和字节流:仅仅是管道类型不一样和操作的单位不一样
 * 
 * @author will
 * 
 */
public class IODemo {

	/**
	 * 使用字符输入流取读取数据
	 * 
	 * @param srcF
	 */
	public static void read(File srcFile) {
		try (Reader reader = new FileReader(srcFile);) {
			// IO操作
			char[] buff = new char[1024];// 创建一个大小为1024个字符的缓冲区

			int len = 0;// 表示读取的字符长度
			while ((len = reader.read(buff)) != -1) {
				// 数据依然在缓冲区里
				String data = new String(buff, 0, len);
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(File destFile) throws IOException {
		Writer w = new FileWriter(destFile);
		w.write("爱老虎油".toCharArray());
		w.write("哈哈哈哈哈哈哈哈,嘻嘻嘻,呵呵呵");
		w.write("\n");
		w.write("我爱你,裴鹏飞!");
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
		 *  FileReader 是直接向上层提供是经过 Unicode 编译好的内容。
        *
		*所以，FileReader 主要针对的是以 Unicode 为基础的文件，比如 JSON, XML, TXT 等等。
		*
		*用 FileReader 读取非 Unicode 的文件，比如二进制文件，得到的会是乱码，经过处理可以还原原始数据，但是这不是最好的做法。
		 */
		 copy("ps.jpg","dios.jpg");
	}

	public static void copy(String src, String dest) {

		try (
		// 打开资源
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
	 * 使用Java7开始出现的自动关闭资源的新特性
	 * 
	 * @param srcFile
	 * @param targetFile
	 */

	/**
	 * Java7自动关闭资源的代码格式: try ( //这里写打开资源的代码,这里创建对象的类必须实现
	 * java.lang.AutoCloseable接口
	 * 
	 * 声明变量 变量 = java.lang.AutoCloseable的实例; ) {
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
		// OOXX ox = new OOXX();可以
		)
		// int d = 17;//不能写代码
		{
			/**
			 * IO操作
			 */

			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff)) != -1) {
				out.write(buff, 0, len);
			}
		} catch (IOException e) {
			// 处理异常的代码
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