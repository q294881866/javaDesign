package javase.special.classLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader2 extends ClassLoader {

	/** Class文件目录 */
	private String classDir;
	public MyClassLoader2() {}

	public MyClassLoader2(String classDir) {
		this.classDir = classDir;
	}

	
	
	public static void main(String[] args) throws Exception {
		ClassLoader cl = new MyClassLoader2("E:\\work4java\\MyJavaDesign\\bin");
		Class<?> c= cl.loadClass("javase.special.classLoader.MyClassLoader");
		System.out.print(c.getClassLoader()+" ");
		System.out.println(c.newInstance() instanceof MyClassLoader2);
	}
	
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = null;
		try (InputStream in = new FileInputStream(new File(classDir + File.separator
				+ name.replace('.', File.separatorChar))+".class");){
			b = new byte[in.available()];
			in.read(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.defineClass(name, b, 0, b.length,null);
	}
}
