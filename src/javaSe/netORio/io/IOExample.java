package javaSe.netORio.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

import org.junit.Test;

/**
 * IO流
 */
public class IOExample {


	/**
	 * dataStream
	 * 
	 * @throws IOException
	 */
	@Test
	public void dataStreamTest() throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("data.txt"));
		dataOutputStream.writeUTF("大家好才是真的好");
		dataOutputStream.close();// 一定要关闭各种流

		DataInputStream dataInputStream = new DataInputStream(new FileInputStream("data.txt"));
		String string = dataInputStream.readUTF();
		dataInputStream.close();
		System.out.println(string);
	}

	// 简单定义一个静态内部类（否则无法序列化） 被序列化的对象必须实现Serializable接口
	static class User implements Serializable { String name = "objectStream"; }
	@Test
	public  void objectStreamTest() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("user.Obj"));
		oos.writeObject(new User());
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("user.Obj"));
		User user = (User) ois.readObject();
		System.out.println(user.name);
		ois.close();
	}

	
	@Test
	public void printStreamTest() throws Exception {
		PrintStream out = new PrintStream(// out, autoFlush 
				new FileOutputStream("data.txt"), true);
		//ln 表示写入换行
		out.println("Test for PrintStream");
		// append -> print :-)
		out.append(':').append('-').print(')');
		// print -> write
		out.write(0);
		// java.util.Formatter.format(//0.0
		//		Locale l, String format, Object... args)
		out.printf("%s.%d\n", "0", 0);
		out.close();
	}
	
	@Test
	public void pipedStreamTest() throws Exception {
		PipedInputStream input = new PipedInputStream();
		PipedOutputStream output = new PipedOutputStream();
		input.connect(output);
		new Thread(new Ping(output)).start();
		new Thread(new Pong(input)).start();
	}
	
	public static void main(String[] args) throws Exception {
		IOExample io = new IOExample();
		io.printStreamTest();
	}

}


class Pong implements Runnable {
	private PipedInputStream in;
	Pong(PipedInputStream in) {
		this.in = in;
	}
	public void run(){
		try {
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			System.out.println("邓亚萍 接住："//
					+ new String(buf, 0, len));
			in.close();
		} catch (Exception e) {}
	}
}


class Ping implements Runnable {
	private PipedOutputStream out;
	Ping(PipedOutputStream out) {
		this.out = out;
	}
	public void run() {
		try {
			out.write("王楠 一记扣杀".getBytes());
			out.close();
		} catch (Exception e) {}
	}
}
