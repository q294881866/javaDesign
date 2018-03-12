package org.jiumao.example.utils.textFilter;

/**
 * 文本过滤操作接口，所有的文本过滤方式都要实现这个接口
 * @author ppf@jiumao.org
 * @date 2016年11月23日
 */
public interface Format {
	/**
	 * E为返回值类型<br>使用者定义为任意类型
	 * @param text
	 * 		要处理的文本
	 * @return
	 * 		返回数据类型
	 */
	<E>E doWithFormat(String text);
}
