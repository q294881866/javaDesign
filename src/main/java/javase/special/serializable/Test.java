package javase.special.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化示例<br>
 * 
 * @author ppf@jiumao.org
 * @date 2017年1月18日
 */
public class Test implements Serializable {

	private static final long serialVersionUID = 1L;
	public Test() { System.out.println("Serializable init");}
	
	public String name = "Serializable";
	public transient/*不会被序列化*/ String tmp = "transient";
	

	public static <O extends Serializable> void serialize(O o, File file) {
		try(FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(o);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@SuppressWarnings("unchecked")
	public static <O extends Serializable> O deserialize(Class<O> clazz,
			File file) {
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			return (O) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Test t1 = new Test();
		File file = new File("serialize");
		serialize(t1, file);
		Test t2 = deserialize(Test.class, file );
		System.out.println(t1==t2);
		System.out.println(t2.name+" || "+t2.tmp);
	}
}
