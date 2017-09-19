package org.jiumao.example.urlFilter.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.jiumao.example.urlFilter.MixAll;
import org.jiumao.example.urlFilter.URLSrvConfig;
import org.jiumao.example.utils.annotation.Singleton;

/**
 * 文件目录结构
 * @author ppf@jiumao.org
 * @date 2017年9月19日
 */
@Singleton
public class CatalogFile {
    
    private URLSrvConfig srvConfig = new URLSrvConfig();
    
    private String fileName = srvConfig.getUrlSrvHome()+File.pathSeparator+MixAll.CATALOG_FILE_NAME;
    private final MappedByteBuffer mappedByteBuffer;
    
    private static final CatalogFile CF = new CatalogFile();
    
    
    @SuppressWarnings("resource")
    private CatalogFile() {
        try {
            File file = new File(fileName);
            if (!file.exists()) 
                file.createNewFile();
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void write(// 写文件
            int length,// 消息长度
            int index,//叶子节点索引
            long beginOffset,//文件存储的开始物理偏移量，用于细粒度控制删除
            int bodyLength,//消息体长度
            String body,//完整的URL，用于恢复
            int extLength,//额外信息长度
            String ext//map<k,v>类型劲松字符串
            ) {
        CF.mappedByteBuffer.putInt(length);
        CF.mappedByteBuffer.putInt(index);
        CF.mappedByteBuffer.putLong(beginOffset);
        CF.mappedByteBuffer.putInt(bodyLength);
        CF.mappedByteBuffer.put(body.getBytes());
        CF.mappedByteBuffer.putInt(extLength);
        CF.mappedByteBuffer.put(ext.getBytes());
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

    public static CatalogFile getCf() {
        return CF;
    }
    
    
    

}
