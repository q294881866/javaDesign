package org.jiumao.example.urlFilter.tree;

import java.util.concurrent.atomic.AtomicInteger;

public class LeafFactory {
    /**
     * 自增的叶子节点分配，匹配是先匹配叶子节点是否相等，在匹配hash
     */
    private static final AtomicInteger LEAF_COUNT = new AtomicInteger(0);
    
    
}
