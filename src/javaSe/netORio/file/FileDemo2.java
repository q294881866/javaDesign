package javaSe.netORio.file;

import java.io.File;
import java.util.Arrays;
/**
 * �ļ��ı���
 * @author Administrator
 *
 */
public class FileDemo2 {
	/**�ļ�������*/
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
	 * �����ļ�
	 */
	public static void list(File f) {

		if (f.isDirectory()) {
			// ����·�����ļ������ļ�����
			String[] sArr = f.list();
			for (String s : sArr) {
				System.out.println(s);
			}
		}
		System.out.println("===============");
		/**
		 * File[] listFiles() ����һ������·�������飬��Щ·������ʾ�˳���·������ʾ��Ŀ¼�е��ļ���
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
	 * �г�ָ���ļ�����������ļ��������ļ�������������ļ�
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
