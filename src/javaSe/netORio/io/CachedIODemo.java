package javaSe.netORio.io;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.junit.Test;

/**
 * 缓冲流
 */
public class CachedIODemo {
	@Test
	public void byteArrayStreamTest() throws Exception {
		String data = "努力了，珍惜了，问心无愧。其它的，交给命运!";
		// 保存到内存中去.//程序-->内存
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.write(data.getBytes());
		bos.close();// 关闭无效
		byte[] bys = bos.toByteArray();// 真正的数据
		System.out.println(new String(bys));

		ByteArrayInputStream bis = new ByteArrayInputStream(bys);
	}

	@Test
	public void charArrayChar() throws IOException {
		String data = "人和人之间就是一份情，一份缘，你珍惜我，"//
				+ "我会加倍奉还，你不在意，我又何必去珍惜。";
		// 程序 -> 内存
		CharArrayWriter cw = new CharArrayWriter();
		cw.write(data);// FileOutputStream
		char[] cs = cw.toCharArray();// 取出内存输出流的数据
		System.out.println(cs.length);
		// 把内存的数据 ---> 程序

		CharArrayReader cr = new CharArrayReader(cs);
		char[] buff = new char[1024];
		int len = 0;
		while ((len = cr.read(buff)) != -1) {
			System.out.println(new String(buff, 0, len));
		}
	}

	@Test
	public void bufferedCharTest() throws Exception {
		BufferedReader br = new BufferedReader(//
				new FileReader("data.txt"));
		BufferedWriter bw = new BufferedWriter(//
				new FileWriter("copy.txt"));
		String line = null;
		while ((line = br.readLine()) != null) {//
			bw.write(line);// 写一行
			bw.newLine();// 写完一行就换行
		}
		bw.close();
		br.close();
	}

	@Test
	public void bufferedStreamTest() throws Exception {
		BufferedInputStream bis = new BufferedInputStream(//缓冲
				new FileInputStream("data.txt"));
		ByteArrayOutputStream bos = new //字节数组
				ByteArrayOutputStream(bis.available());
		int i = -1;
		while ((i = bis.read()) != -1) {
			bos.write(i);
		}
		bis.close();//内部调用 input.close() 即FileInputStream close
		byte[] bs = bos.toByteArray();
		
		BufferedOutputStream buffOut = new BufferedOutputStream(
				new FileOutputStream("copy.txt"));
		buffOut.write(bs);
		buffOut.close();//先flush 再调用底层out.close
	}

	@Test
	public void stream2Char() throws Exception {

		InputStreamReader isr = new InputStreamReader(new FileInputStream("data.txt"));
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while (null != (line = br.readLine())) {
			System.out.println(line);
		}

		br.close();
		isr.close();
	}

	@Test
	public void char2Stream() throws Exception {
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("data.txt"), // out
				Charset.defaultCharset().name());// charset
		osw.write("Hi there!");
		osw.close();
	}

	// Java的标准输入输出，分别通过System.in和System.out表示，默认情况下，他们分别表示键盘和屏幕。也就是说键盘输入，屏幕显示输出。
	// System类里面有三个重定向标准输入/输出的方法：
	// static void setErr(PrintStream err)：重定向“标准”错误输出流”
	// static void setIn(InputStream in)：重定向“标准"输入流”
	// static void setOut(PrintStream out)：重定向“标准”输出流”
	@SuppressWarnings("resource")
	@Test
	public void systemStreamTest() throws Exception {
		// 1.例子：拷贝文件
		System.setIn(new FileInputStream("data.txt"));
		System.setOut(new PrintStream(new File("copy.txt")));
	}

	// 2.例子：从标准输入流打印
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = null;
		while (sc.hasNext()) {
			line = sc.next();
			System.out.print(line);
			if ("exit".equals(line)) {
				System.out.println("end ...");
				break;
			}
		}
		sc.close();
		System.exit(0);
	}
}
