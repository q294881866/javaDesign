package javase.netio.file;

import java.io.File;
import java.io.FilenameFilter;

class JavaFilter implements FilenameFilter {
	private String ext;
	public JavaFilter(String ext) { this.ext = ext; }
	public boolean accept(File dir, String name) {
		// ext ��β�ļ� �� �ļ������������
		return name.endsWith(ext) || dir.isDirectory();
	}
}

public class TraversalDirs {
	static String path = "E://work//w1//word-count-beam//src";
	public static void main(String[] args) {
		File f = new File(path);
		getFiles(f);
	}
	private static void getFiles(File file) {
		if (file.isFile()) {
			System.out.println("�ļ���"+file.getName());
		}else{
			System.out.println("�ļ��У�"+file.getName());
			for (File f : file.listFiles(new JavaFilter(".java"))) {
				getFiles(f);
			}
		}
	}
}
