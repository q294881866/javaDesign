package io.file;

import java.io.File;
import java.io.FilenameFilter;

class MyFileNameFilter implements FilenameFilter{

	private String ext;
	public MyFileNameFilter(String ext){
		this.ext = ext;
	}
	public boolean accept(File dir, String name) {
		return name.endsWith(ext);
	}
	
}
public class FilenameFilterDemo {
	/**ÎÄ¼þ¼ÐÃû³Æ*/
	static String dir = "test";
	static String disk = "D:";
	
	public static void main(String[] args) {
		
		File f  = new File(disk+File.separator+dir);
		
		String[] fs = f.list(new MyFileNameFilter(".java"));
		for (String string : fs) {
			System.out.println(string);
		}
	}
}
