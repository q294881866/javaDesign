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
 * �򵥵�io������
 * ����io���������⻹��һ��
 * RandomAccessFile
 * @author Administrator
 *�ص㣺
		 * 1���ö����ܶ�������д��
		 * 2���ö����ڲ�ά����һ��byte���飬��ͨ��ָ����Բ��������е�Ԫ�أ�
		 * 3������ͨ��getFilePointer������ȡָ���λ�ã���ͨ��seek��������ָ���λ�á�
		 * 4����ʵ�ö�����ǽ��ֽ�������������������˷�װ�� 
		 * 5���ö����Դ����Ŀ��ֻ�����ļ���ͨ�����캯���Ϳ��Կ����� 
		 * 
 */

public class IOExample {

	
	/**
	 * ByteArrayInputStream
	 * ����
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
			dataOutputStream.writeUTF("��Һò�����ĺ�");
			dataOutputStream.close();//һ��Ҫ�رո�����
		}
	
	/**
	 * ObjectStream
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
		public void objectStreamTest() throws FileNotFoundException, IOException, ClassNotFoundException{
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.object"));
			//�������л���  �����л��Ķ������ʵ��Serializable�ӿڡ� 
			oos.writeObject(new User("Сǿ"));
			
			oos.close();
			
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.object"));
			//����ķ����л��� 
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
			out.write("hi���ܵ����ˣ�".getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
