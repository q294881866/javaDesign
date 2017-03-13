package javaSe.netORio.file;

import java.io.File;
import java.io.FilenameFilter;

class JavaFilter implements FilenameFilter {
	private String ext;
	public JavaFilter(String ext) { this.ext = ext; }
	public boolean accept(File dir, String name) {
		// ext 结尾文件 或 文件夹则继续遍历
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
			System.out.println("文件："+file.getName());
		}else{
			System.out.println("文件夹："+file.getName());
			for (File f : file.listFiles(new JavaFilter(".java"))) {
				getFiles(f);
			}
		}
	}
}
