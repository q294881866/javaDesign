package org.jiumao.example.urlFilter.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * url 存储目录
 * @author ppf@jiumao.org
 * @date 2017年9月15日
 */
public class Catalog {
    
    
    private final static Map<String,Set<String>> CATALOGS = new ConcurrentHashMap<>(128);
    
    public static void main(String[] args) {
        
    }
    
    
    public static void add(String host) {
        Set<String> tree = new ConcurrentSkipListSet<>();
        CATALOGS.put(host, tree);
    }
    
    
    /**
     * 1.判断不重复，写日志，成功就返回
     * 2.缓存，批量刷文件并去重
     * 2.定时回写到存储结构
     * 4.写目录文件
     * 5.写数据文件
     */

}
