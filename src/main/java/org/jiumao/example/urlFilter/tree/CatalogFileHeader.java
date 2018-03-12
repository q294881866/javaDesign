package org.jiumao.example.urlFilter.tree;

import java.util.concurrent.atomic.AtomicInteger;

import org.jiumao.example.urlFilter.FileHeader;


public class CatalogFileHeader extends FileHeader {

    private static final long serialVersionUID = 1L;
    // 过滤的网址host数量
    private static final AtomicInteger HostCount = new AtomicInteger(0);
    // 树叶子节点的个数
    private static final AtomicInteger LeafCount = new AtomicInteger(0);


    public CatalogFileHeader() {
        super();
    }


    public static AtomicInteger getLeafcount() {
        return LeafCount;
    }


    public static AtomicInteger getHostcount() {
        return HostCount;
    }

}
