package org.jiumao.example.urlFilter.data;

import java.util.concurrent.atomic.AtomicLong;

import org.jiumao.example.urlFilter.FileHeader;


public class DataFileHeader extends FileHeader {

    private static final long serialVersionUID = 1L;
    // 当前文件存储的URL个数
    private static final AtomicLong UrlCount = new AtomicLong(0);


    public static AtomicLong getUrlcount() {
        return UrlCount;
    }


    public DataFileHeader() {
        super();
    }

}
