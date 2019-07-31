package javase.netio.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;


/**
 * IO��
 */
public class IOExample {

	@Test
	public void dataStreamTest() throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("data.txt"));
		dataOutputStream.writeUTF("��Һò�����ĺ�");
		dataOutputStream.close();// һ��Ҫ�رո�����

		DataInputStream dataInputStream = new DataInputStream(new FileInputStream("data.txt"));
		String string = dataInputStream.readUTF();
		dataInputStream.close();
		System.out.println(string);
	}

	// �򵥶���һ����̬�ڲ��ࣨ�����޷����л��� �����л��Ķ������ʵ��Serializable�ӿ�
	static class User implements Serializable {
		String name = "objectStream";
	}

	@Test
	public void objectStreamTest() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.Obj"));
		oos.writeObject(new User());
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.Obj"));
		User user = (User) ois.readObject();
		System.out.println(user.name);
		ois.close();
	}

	@Test
	public void printStreamTest() throws Exception {
		PrintStream out = new PrintStream(// out, autoFlush
				new FileOutputStream("data.txt"), true);
		// ln ��ʾд�뻻��
		out.println("Test for PrintStream");
		// append -> print :-)
		out.append(':').append('-').print(')');
		// print -> write
		out.write(0);
		// java.util.Formatter.format(//0.0
		// Locale l, String format, Object... args)
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

	@Test
	public void exchangerTest() throws Exception {
		ExecutorService service = Executors.newCachedThreadPool();
		Exchanger<Integer> exchanger = new Exchanger<Integer>();

		Runnable producer = new AbstractExchanger(exchanger) {
			@Override
			public void run() {
				for (int i = 1; i < 3; ++i) {
					try {
						System.out.println("�������ݲ���ȡ���Ѻ�����: " + exchanger.exchange(i));
					} catch (InterruptedException e) {
					}
				}
				isDone = true;
			}
		};
		Runnable consumer = new AbstractExchanger(exchanger) {
			@Override
			public void run() {
				try {
					while (!Thread.interrupted()) {
						if (isDone) break;
						System.out.println("��ȡ�������ݲ����ã�" + exchanger.exchange(0));
					}
				} catch (InterruptedException e) {
				}
			}
		};

		service.execute(producer);
		service.execute(consumer);
		service.shutdown();
	}

}

class Pong implements Runnable {
	private PipedInputStream in;

	Pong(PipedInputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			System.out.println("����Ƽ ��ס��" + new String(buf, 0, len));
			in.close();
		} catch (Exception e) {
		}
	}
}

class Ping implements Runnable {
	private PipedOutputStream out;

	Ping(PipedOutputStream out) {
		this.out = out;
	}

	public void run() {
		try {
			out.write("��� һ�ǿ�ɱ".getBytes());
			out.close();
		} catch (Exception e) {
		}
	}
}




abstract class AbstractExchanger implements Runnable {
protected Exchanger<Integer> exchanger;
protected int data;
protected static volatile boolean isDone = false;

AbstractExchanger(Exchanger<Integer> exchanger) {
	this.exchanger = exchanger;
}
}
