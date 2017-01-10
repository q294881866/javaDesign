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
		 * 多种数据类型写入
		 */
		ps.write('A');
		ps.print(false);
		ps.print(true);
//		 ps.print();//错误
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

		// 与 PrintStream 类不同，如果启用了自动刷新，则只有在调用 println、printf 或 format
		// 的其中一个方法时才可能完成此操作
		//pw = new PrintWriter(new FileWriter("out3.txt"), true);
		
		pw = new PrintWriter(System.out,true);
		pw.println("呼哈哈哈哈");
		pw.println(false);
		// pw.close();
	}
	
	public static void  printf() {

		/* 占位符printf
		 * //名字= XX,年龄=xx,分数=xx
		 */
		String name = "Will";
		int age = 17;
		char score = 'A';
		//格式  %占位符
		//String format = "名字= %s,年龄=%d,分数=%c";
		String format = "名字= %s,年龄=%s,分数=%s";
		System.out.printf(format, name,age,score);
		
		/*
		 * 打印十进制,八进制,十六进制
		 */
		System.out.println();
		int num = 20;
		
		System.out.printf("八进制=%o, 十六进制=%x",num,num);
		System.out.println();
		System.out.println(Integer.toOctalString(num));
		System.out.println(Integer.toHexString(num));
	}
	
	/**
	 * 标准流:
	 * 		标准输入流:  System.in   默认表示的是键盘录入
	 * 		标准输出流:  System.out  默认表示的是屏幕输出
	 * 
	 * 
	 * 		若现在我输出不想打印在屏幕上,怎么办?
	 * 		若现在我不想通过键盘来录入数据,有怎么办?
	 * 
	 * 
	 * 		流的重定向:
	 * 		static void setOut(PrintStream out)     重新分配“标准”输出流。 
	 * 		static void setIn(InputStream in)  重新分配“标准”输入流。 
	 * @throws IOException 
	 */
	public static void staticSystem() throws IOException {
		
		System.out.println("----begin-----");
		
		//把屏幕输出重定向到setOut.txt,以后的打印就不会打印在屏幕上了,而是打印在setOut.txt文件里面
		System.setOut(new PrintStream("out2.txt"));
		
		System.out.println("AAAA");
		System.out.println("BBBB");
		
		int i;
		//===================================
		while( (i = System.in.read())!=48){//接受从键盘输入的数据
			System.out.print((char)i);
		}
		
//		/**
//		 * 重新分配 输入的源,原本是键盘,现在改成 stream.txt文件
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
