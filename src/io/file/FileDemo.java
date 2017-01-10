package io.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * java io api
 * @author ppf@jiumao.org
 *
 */
public class FileDemo {
	/**文件夹名称*/
	static String dir = "test";
	static String disk = "D:";
	static String  filename = "D:\\v5\\v6\\87";
	public static void main(String[] args) throws Exception {
//		params();
//		apis();
		apis2();
	}

	/**
	 * java file 中的属性
	 */
	public static void params() {
		filename = "D:/v5/87.txt";
		filename = disk+File.separator+dir+File.separator+
				"test.txt";
		System.out.println(filename);
		System.out.println(File.separator.equals(""+File.separatorChar));
		
		System.out.println(File.pathSeparator);// ;
		System.out.println(File.pathSeparatorChar);// ;
	}
	
	/**
	 * file api
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public static void apis() throws IOException, URISyntaxException {
		File f = new File(filename+".txt");
		boolean b = false;
//		boolean b = f.mkdir();//创建目录
//				boolean b  = f.mkdirs();//若父目录不存在,就先创建父目录
//				System.out.println(b);
		//创建一个文件
//		b = f.createNewFile();// 创建出来的是文件而不是文件夹
//		System.out.println(b);
//		System.out.println(f);
//		System.out.println(f.canExecute());
//		System.out.println(f.canWrite());
//
//		//String getAbsolutePath() 返回此抽象路径名的绝对路径名字符串
//		System.out.println(f.getAbsolutePath());
//		//File getAbsoluteFile() 返回此抽象路径名的绝对路径名形式。<br>
//		System.out.println(f.getAbsoluteFile());
//		System.out.println(f.getName());
//		
//		System.out.println(f.isFile());
//		System.out.println(f.isDirectory());
		
//		
//		System.out.println(f.getParent());
//		System.out.println(f.getPath());
//		
//
//		/*
//		 * other operation
//		 */
//		
//		/*
//		 * boolean renameTo(File dest)  重新命名此抽象路径名表示的文件。 
//		 * */
//		
//		f.renameTo(new File(filename+"1"));
//		
//		URI uri= f.toURI();
//		System.out.println(uri);
//		
//		f = new File(uri);
//		System.out.println(f);
//		
//		/**
//		 * boolean delete() 删除此抽象路径名表示的文件或目录。
//		 */
		System.out.println(new File(filename).delete());
		System.out.println(new File(filename).exists());
		
	}

	/**
	 * 创建指定文件
	 * @throws URISyntaxException 
	 */
	private static void apis2() throws IOException, URISyntaxException {
		/*
		 * static File createTempFile(String prefix, String suffix, File
		 * directory) 在指定目录中创建一个新的空文件，使用给定的前缀和后缀字符串生成其名称。
		 */

		File myfile = File.createTempFile("test", ".java", new File(disk+File.separator+dir));
		System.out.println(myfile);
		
		/*
		 * boolean isHidden() 
			          测试此抽象路径名指定的文件是否是一个隐藏文件。 
		   long lastModified() 
			          返回此抽象路径名表示的文件最后一次被修改的时间。 
		   long length()  

		 * */
		System.out.println(myfile.isHidden());
		System.out.println(new Date(myfile.lastModified()));
		System.out.println(myfile.length());
		
		
		
	}
	
	
}
