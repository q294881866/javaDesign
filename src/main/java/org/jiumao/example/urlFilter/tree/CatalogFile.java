package org.jiumao.example.urlFilter.tree;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.jiumao.example.urlFilter.FileHeader;
import org.jiumao.example.urlFilter.MixAll;
import org.jiumao.example.urlFilter.URLSrvConfig;
import org.jiumao.example.utils.annotation.Singleton;


/**
 * 文件目录结构
 * 
 * @author ppf@jiumao.org
 * @date 2017年9月19日
 */
@Singleton
public class CatalogFile {

    private static volatile CatalogFile CF;

    private URLSrvConfig srvConfig = new URLSrvConfig();
    private String fileName = srvConfig.getUrlSrvHome() + File.pathSeparator + MixAll.CATALOG_FILE_NAME;
    private String header = fileName + ".json";
    private String headerBak = fileName + ".json.bak";
    private File file = new File(fileName);
    private File h = new File(header);
    private File hBak = new File(headerBak);

    private final FileChannel channel;// 文件管道
    private MappedByteBuffer mappedByteBuffer;// 文件内存映射
    private PrintStream hOut;// 记录文件写信息
    private PrintStream hBakOut;// 备份文件写信息

    private long fileFromOffset;// 映射的起始偏移量
    private final int fileSize = 4 * 1024 * 1024; // 映射的文件大小，定长

    // 当前JVM中映射的虚拟内存总大小
    private static final AtomicLong TotalMapedVitualMemory = new AtomicLong(0);
    // 当前JVM中mmap句柄数量
    private static final AtomicInteger TotalMapedFiles = new AtomicInteger(0);
    // cache到什么位置，包含未flush
    private final AtomicLong committedPosition = new AtomicLong(0);


    @SuppressWarnings("resource")
    private CatalogFile() {
        try {
            createFile(file);
            createFile(h);
            createFile(hBak);

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            this.channel = raf.getChannel();
            this.hOut = new PrintStream(h);
            this.hBakOut = new PrintStream(hBak);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 内存映射
     * 
     * @throws IOException
     */
    public void mmap() throws IOException {
        try {
            synchronized (mappedByteBuffer) {
                this.mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, fileFromOffset, fileSize);
            }
            TotalMapedFiles.incrementAndGet();
            TotalMapedVitualMemory.addAndGet(fileSize);
        }
        catch (IOException e) {
            throw e;
        }
    }


    private static void createFile(File file) throws IOException {
        if (!file.exists())
            file.createNewFile();
    }


    /**
     * @see FileHeader json格式 fileHeader
     */
    public synchronized void writeFileHeader(String fileHeader) {
        hOut.print(fileHeader);
        hOut.flush();
        hBakOut.print(fileHeader);
        hBakOut.flush();
    }


    /**
     * 每条数据长度4+length
     * 
     * @throws IOException
     */
    public static synchronized void write(// 写文件
            int length, // 消息长度
            int index, // 叶子节点索引
            long beginOffset, // 文件存储的开始物理偏移量，用于细粒度控制删除
            int bodyLength, // 消息体长度
            String body, // 完整的URL，用于恢复
            int extLength, // 额外信息长度
            String ext// map<k,v>类型JSON字符串
    ) throws IOException {
        if (CF.mappedByteBuffer.remaining() < (4 + length)) {
            CF.fileFromOffset = CF.mappedByteBuffer.position();
            CF.mmap();
        }
        ByteBuffer bb = CF.mappedByteBuffer.slice();
        bb.putInt(length);
        bb.putInt(index);
        bb.putLong(beginOffset);
        bb.putInt(bodyLength);
        bb.put(body.getBytes());
        bb.putInt(extLength);
        bb.put(ext.getBytes());
        CF.committedPosition.addAndGet(4 + length);
    }


    /**
     * @throws IOException
     * @see CatalogFile#write(int, int, long, int, String, int, String)
     */
    public static int writeAndFlush(// 写文件
            int length, // 消息长度
            int index, // 叶子节点索引
            long beginOffset, // 文件存储的开始物理偏移量，用于细粒度控制删除
            int bodyLength, // 消息体长度
            String body, // 完整的URL，用于恢复
            int extLength, // 额外信息长度
            String ext// map<k,v>类型JSON字符串
    ) throws IOException {
        write(length, index, beginOffset, bodyLength, body, extLength, ext);
        return flush();
    }


    public static int flush() {
        CF.mappedByteBuffer.force();
        return CF.mappedByteBuffer.position();
    }


    /**
     * 每次读取一条数据，如果没有 返回{@link MixAll#NULL_BYTE}
     */
    public static byte[] read() {
        byte[] dst = MixAll.NULL_BYTE;
        if (4 < CF.mappedByteBuffer.remaining()) {
            int msgLength = CF.mappedByteBuffer.getInt();
            if (msgLength < CF.mappedByteBuffer.remaining()) {
                dst = new byte[msgLength];
                CF.mappedByteBuffer.get(dst);
            }
        }
        return dst;
    }


    public URLSrvConfig getSrvConfig() {
        return srvConfig;
    }


    public void setSrvConfig(URLSrvConfig srvConfig) {
        this.srvConfig = srvConfig;
    }


    public MappedByteBuffer getMappedByteBuffer() {
        return mappedByteBuffer;
    }


    public static synchronized CatalogFile getInstance() {
        if (null == CF) {
            CF = new CatalogFile();
        }
        return CF;
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (channel.isOpen()) {
            channel.close();
        }
        hOut.close();
        hBakOut.close();
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getHeader() {
        return header;
    }


    public void setHeader(String header) {
        this.header = header;
    }


    public String getHeaderBak() {
        return headerBak;
    }


    public void setHeaderBak(String headerBak) {
        this.headerBak = headerBak;
    }


    public PrintStream gethOut() {
        return hOut;
    }


    public void sethOut(PrintStream hOut) {
        this.hOut = hOut;
    }


    public PrintStream gethBakOut() {
        return hBakOut;
    }


    public void sethBakOut(PrintStream hBakOut) {
        this.hBakOut = hBakOut;
    }


    public long getFileFromOffset() {
        return fileFromOffset;
    }


    public void setFileFromOffset(long fileFromOffset) {
        this.fileFromOffset = fileFromOffset;
    }


    public FileChannel getChannel() {
        return channel;
    }


    public int getFileSize() {
        return fileSize;
    }


    public static AtomicLong getTotalmapedvitualmemory() {
        return TotalMapedVitualMemory;
    }


    public static AtomicInteger getTotalmapedfiles() {
        return TotalMapedFiles;
    }


    public AtomicLong getCommittedPosition() {
        return committedPosition;
    }


    public void setMappedByteBuffer(MappedByteBuffer mappedByteBuffer) {
        this.mappedByteBuffer = mappedByteBuffer;
    }


    public File getFile() {
        return file;
    }


    public void setFile(File file) {
        this.file = file;
    }


    public File getH() {
        return h;
    }


    public void setH(File h) {
        this.h = h;
    }


    public File gethBak() {
        return hBak;
    }


    public void sethBak(File hBak) {
        this.hBak = hBak;
    }

}
