package javaSe.netORio.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws Exception {
		// write("data.txt");
		// read("data.txt");
		nioRA("data.txt");
	}

	public static void read(String filename) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(filename, "r");
		System.out.println(raf.readBoolean());
		raf.seek(3);// �ļ�ָ�����λ��
		System.out.println(raf.readInt());
		System.out.println(raf.getFilePointer());
		raf.skipBytes(4);// �ļ�ָ�����λ��
		System.out.println(raf.readLong());
		raf.close();
	}

	/**
	 * ���ļ������д������
	 * 
	 * @param filename
	 *            Ŀ���ļ�
	 * @throws IOException
	 */
	public static void write(String filename) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(filename, "rw");
		raf.setLength(1024);// �ļ���СԤ����
		raf.writeBoolean(true);// true (byte)1��false (byte)0
		raf.writeShort(2);// 2 �ֽڣ�������������أ���ͬ
		raf.writeInt(5);
		raf.writeFloat(1F);
		raf.writeLong(2L);
		raf.writeDouble(3.33);
		raf.writeChar('��');
		raf.writeUTF("��");
		raf.write("���񹲺͹�".getBytes());
		System.out.print("�ļ�ָ�룺" + raf.getFilePointer());
		System.out.println(" �ļ�������" + raf.length());

		raf.close();
	}

	@SuppressWarnings("resource")
	public static void nioRA(String filename) throws IOException {
		FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
		MappedByteBuffer out = fc.map(// �ļ����ڴ���ӳ��
				FileChannel.MapMode.READ_WRITE, // ����дģʽ
				0, // �ļ�ӳ�俪ʼλ��
				Math.max(fc.size(), 1 << 10));// ����λ��
		for (int i = 0; i < 1 << 10; i++) {
			out.put((byte) i);// д0-1023
		}
		System.out.println(out.get(2) + out.get(3));// 5
		System.out.println(out.limit());// 1024
		fc.close();
	}
}
