package org.jiumao.example.urlFilter.tree;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.jiumao.example.urlFilter.MixAll;

public class TreeService {
    private final static Map<String, Set<String>> CATALOGS = new ConcurrentHashMap<>(128);


    public static void main(String[] args) {

    }


    public static void add(String host) {
        Set<String> tree = new ConcurrentSkipListSet<>();
        CATALOGS.put(host, tree);
    }


    /**
     * 1.判断不重复，写日志，成功就返回 2.缓存，批量刷文件并去重 2.定时回写到存储结构 4.写目录文件 5.写数据文件
     */

    public static void tree() {
        byte[] bs = MixAll.NULL_BYTE;
        do {
            ByteBuffer bb = ByteBuffer.wrap(bs);

            int index = bb.getInt();
            long beginOffset = bb.getLong();
            int bodyLength = bb.getInt();
            byte[] dst = new byte[bodyLength];
            bb.get(dst, bb.position(), bodyLength);
            String body = new String(dst);// 完整的URL，用于恢复
            int extLength = bb.getInt();
            dst = new byte[extLength];
            bb.get(dst, bb.position(), bodyLength);
            String ext = new String(dst);// map<k,v>类型JSON字符串

        } while ((bs = CatalogFile.read()) != MixAll.NULL_BYTE);
    }
}
