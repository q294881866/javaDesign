package javaSe.netORio.file;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.Date;

import org.junit.Test;

/**
 * �ļ����ļ��еĴ�������д
 * 
 * @author ppf
 * @since 2017��3��2��
 */
public class FileDemo {
	/** �ļ������� */
	final static String dir = "test";
	final static String disk = "D:";
	final static String filename = "test.txt";
	final static String path = disk + File.separator + dir;
	final static String file = path + File.separator + filename;

	/** ע�⣺char������String���� */
	@Test
	public void params() {
		String useString = File.separator + File.pathSeparator;
		String useChar = "" + File.separatorChar + File.pathSeparatorChar;
		System.out.println(useString.equals(useChar));
	}

	@Test
	public void createFileWithTmpFile() throws Exception {
		File f1 = new File(path + File.separator + dir + File.separator + filename);
		File f2 = new File(File.separator + filename);
		// ����һ���ļ�
		boolean b1 = f1.createNewFile();// �������������ļ��������ļ���
		boolean b2 = f2.createNewFile();// �������������ļ��������ļ���
		System.out.println(b1 + " || " + b2);
		// String �����ַ���
		System.out.println(f1.getAbsolutePath() + " || " + f2.getAbsolutePath() );
		//���·���ǿգ����ظ���ϵͳ����user.dir��ǰ�û�·��
		System.out.println(new File("").getAbsolutePath().equals(System.getProperty("user.dir")));
		// File �����ļ�
		System.out.println(f1.getAbsoluteFile() + " || " + f2.getAbsoluteFile());
		// ���·��
		System.out.println(f1.getPath() + " || " + f2.getPath());
		
		File tmp = File.createTempFile("test", null, null);
		System.out.println(tmp.getAbsolutePath());
		tmp.deleteOnExit();

	}
	
	@Test
	public void charFile() throws Exception {
		File f = new File(file);
		Writer fw = new BufferedWriter(new FileWriter(f));
		fw.write("�ֽ����ļ���ȡ");
		fw.close();

		Reader fr = new BufferedReader(new FileReader(f));
		int i=-1;
		while ((i = fr.read())!=-1) {
			System.out.print((char)i);
		}
		fr.close();
	}

}
