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
			//�ȶ� lisi,�ٶ� zhangsan, ����wangwu
			
			//�ƶ��ļ�ָ��
			
			//raf.seek(9);//����λ��Ч��ƫ��
			raf.skipBytes(9);//���λ��
			
			String name = "";
			for (int i = 0; i < "lisi".length(); i++) {
				char c = (char)raf.read();
				name += c;
			}
			System.out.println(name);
			System.out.println(raf.readInt());//���¶��ĸ��ַ�
			
			//����
			
			raf.seek(0);
			name = "";
			
			for (int i = 0; i < "zhangsan".length(); i++) {
				char c = (char)raf.read();
				name += c;
			}
			System.out.println("name="+name);
			/*
			 * ����ֵ�˷�����������ļ������ĸ��ֽڣ�����Ϊһ��int��
			 * 
			 * �쳣IOException --�������I/ O����
			 * 
			 * EOFException -- �������ļ���ȡ�ĸ��ֽ�֮ǰ����ĩβ��
			 */
			System.out.println(raf.readInt());
			
			
			//����
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
			// ������ر���Դ
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
	 * ���ļ������д������
	 * 
	 * @param filename
	 *            Ŀ���ļ�
	 */
	public static void write(String filename) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(filename, "rw");

			// raf.setLength(1024 * 1024);
			// ���ļ���д������
			// System.out.println(raf.getFilePointer());
			raf.write("zhangsan".getBytes());
			raf.writeInt(15);

			System.out.println(raf.getFilePointer());

			// raf.seek(0);//���ļ�ָ���Ƶ�0λ��
			raf.write("lisi".getBytes());
			raf.writeInt(16);

			raf.write("wangwu".getBytes());
			raf.writeInt(17);
			System.out.println(raf.getFilePointer());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ������ر���Դ
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
