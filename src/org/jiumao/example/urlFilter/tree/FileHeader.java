package org.jiumao.example.urlFilter.tree;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.jiumao.example.utils.annotation.Sharable;
/**
 * 记录文件读写信息
 * @author ppf@jiumao.org
 * @date 2017年9月20日
 */
@Sharable
public class FileHeader implements Serializable{

    private static final long serialVersionUID = 1L;
    // ---------------------------目录文件独有----------------------------
    // 过滤的网址host数量
    private static final AtomicInteger HostCount = new AtomicInteger(0);
    // 树叶子节点的个数
    private static final AtomicInteger LeafCount = new AtomicInteger(0);
    
    // ---------------------------数据文件独有----------------------------
    // 当前文件存储的URL个数
    private static final AtomicLong UrlCount = new AtomicLong(0);
    
    // 当前写到什么位置，用于删除
    private final AtomicLong wrotePostion = new AtomicLong(0);
    // Flush到什么位置
    private final AtomicLong committedPosition = new AtomicLong(0);
    // 最后一条消息存储时间
    private volatile long storeTimestamp = 0;
    
    /**
     * 设置为当前系统时间
     */
    public FileHeader() {
        storeTimestamp = System.currentTimeMillis();
    }
    
    
    public long getStoreTimestamp() {
        return storeTimestamp;
    }
    public void setStoreTimestamp(long storeTimestamp) {
        this.storeTimestamp = storeTimestamp;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public static AtomicInteger getHostcount() {
        return HostCount;
    }
    public static AtomicInteger getLeafcount() {
        return LeafCount;
    }
    public AtomicLong getWrotePostion() {
        return wrotePostion;
    }
    public AtomicLong getCommittedPosition() {
        return committedPosition;
    }


    public static AtomicLong getUrlcount() {
        return UrlCount;
    }
    
}
