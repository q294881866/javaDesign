package javaSe.special.classLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

	/** Class文件目录 */
	private String classDir;
	public MyClassLoader() {}

	public MyClassLoader(String classDir) {
		this.classDir = classDir;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		byte[] b = null;
		try (InputStream in = new FileInputStream(new File(classDir + File.separator
				+ name.replace('.', File.separatorChar))+".class");){
			b = new byte[in.available()];
			in.read(b);
		} catch (IOException e) {//二次调用
			return super.loadClass(name/*java.lang.ClassLoader*/);
		}
		return super.defineClass(name, b, 0, b.length,null);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		ClassLoader cl1 = new MyClassLoader("E:\\work4java\\MyJavaDesign\\bin");
		ClassLoader cl2 = new MyClassLoader2("E:\\work4java\\MyJavaDesign\\bin");
		Class<?> c1= cl1.loadClass("javaSe.special.classLoader.MyClassLoader");
		Class<?> c2= cl2.loadClass("javaSe.special.classLoader.MyClassLoader");
		System.out.println(c1 == c2);
	}
	
	
//	@Override
//	public Class<?> findClass(String name) throws ClassNotFoundException {
//		byte[] b = null;
//		try (InputStream in = new FileInputStream(new File(classDir + File.separator
//				+ name.replace('.', File.separatorChar))+".class");){
//			b = new byte[in.available()];
//			in.read(b);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return super.defineClass(name, b, 0, b.length,null);
//	}
}
