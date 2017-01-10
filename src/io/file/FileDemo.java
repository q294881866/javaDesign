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
	/**�ļ�������*/
	static String dir = "test";
	static String disk = "D:";
	static String  filename = "D:\\v5\\v6\\87";
	public static void main(String[] args) throws Exception {
//		params();
//		apis();
		apis2();
	}

	/**
	 * java file �е�����
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
//		boolean b = f.mkdir();//����Ŀ¼
//				boolean b  = f.mkdirs();//����Ŀ¼������,���ȴ�����Ŀ¼
//				System.out.println(b);
		//����һ���ļ�
//		b = f.createNewFile();// �������������ļ��������ļ���
//		System.out.println(b);
//		System.out.println(f);
//		System.out.println(f.canExecute());
//		System.out.println(f.canWrite());
//
//		//String getAbsolutePath() ���ش˳���·�����ľ���·�����ַ���
//		System.out.println(f.getAbsolutePath());
//		//File getAbsoluteFile() ���ش˳���·�����ľ���·������ʽ��<br>
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
//		 * boolean renameTo(File dest)  ���������˳���·������ʾ���ļ��� 
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
//		 * boolean delete() ɾ���˳���·������ʾ���ļ���Ŀ¼��
//		 */
		System.out.println(new File(filename).delete());
		System.out.println(new File(filename).exists());
		
	}

	/**
	 * ����ָ���ļ�
	 * @throws URISyntaxException 
	 */
	private static void apis2() throws IOException, URISyntaxException {
		/*
		 * static File createTempFile(String prefix, String suffix, File
		 * directory) ��ָ��Ŀ¼�д���һ���µĿ��ļ���ʹ�ø�����ǰ׺�ͺ�׺�ַ������������ơ�
		 */

		File myfile = File.createTempFile("test", ".java", new File(disk+File.separator+dir));
		System.out.println(myfile);
		
		/*
		 * boolean isHidden() 
			          ���Դ˳���·����ָ�����ļ��Ƿ���һ�������ļ��� 
		   long lastModified() 
			          ���ش˳���·������ʾ���ļ����һ�α��޸ĵ�ʱ�䡣 
		   long length()  

		 * */
		System.out.println(myfile.isHidden());
		System.out.println(new Date(myfile.lastModified()));
		System.out.println(myfile.length());
		
		
		
	}
	
	
}
