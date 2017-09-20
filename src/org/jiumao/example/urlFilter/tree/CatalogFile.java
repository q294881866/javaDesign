package org.jiumao.example.urlFilter.tree;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

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

    private URLSrvConfig srvConfig = new URLSrvConfig();

    private String fileName = srvConfig.getUrlSrvHome() + File.pathSeparator + MixAll.CATALOG_FILE_NAME;
    private final MappedByteBuffer mappedByteBuffer;

    private static final CatalogFile CF = new CatalogFile();
    private String header = fileName + ".json";
    private String headerBak = fileName + ".json.bak";


    @SuppressWarnings("resource")
    private CatalogFile() {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeFileHeader(){
    }


    public static void write(// 写文件
            int length, // 消息长度
            int index, // 叶子节点索引
            long beginOffset, // 文件存储的开始物理偏移量，用于细粒度控制删除
            int bodyLength, // 消息体长度
            String body, // 完整的URL，用于恢复
            int extLength, // 额外信息长度
            String ext// map<k,v>类型JSON字符串
    ) {
        ByteBuffer bb = CF.mappedByteBuffer.slice();
        bb.putInt(length);
        bb.putInt(index);
        bb.putLong(beginOffset);
        bb.putInt(bodyLength);
        bb.put(body.getBytes());
        bb.putInt(extLength);
        bb.put(ext.getBytes());
    }


    public static void writeAndFlush(// 写文件
            int length, // 消息长度
            int index, // 叶子节点索引
            long beginOffset, // 文件存储的开始物理偏移量，用于细粒度控制删除
            int bodyLength, // 消息体长度
            String body, // 完整的URL，用于恢复
            int extLength, // 额外信息长度
            String ext// map<k,v>类型JSON字符串
    ) {
        write(length, index, beginOffset, bodyLength, body, extLength, ext);
        flush();
    }


    public static void flush() {
        CF.mappedByteBuffer.force();
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


    public static CatalogFile getInstance() {
        return CF;
    }

}
