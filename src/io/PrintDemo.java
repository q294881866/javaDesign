package io;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintDemo {
	public static void main(String[] args) throws IOException {
		print();
	}
	
	public static void print() throws IOException {
		PrintStream ps = new PrintStream("out.txt");
		/*
		 * ������������д��
		 */
		ps.write('A');
		ps.print(false);
		ps.print(true);
//		 ps.print();//����
		ps.println();
		ps.println("Will");
		ps.println(113);
		ps.println(false);

		ps = System.out;
		System.out.println("");

		/*
		 * PrintWriter
		 */
		PrintWriter pw = new PrintWriter("out2.txt");
		// PrintWriter(Writer out, boolean autoFlush)

		// �� PrintStream �಻ͬ������������Զ�ˢ�£���ֻ���ڵ��� println��printf �� format
		// ������һ������ʱ�ſ�����ɴ˲���
		//pw = new PrintWriter(new FileWriter("out3.txt"), true);
		
		pw = new PrintWriter(System.out,true);
		pw.println("����������");
		pw.println(false);
		// pw.close();
	}
	
	public static void  printf() {

		/* ռλ��printf
		 * //����= XX,����=xx,����=xx
		 */
		String name = "Will";
		int age = 17;
		char score = 'A';
		//��ʽ  %ռλ��
		//String format = "����= %s,����=%d,����=%c";
		String format = "����= %s,����=%s,����=%s";
		System.out.printf(format, name,age,score);
		
		/*
		 * ��ӡʮ����,�˽���,ʮ������
		 */
		System.out.println();
		int num = 20;
		
		System.out.printf("�˽���=%o, ʮ������=%x",num,num);
		System.out.println();
		System.out.println(Integer.toOctalString(num));
		System.out.println(Integer.toHexString(num));
	}
	
	/**
	 * ��׼��:
	 * 		��׼������:  System.in   Ĭ�ϱ�ʾ���Ǽ���¼��
	 * 		��׼�����:  System.out  Ĭ�ϱ�ʾ������Ļ���
	 * 
	 * 
	 * 		����������������ӡ����Ļ��,��ô��?
	 * 		�������Ҳ���ͨ��������¼������,����ô��?
	 * 
	 * 
	 * 		�����ض���:
	 * 		static void setOut(PrintStream out)     ���·��䡰��׼��������� 
	 * 		static void setIn(InputStream in)  ���·��䡰��׼���������� 
	 * @throws IOException 
	 */
	public static void staticSystem() throws IOException {
		
		System.out.println("----begin-----");
		
		//����Ļ����ض���setOut.txt,�Ժ�Ĵ�ӡ�Ͳ����ӡ����Ļ����,���Ǵ�ӡ��setOut.txt�ļ�����
		System.setOut(new PrintStream("out2.txt"));
		
		System.out.println("AAAA");
		System.out.println("BBBB");
		
		int i;
		//===================================
		while( (i = System.in.read())!=48){//���ܴӼ������������
			System.out.print((char)i);
		}
		
//		/**
//		 * ���·��� �����Դ,ԭ���Ǽ���,���ڸĳ� stream.txt�ļ�
//		 */
//		System.setIn(new FileInputStream("stream.txt"));
//		
//		byte[] buff = new byte[1024];
//		int len = 0;
//		while((len = System.in.read(buff)) != -1){
//			System.out.println(new String(buff,0,len));
//		}
	}
}
