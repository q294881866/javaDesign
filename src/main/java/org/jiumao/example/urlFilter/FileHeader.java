package org.jiumao.example.urlFilter;

import java.io.Serializable;
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
    
    // 当前写到什么位置
    private final AtomicLong wrotePostion = new AtomicLong(0);
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

    public AtomicLong getWrotePostion() {
        return wrotePostion;
    }
    
    
}
