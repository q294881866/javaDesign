package org.jiumao.example.ipsearch;
/**
 * 查询接口
 * @author ppf@jiumao.org
 * @date 2016年12月23日
 */
public interface Search {
	/**
	 * 通过参数招到索引
	 * @param para
	 * @return
	 */
	String getIndex(String para);
	
	/**
	 * 添加一条索引
	 * @param para
	 * @return
	 */
	boolean addIndex(String index, String para);
}
