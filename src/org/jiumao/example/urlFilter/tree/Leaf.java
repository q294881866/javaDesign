package org.jiumao.example.urlFilter.tree;

import java.util.HashMap;
import java.util.Map;

import org.jiumao.example.utils.annotation.Required;

/**
 * 叶子节点，数据结构
 * @author ppf@jiumao.org
 * @date 2017年9月19日
 */
public class Leaf {
    
    /**叶子节点占用物理空间大小*/
    @Required private int length;
    /**叶子序号*/
    @Required private int index;
    /**开始写入的文件相对物理位置，用于删除时指定新的起始位置*/
    @Required private long beginOffset;
    
    /**序列化为JSON字符串*/
    @Required private Map<String,String> ext = new HashMap<>(1);

    public Leaf(int index, long beginOffset) {
        super();
        this.index = index;
        this.beginOffset = beginOffset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getBeginOffset() {
        return beginOffset;
    }

    public void setBeginOffset(long beginOffset) {
        this.beginOffset = beginOffset;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public void setExt(Map<String, String> ext) {
        this.ext = ext;
    }
}
