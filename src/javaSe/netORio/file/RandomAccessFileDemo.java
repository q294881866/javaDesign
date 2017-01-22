package javaSe.netORio.file;

import java.io.IOException;
import java.io.RandomAccessFile;



public class RandomAccessFileDemo {
	public static void main(String[] args) {
		// write("data.txt");
		read("data.txt");
	}

	public static void read(String filename) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(filename, "r");
			//先读 lisi,再度 zhangsan, 最后读wangwu
			
			//移动文件指针
			
			//raf.seek(9);//绝对位置效率偏低
			raf.skipBytes(9);//相对位置
			
			String name = "";
			for (int i = 0; i < "lisi".length(); i++) {
				char c = (char)raf.read();
				name += c;
			}
			System.out.println(name);
			System.out.println(raf.readInt());//往下读四个字符
			
			//张三
			
			raf.seek(0);
			name = "";
			
			for (int i = 0; i < "zhangsan".length(); i++) {
				char c = (char)raf.read();
				name += c;
			}
			System.out.println("name="+name);
			/*
			 * 返回值此方法返回这个文件的下四个字节，解释为一个int。
			 * 
			 * 异常IOException --如果发生I/ O错误。
			 * 
			 * EOFException -- 如果这个文件读取四个字节之前到达末尾。
			 */
			System.out.println(raf.readInt());
			
			
			//王五
			raf.skipBytes(3);//lisi  16
			name = "";
			for (int i = 0; i < "wangwu".length(); i++) {
				char c = (char)raf.read();
				name += c;
			}
			System.out.println(name);
			System.out.println(raf.readInt());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 最后必须关闭资源
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 相文件里随机写入数据
	 * 
	 * @param filename
	 *            目标文件
	 */
	public static void write(String filename) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(filename, "rw");

			// raf.setLength(1024 * 1024);
			// 相文件里写入数据
			// System.out.println(raf.getFilePointer());
			raf.write("zhangsan".getBytes());
			raf.writeInt(15);

			System.out.println(raf.getFilePointer());

			// raf.seek(0);//把文件指针移到0位置
			raf.write("lisi".getBytes());
			raf.writeInt(16);

			raf.write("wangwu".getBytes());
			raf.writeInt(17);
			System.out.println(raf.getFilePointer());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 最后必须关闭资源
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
