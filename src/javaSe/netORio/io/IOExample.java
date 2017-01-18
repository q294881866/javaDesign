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

import Domain.User;
/**
 * 简单的io流介绍
 * 除了io流的子类外还有一个
 * RandomAccessFile
 * @author Administrator
 *特点：
		 * 1，该对象即能读，又能写。
		 * 2，该对象内部维护了一个byte数组，并通过指针可以操作数组中的元素，
		 * 3，可以通过getFilePointer方法获取指针的位置，和通过seek方法设置指针的位置。
		 * 4，其实该对象就是将字节输入流和输出流进行了封装。 
		 * 5，该对象的源或者目的只能是文件。通过构造函数就可以看出。 
		 * 
 */

public class IOExample {

	
	/**
	 * ByteArrayInputStream
	 * 介绍
	 */
	public void byteArrayIS(){
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("peipengfei".getBytes());
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		int b = 0;
		
		while ((b=byteArrayInputStream.read())!=-1) {
			byteArrayOutputStream.write(b);
		}
	}
		
		/**
		 * dataStream
		 * @throws IOException 
		 */
		public void dataStreamTest() throws IOException{
			DataInputStream dataInputStream = new DataInputStream(new FileInputStream("Test1_Edited.xml"));
			
			String string =dataInputStream.readUTF();
			
			System.out.println(string);
			
			DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("Test1_Edited.xml"));
			dataOutputStream.writeUTF("大家好才是真的好");
			dataOutputStream.close();//一定要关闭各种流
		}
	
	/**
	 * ObjectStream
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
		public void objectStreamTest() throws FileNotFoundException, IOException, ClassNotFoundException{
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.object"));
			//对象序列化。  被序列化的对象必须实现Serializable接口。 
			oos.writeObject(new User("小强"));
			
			oos.close();
			
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.object"));
			//对象的反序列化。 
			User user = (User)ois.readObject();
			
			System.out.println(user.getName());
			
			ois.close();
		}
		
		/**
		 * pipedStream
		 * @throws IOException 
		 */
		public void pipedStreamTest() throws IOException{
			PipedInputStream input = new PipedInputStream();
			PipedOutputStream output = new PipedOutputStream();
			
			input.connect(output);
			
			new Thread(new Input(input)).start();
			new Thread(new Output(output)).start();
		}
	
}


class Input implements Runnable{
	
	private PipedInputStream in;
	Input(PipedInputStream in){
		this.in = in;
	}
	public void run(){
		
		try {
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			
			String s = new String(buf,0,len);
			
			System.out.println("s="+s);
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

class Output implements Runnable{
	private PipedOutputStream out;
	Output(PipedOutputStream out){
		this.out = out;
	}
	public void run(){
		
		try {
			Thread.sleep(5000);
			out.write("hi，管道来了！".getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
