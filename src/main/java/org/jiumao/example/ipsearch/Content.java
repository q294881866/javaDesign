package org.jiumao.example.ipsearch;
/**
 * 根据查找到的索引获取内容<br>
 * 可以从数据库、内存的Map、或者直接读取文件（Nio，可以随机读写）
 * @author ppf@jiumao.org
 * @date 2016年12月23日
 */
public interface Content<T> {

	String getContent(T index);
	
}
