package org.jiumao.example.utils.textFilter;

import java.util.Collection;

/**
 * 文本过滤接口<br>
 * 过滤结果是字符串集合
 * @author ppf@jiumao.org
 * @date 2017年1月3日
 */
public interface TextFormat extends Format {
	/**
	 * 要求返回值是String集合
	 */
	@SuppressWarnings("unchecked")
	Collection<String> doWithFormat(String text);
}
