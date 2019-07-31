package javase.netio.file;

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
        raf.seek(3);// 文件指针绝对位置
        System.out.println(raf.readInt());
        System.out.println(raf.getFilePointer());
        raf.skipBytes(4);// 文件指针相对位置
        System.out.println(raf.readLong());
        raf.close();
    }

    /**
     * 相文件里随机写入数据
     * 
     * @param filename
     *            目标文件
     * @throws IOException
     */
    public static void write(String filename) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(filename, "rw");
        raf.setLength(1024);// 文件大小预分配
        raf.writeBoolean(true);// true (byte)1，false (byte)0
        raf.writeShort(2);// 2 字节，与数据类型相关，下同
        raf.writeInt(5);
        raf.writeFloat(1F);
        raf.writeLong(2L);
        raf.writeDouble(3.33);
        raf.writeChar('中');
        raf.writeUTF("华");
        raf.write("人民共和国".getBytes());
        System.out.print("文件指针：" + raf.getFilePointer());
        System.out.println(" 文件容量：" + raf.length());

        raf.close();
    }

    @SuppressWarnings("resource")
    public static void nioRA(String filename) throws IOException {
        FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
        MappedByteBuffer out = fc.map(// 文件在内存中映射
                FileChannel.MapMode.READ_WRITE, // 读或写模式
                0, // 文件映射开始位置
                Math.max(fc.size(), 1 << 10));// 结束位置
        for (int i = 0; i < 1 << 10; i++) {
            out.put((byte) i);// 写0-1023
        }
        System.out.println(out.get(2) + out.get(3));// 5
        System.out.println(out.limit());// 1024
        fc.close();
    }
}
