package javaSe.netORio.file;

import java.io.File;
import java.util.Arrays;
/**
 * 文件的遍历
 * @author Administrator
 *
 */
public class FileDemo2 {
	/**文件夹名称*/
	static String dir = "test";
	static String disk = "D:";
	static String  filename = disk+File.separator+dir;
//			+File.separator+			"test.txt";
	public static void main(String[] args) {

		File f = new File(filename);
//		list(f);
		listMyFiles(f);
	}

	/**
	 * 遍历文件
	 */
	public static void list(File f) {

		if (f.isDirectory()) {
			// 不带路径的文件名或文件夹名
			String[] sArr = f.list();
			for (String s : sArr) {
				System.out.println(s);
			}
		}
		System.out.println("===============");
		/**
		 * File[] listFiles() 返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件。
		 */

		File[] fs = f.listFiles();

		for (File file : fs) {
			System.out.println("--->" + file);
		}
		System.out.println("===============");
		fs = File.listRoots();
		//fs != null 
		System.out.println(Arrays.toString(fs));
		for (File file : fs) {
			System.out.println(file);
		}
	}


	/**
	 * 列出指定文件夹下面的子文件包括子文件夹下面的所有文件
	 */
	public static void listMyFiles(File f) {
		System.out.println(f);
		if (f.isDirectory()) {
			File[] fs = f.listFiles();
			for (File file : fs) {
				listMyFiles(file);
			}
		}
	}
}
